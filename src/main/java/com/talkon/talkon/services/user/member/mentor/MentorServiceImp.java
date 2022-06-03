package com.talkon.talkon.services.user.member.mentor;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.exceptions.MentorIdNotFoundException;
import com.talkon.talkon.exceptions.user.MentorNotFoundException;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.mappers.user.member.mentor.MentorMapper;
import com.talkon.talkon.mappers.user.user.UserMapper;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.member.mentor.MentorValidation;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class MentorServiceImp extends AbstractService<MentorRepository, MentorMapper, MentorValidation> implements MentorService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public MentorServiceImp(MentorMapper mapper, MentorValidation validator, MentorRepository repository, UserRepository userRepository, UserMapper userMapper) {
        super(mapper, validator, repository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public String create(MentorCreateDto dto) {
        validator.validOnCreate(dto);
        User user = null;
        Optional<User> userOptional = userRepository.findById(dto.getId());
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
        user = mapper.fromCreateDto(dto, user);
        User savedUser = userRepository.save(user);
        Mentor mentee = new Mentor(dto.getExperience());
        mentee.setUser(savedUser);
        repository.save(mentee);
        return dto.getId();
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        userRepository.deleteByIdMy(id);
        repository.deleteByUserId(id);
    }

    @Override
    public void update(MentorUpdateDto dto) {
        validator.validOnUpdate(dto);
        User user = mapper.fromUpdateDto(dto, userRepository.findById(dto.getId()).get());
        userRepository.save(user);

        if (Objects.nonNull(dto.getAboutText())) {
            if (Objects.isNull(dto.getMentorId())) {
                throw new MentorIdNotFoundException("MentorId Not Found Exception");
            }
            Mentor mentor = null;
            Optional<Mentor> mentorOptional = repository.findByIdAndDeletedFalse(dto.getMentorId());
            if (mentorOptional.isPresent()) {
                mentor = mentorOptional.get();
            } else {
                throw new MentorNotFoundException("Mentor Not Found Exception");
            }
            mentor.setAboutText(dto.getAboutText());
            repository.save(mentor);
        }
    }

    @Override
    public MentorDto get(String id) {
        MentorDto mentee = repository.getMentorById(id);
        if (Objects.isNull(mentee)) {
            throw new MentorNotFoundException("Mentor not found");
        }
        return mentee;
    }

    @Override
    public List<MentorDto> getAll(GenericCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage() - 1, criteria.getSize());
        repository.getAll(pageRequest);
        return null;
    }

    @Override
    public void block(String id) {
        repository.block(id);
    }

    @Override
    public void unBlock(String id) {
        repository.unBlock(id);
    }
}
