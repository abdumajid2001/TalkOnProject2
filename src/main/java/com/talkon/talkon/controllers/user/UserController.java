package com.talkon.talkon.controllers.user;

import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.LoginDto;
import com.talkon.talkon.dtos.user.SessionDto;
import com.talkon.talkon.services.user.UserService;
import com.talkon.talkon.controllers.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/auth/access/token",method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> login(LoginDto dto) {
        return service.getToken(dto);
    }

    @RequestMapping(value = PATH + "/auth/register")
    public ResponseEntity<Void> getCode(String phoneNumber){
        service.getCode(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
