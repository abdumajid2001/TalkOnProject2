package com.talkon.talkon.validators.user.member.mentor;

import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.exceptions.ValidationException;
import com.talkon.talkon.exceptions.user.AlreadyEmail;
import com.talkon.talkon.exceptions.user.AlreadyUsername;
import com.talkon.talkon.exceptions.user.NotFoundUserException;
import com.talkon.talkon.exceptions.user.NotFoundUserIdException;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.validators.base.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MentorValidation extends AbstractValidator<MentorCreateDto, MentorUpdateDto, String> {

    private final UserRepository userRepository;

    public MentorValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validateKey(String id) throws ValidationException {
        existsUserByUserId(id);
    }

    @Override
    public void validOnCreate(MentorCreateDto dto) throws ValidationException {
        existsUserByUsername(dto.getUsername());
        existsUserByEmail(dto.getEmail());
    }

    @Override
    public void validOnUpdate(MentorUpdateDto dto) throws ValidationException {
        existsUserByUsername(dto.getUsername());
        existsUserByEmail(dto.getEmail());
    }

    private void existsUserByEmail(String email) {
        if (Objects.nonNull(email)) {
            if (userRepository.existsByEmailAndDeletedFalse(email)) {
                throw new AlreadyEmail("Already email");
            }
        }
    }

    private void existsUserByUsername(String username) {
        if (Objects.nonNull(username)) {
            if (userRepository.existsByUsernameAndDeletedFalse(username)) {
                throw new AlreadyUsername("Already username");
            }
        }
    }

    private void existsUserByUserId(String id) {
        if (Objects.isNull(id)) {
            throw new NotFoundUserIdException("Not found user id");
        }
        if (!userRepository.existsByIdAndDeletedFalse(id)) {
            throw new NotFoundUserException("Not found User");
        }
    }

}
