package com.talkon.talkon.entities.user.members;
import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.user.User;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "users")
@Entity(name = "mentees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentee extends Auditable {
    @OneToOne
    User user;
    Integer experience;
    String aboutMediaLink;
    String aboutText;
    Integer ratingCount;
    Integer ratingValue;
}
