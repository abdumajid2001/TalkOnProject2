package com.talkon.talkon.entities.user;

import com.talkon.talkon.entities.base.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "users")
@Entity(name = "account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends Auditable {
   @OneToOne
   User user;
   Integer talkCoins;

}


