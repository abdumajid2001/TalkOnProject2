package com.talkon.talkon.services.user;

import com.talkon.talkon.repositories.user.UserRepository;
import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.user.LoginDto;
import com.talkon.talkon.dtos.user.UserCreateDto;
import com.talkon.talkon.dtos.user.UserDto;
import com.talkon.talkon.dtos.user.UserUpdateDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.exceptions.user.UserNotFoundException;
import com.talkon.talkon.mappers.user.UserMapper;
import com.talkon.talkon.services.base.AbstractService;
import com.talkon.talkon.validators.user.UserValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp extends AbstractService<UserRepository, UserMapper, UserValidator> implements UserService, UserDetailsService {

    protected UserServiceImp(UserMapper mapper, UserValidator validator, UserRepository repository) {
        super(mapper, validator, repository);
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

    @Override
    public String login(LoginDto dto) {

        return null;
    }
}
