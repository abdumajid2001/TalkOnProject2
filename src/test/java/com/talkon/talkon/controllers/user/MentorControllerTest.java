package com.talkon.talkon.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkon.talkon.dtos.schedule.ScheduleDto;
import com.talkon.talkon.dtos.schedule.ScheduleTimeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureMockMvc
class MentorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void saveSchedule() {
        List<ScheduleTimeDto> times = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            times.add(new ScheduleTimeDto(LocalDateTime.now()));
        }
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setScheduleTimes(times);
        try {
            ResultActions perform = mockMvc.perform(
                    post("/api/v1/schedule")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(scheduleDto))
            );
            perform.andExpect(result -> new String(result.getResponse().getContentAsByteArray()).equals("successfully saved"));
            perform.andExpect(status().is2xxSuccessful());
            System.out.println(perform.andReturn().getResponse().getStatus());
            System.out.println(perform);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    void getAllSchedule() throws Exception {
        ResultActions perform = mockMvc.perform(get("/api/v1/schedule/all")
                .contentType(MediaType.APPLICATION_JSON)
                        .param("mentorId", "1b03e87c-e042-4497-8467-a4e44b515fba")
        );
        perform.andExpect(status().is2xxSuccessful());
        perform.andExpect(content().contentType(MediaType.APPLICATION_JSON));
        String s = new String(perform.andReturn().getResponse().getContentAsByteArray());
        System.out.println(s);
    }






}