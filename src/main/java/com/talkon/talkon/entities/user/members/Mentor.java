package com.talkon.talkon.entities.user.members;
import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.schedule.Schedule;
import com.talkon.talkon.entities.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "users")
@Entity(name = "mentors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentor extends Auditable {

    @OneToOne(optional = false)
    User user;
    @Column(nullable = false)
    Integer experience;
    @Column(unique = true, nullable = false)
    String aboutMediaLink;
    @Column(nullable = false)
    String aboutText;

    Integer ratingCount;
    Integer ratingValue;


    @OneToMany(mappedBy = "mentor")
    List<Schedule> schedules = new ArrayList<>();

}
