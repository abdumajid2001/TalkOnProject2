package com.talkon.talkon.services.user.user;


import com.talkon.talkon.dtos.responce.DataDto;
import com.talkon.talkon.dtos.transaction.CoinTransferDto;
import com.talkon.talkon.dtos.user.user.*;
import com.talkon.talkon.services.base.BaseGenericService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface UserService extends BaseGenericService {

    ResponseEntity<DataDto<SessionDto>> getToken(LoginDto dto);

    void getCode(String phoneNumber);

    HttpEntity<?> getAllTransactionByDate(LocalDate start, LocalDate end);

    HttpEntity<?> transferCoins(CoinTransferDto coinTransferDto);
}
