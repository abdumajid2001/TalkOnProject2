package com.talkon.talkon.services.user.member.mentee;

import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.user.member.mentee.MenteeCreateDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeDto;
import com.talkon.talkon.dtos.user.member.mentee.MenteeUpdateDto;
import com.talkon.talkon.services.base.GenericCrudService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface MenteeService extends GenericCrudService<
        MenteeDto,
        MenteeCreateDto,
        MenteeUpdateDto,
        String,
        GenericCriteria
        > {
    void block(String id);

    void unBlock(String id);

    ResponseEntity<?> seeHistories(int page, int size, String id);


}
