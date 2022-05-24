package com.talkon.talkon.entities.user.members;

import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.entities.user.User;

import javax.persistence.Table;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "users", indexes = {
        @Index(name = "experience_index", columnList = "experience"),
        @Index(name = "ratingValue_index", columnList = "ratingValue")
})
@Entity(name = "mentors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentor extends Auditable {

    @OneToOne(optional = false)
    User user;

    @Column(nullable = false)
    int experience;

    String aboutMediaLink;

    String aboutText;

    int ratingCount;

    int ratingValue;

    @OneToMany(mappedBy = "mentor")
    List<Schedule> schedules = new ArrayList<>();

    public Mentor(int experience) {
        this.setId(UUID.randomUUID().toString());
        this.experience = experience;
    }
}
