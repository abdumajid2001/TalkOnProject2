package com.talkon.talkon.projections.schedule;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleProjection {

    LocalDate getDate();

    @Value("#{@scheduleRepository.getScheduleByDateAndMentorId(target.mentorId, target.date)}")
    List<ScheduleBodyProjection> getSchedule();

}
