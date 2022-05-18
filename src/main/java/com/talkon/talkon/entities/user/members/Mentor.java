package com.talkon.talkon.entities.user.members;
import com.talkon.talkon.entities.base.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "users")
@Entity(name = "mentors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mentor extends Auditable {
}
