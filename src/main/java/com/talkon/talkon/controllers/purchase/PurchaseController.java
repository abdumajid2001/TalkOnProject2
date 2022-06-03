package com.talkon.talkon.controllers.purchase;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.dtos.purchase.PurchaseCoinDto;
import com.talkon.talkon.services.purchase.PurchaseService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class PurchaseController extends AbstractController<PurchaseService> {
    protected PurchaseController(PurchaseService service) {
        super(service);
    }

    @GetMapping(PATH+"/paytype")
    public HttpEntity<?> getPayTypes(){
        return service.getPayTypes();
    }

    @PostMapping(PATH+"/purchase")
    public HttpEntity<?> purchaseTalkCoin(@RequestBody PurchaseCoinDto purchaseCoinDto){
        return service.purchaseTalkCoin(purchaseCoinDto);
    }

    @PostMapping(PATH+"/schedule/purchase")
    public HttpEntity<?> purchaseSchedule(@RequestParam String scheduleId){
        return service.purchaseSchedule(scheduleId);
    }

}
