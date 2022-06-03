package com.talkon.talkon.repositories.schedule;

import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.projections.schedule.ScheduleBodyProjection;
import com.talkon.talkon.projections.schedule.ScheduleProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends AbstractRepository<Schedule, String> {

    @Query(nativeQuery = true,
            value = "select cast(start_date_time as date) as \"date\", :mentorId as \"mentorId\" from schedule.schedules\n" +
            "join users.mentors m on schedules.mentor_id = m.id\n" +
            "where schedule_status='NEW'\n" +
            "and schedules.start_date_time>now() and m.id = :mentorId\n" +
            "group by \"date\"")
    List<ScheduleProjection> getAllScheduleByMentorId(String mentorId);

    @Query(nativeQuery = true,
            value = "select schedules.id as \"id\",\n" +
                    "       cast(start_date_time as time) as \"time\",\n" +
                    "       cost as \"cost\",\n" +
                    "       duration as \"duration\"\n" +
                    "from schedule.schedules\n" +
                    "join users.mentors on schedules.mentor_id = mentors.id\n" +
                    "where cast(start_date_time as date) = :date\n" +
                    "and mentors.id = :mentorId")
    List<ScheduleBodyProjection> getScheduleByDateAndMentorId(String mentorId, Date date);
}
