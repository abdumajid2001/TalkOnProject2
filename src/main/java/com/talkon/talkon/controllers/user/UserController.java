package com.talkon.talkon.controllers.user;

import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.transaction.CoinTransferDto;
import com.talkon.talkon.dtos.user.user.LoginDto;
import com.talkon.talkon.dtos.user.user.SessionDto;
import com.talkon.talkon.services.user.user.UserService;
import com.talkon.talkon.controllers.AbstractController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/auth/access/token",method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> login(LoginDto dto) {
        return service.getToken(dto);
    }

    @RequestMapping(value = PATH + "/auth/register",method = RequestMethod.POST)
    public ResponseEntity<Void> getCode(String phoneNumber){
        service.getCode(phoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/transaction/per-day")
    public HttpEntity<?> getTransactionPerDay(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate end
    ){
        return service.getAllTransactionByDate(start, end);
    }

    @PostMapping
    public HttpEntity<?> transferCoins(@RequestBody CoinTransferDto coinTransferDto){
        return service.transferCoins(coinTransferDto);
    }



}
