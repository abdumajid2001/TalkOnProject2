package com.talkon.talkon.controllers.user;

import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.user.user.LoginDto;
import com.talkon.talkon.dtos.user.user.ProfileDto;
import com.talkon.talkon.dtos.user.user.SessionDto;
import com.talkon.talkon.services.user.user.UserService;
import com.talkon.talkon.controllers.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/auth/access/token",method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> login(@RequestBody LoginDto dto) {
        return service.getToken(dto);
    }

    @RequestMapping(value = PATH + "/auth/register/{phoneNumber}",method = RequestMethod.POST)
    public ResponseEntity<Void> getCode(@PathVariable String phoneNumber){
        service.getCode(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/auth/profile",method = RequestMethod.POST)
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDto profileDto){
        service.updateProfile(profileDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = PATH + "/auth/balance",method = RequestMethod.GET)
    public ResponseEntity<?> seeBalance(String id){
        return service.seeBalance(id);
    }
}
