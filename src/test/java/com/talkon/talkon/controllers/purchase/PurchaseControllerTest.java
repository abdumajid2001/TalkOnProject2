package com.talkon.talkon.controllers.purchase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkon.talkon.dtos.purchase.PurchaseCoinDto;
import com.talkon.talkon.enums.PayType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureMockMvc
class PurchaseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getPayTypes() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/v1/paytype"));
        perform.andExpect(status().is2xxSuccessful());
        System.out.println(new String(perform.andReturn().getResponse().getContentAsByteArray()));
    }

    @Test
    void purchaseTalkCoin() throws Exception {

        PurchaseCoinDto coinDto = new PurchaseCoinDto();
        coinDto.setCoin(24);
        coinDto.setPaytype(PayType.CLICK.toString());
        mockMvc.perform(post("/api/v1/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(coinDto))
        );
    }

    @Test
    void purchaseSchedule() throws Exception {

        ResultActions perform = mockMvc.perform(post("/api/v1/schedule/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .param("scheduleId", "feb0511c-de5a-464d-87e9-046e2b6cbef1")
        );
        perform.andExpect(status().is2xxSuccessful());
        System.out.println(new String(perform.andReturn().getResponse().getContentAsByteArray()));

    }
}