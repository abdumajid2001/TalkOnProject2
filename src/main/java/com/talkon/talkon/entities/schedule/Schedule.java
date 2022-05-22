package com.talkon.talkon.entities.schedule;

import javax.persistence.*;

import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.enums.ScheduleStatus;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "schedule")
@Entity(name = "schedules")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule extends Auditable {

    @Column(nullable = false)
    LocalDateTime startDateTime;

    @Column(nullable = false)
    Integer duration;

    @Enumerated(EnumType.STRING)
    ScheduleStatus scheduleStatus;

    Integer cost;

    @ManyToOne
    Mentor mentor;

    @ManyToOne
    Mentee mentee;

}
