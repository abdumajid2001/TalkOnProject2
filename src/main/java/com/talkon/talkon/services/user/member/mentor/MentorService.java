package com.talkon.talkon.services.user.member.mentor;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.criteria.base.GenericCriteria;
import com.talkon.talkon.dtos.schedule.ScheduleDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorCreateDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDto;
import com.talkon.talkon.dtos.user.member.mentor.MentorDtoForGetAll;
import com.talkon.talkon.dtos.user.member.mentor.MentorUpdateDto;
import com.talkon.talkon.services.base.GenericCrudService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MentorService extends GenericCrudService<
        MentorDto,
        MentorCreateDto,
        MentorUpdateDto,
        String,
        GenericCriteria
        > {
    void block(String id);

    void unBlock(String id);


    HttpEntity<?> saveSchedule(ScheduleDto scheduleDto);

    HttpEntity<?> getAllSchedule(String mentorId);
    ResponseEntity<?> seeHistories(String id, int page, int size);

    List<MentorDtoForGetAll> getAllForAll(GenericCriteria criteria);
}
