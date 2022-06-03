package com.talkon.talkon.services.user.member.mentee;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.user.member.mentee.MenteeCreateDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.exceptions.user.NotFoundUserIdException;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.mappers.user.member.mentee.MenteeMapper;
import com.talkon.talkon.mappers.user.user.UserMapper;
import com.talkon.talkon.repositories.user.member.mentee.MenteeRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.member.mentee.MenteeValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MenteeServiceImpl extends AbstractService<
        MenteeRepository,
        MenteeMapper,
        MenteeValidator
        > implements MenteeService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public MenteeServiceImpl(MenteeMapper mapper, MenteeValidator validator, MenteeRepository repository, UserRepository userRepository, UserMapper userMapper) {
        super(mapper, validator, repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public String create(MenteeCreateDto dto) {
        validator.validOnCreate(dto);
        Optional<User> userOptional = userRepository.findById(dto.getId());
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
        user = userMapper.fromCreateDto(dto, user);
        User savedUser = userRepository.save(user);

        Mentee mentee = new Mentee(dto.getLevel());
        mentee.setUser(savedUser);
        repository.save(mentee);
        return user.getId();
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        userRepository.deleteByIdMy(id);
        repository.deleteByUserId(id);
    }

    @Override
    public void update(MenteeUpdateDto dto) {
        validator.validOnUpdate(dto);
        User user = null;
        Optional<User> userOptional = userRepository.findById(dto.getId());
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
        user = userMapper.fromUpdateDto(dto, user);
        userRepository.save(user);
    }

    @Override
    public MenteeDto get(String id) {
        MenteeDto mentee = repository.getMenteeById(id);
        if (Objects.isNull(mentee)) {
            throw new UserNotFoundException("User not found");
        }
        return mentee;
    }

    @Override
    public List<MenteeDto> getAll(GenericCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage() - 1, criteria.getSize());
        return repository.findAllMentee(pageRequest);
    }

    public void block(String id) {
        repository.block(id);
    }


    public void unBlock(String id) {
        repository.unBlock(id);
    }
}
