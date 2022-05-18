package com.talkon.talkon.controllers.user;

import com.talkon.talkon.dtos.user.LoginDto;
import com.talkon.talkon.services.user.UserService;
import com.talkon.talkon.controllers.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(name = PATH+"/access/token")
    public ResponseEntity<String> login(LoginDto dto){
            return new ResponseEntity<>(service.login(dto), HttpStatus.OK);
    }

}
