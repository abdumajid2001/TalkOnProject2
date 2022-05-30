package com.talkon.talkon.services.user.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkon.talkon.config.security.UserSession;
import com.talkon.talkon.dtos.responce.AppErrorDto;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.user.*;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.exceptions.user.PhoneNumberAlready;
import com.talkon.talkon.exceptions.user.UserBlockedException;
import com.talkon.talkon.properties.ServerProperties;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.mappers.user.user.UserMapper;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.user.UserValidator;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImp extends AbstractService<UserRepository, UserMapper, UserValidator> implements UserService, UserDetailsService {
    public static final String ACCOUNT_SID = "AC16a37f0224d16647425d873c8cb5c580";
    public static final String AUTH_TOKEN = "7c95879559d7059682bc74ec547a3a47";
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final ServerProperties serverProperties;
    private final int BLOCKED_TIME_SECOND = 3600;
    public static int EXPIRY_TIME_SECOND = 3600;


    public UserServiceImp(UserMapper mapper,
                          UserValidator validator,
                          UserRepository repository,
                          PasswordEncoder passwordEncoder,
                          ObjectMapper objectMapper,
                          ServerProperties serverProperties
    ) {
        super(mapper, validator, repository);
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
        this.serverProperties = serverProperties;
    }

    @Override
    @Transactional(dontRollbackOn = {UserNotFoundException.class})
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User findUser = repository.findByPhoneNumberAndDeletedFalse(phoneNumber).orElseThrow(() -> {
            User user = User.childBuilder()
                    .id(UUID.randomUUID().toString())
                    .phoneNumber(phoneNumber)
                    .status((short) 1)
                    .expiry(LocalDateTime.now())
                    .build();
            repository.save(user);
            throw new UserNotFoundException("user Not found");
        });
        return new com.talkon.talkon.dtos.user.user.UserDetails(findUser);
    }

    public ResponseEntity<DataDto<SessionDto>> getToken(LoginDto dto) {

        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(),
                    AppErrorDto.class)), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }

    @Transactional(dontRollbackOn = {UserBlockedException.class})
    @Override
    public void getCode(String phoneNumber) {
        int code = new Random().nextInt(999999) + 100000;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Optional<User> userOptional = repository.findByPhoneNumberAndDeletedFalse(phoneNumber);
        User user = checkUserToBlock(userOptional);
        sendSmstoPhone(phoneNumber, code);
        user.setTryCount(user.getTryCount() + 1);
        user.setPhoneNumber(phoneNumber);
        user.setCode(passwordEncoder.encode(code + ""));
        user.setExpiry(LocalDateTime.now().plusSeconds(EXPIRY_TIME_SECOND));
        repository.save(user);

    }

    private User checkUserToBlock(Optional<User> userOptional) {
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (user.getStatus() == 1) {
                if (user.getUpdatedAt().isAfter(LocalDateTime.now().minusSeconds(BLOCKED_TIME_SECOND))) {
                    throw new UserBlockedException("User blokcked");
                } else {
                    user.setStatus((short) 0);
                    user.setTryCount(0);
                }
            } else if (user.getTryCount() >= 3) {
                user.setStatus((short) 1);
                repository.save(user);
                throw new UserBlockedException("User blokcked");
            }
        } else user = new User();
        return user;
    }

    private void sendSmstoPhone(String phoneNumber, int code) {
        Runnable runnable = () -> {
            Message message = Message.creator(
                            new PhoneNumber(phoneNumber),
                            new PhoneNumber("+18144984339"),
                            code + " shu kodni jo'nating!!!")
                    .create();
            System.out.println(message.getSid());
        };
        new Thread(runnable).start();
    }
}
