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
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "users",indexes = {
        @Index(name = "level_index",columnList = "level"),
        @Index(name = "conversationCount_index",columnList = "conversationCount")
})
@Entity(name = "mentees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentee extends Auditable {
    public Mentee(Level level) {
        this.setId(UUID.randomUUID().toString());
        this.level = level;
    }

    @Enumerated(EnumType.STRING)
    Level level;

    int conversationCount;

    @OneToOne(optional = false)
    User user;

    @OneToMany(mappedBy = "mentee")
    List<Schedule> schedules = new ArrayList<>();

}
