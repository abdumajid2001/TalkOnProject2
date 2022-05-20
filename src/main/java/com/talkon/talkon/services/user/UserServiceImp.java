package com.talkon.talkon.services.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkon.talkon.dtos.responce.AppErrorDto;
import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.*;
import com.talkon.talkon.properties.ServerProperties;
import com.talkon.talkon.repositories.user.UserRepository;
import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.mappers.user.UserMapper;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.UserValidator;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImp extends AbstractService<UserRepository, UserMapper, UserValidator> implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    private final ServerProperties serverProperties;

    public UserServiceImp(UserMapper mapper, UserValidator validator, UserRepository repository,
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.
                findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not found"));
        return new com.talkon.talkon.dtos.user.UserDetails(user);
    }

    @Override
    public String create(UserCreateDto dto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(UserUpdateDto dto) {

    }

    @Override
    public UserDto get(String id) {
        return null;
    }

    @Override
    public List<UserDto> getAll(BaseGenericCriteria criteria) {
        return null;
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

    @Override
    public void getCode(String phoneNumber) {

    }
}
