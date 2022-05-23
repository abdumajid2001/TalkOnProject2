package com.talkon.talkon.entities.user.members;
import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.entities.user.User;

import javax.persistence.*;

import com.talkon.talkon.enums.Level;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "users")
@Entity(name = "mentees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentee extends Auditable {

    @Enumerated(EnumType.STRING)
    Level level;

    Integer conversationCount;

    @OneToOne(optional = false)
    User user;

    @OneToMany(mappedBy = "mentee")
    List<Schedule> schedules = new ArrayList<>();

}
