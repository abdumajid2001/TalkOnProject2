package com.talkon.talkon.services.purchase;

import com.talkon.talkon.dtos.purchase.PurchaseCoinDto;
import com.talkon.talkon.services.base.BaseGenericService;
import org.springframework.http.HttpEntity;


public interface PurchaseService extends BaseGenericService {

    HttpEntity<?> getPayTypes();

    HttpEntity<?> purchaseTalkCoin(PurchaseCoinDto purchaseCoinDto);

    HttpEntity<?> purchaseSchedule(String scheduleId);
}
