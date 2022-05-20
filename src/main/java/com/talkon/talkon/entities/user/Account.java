package com.talkon.talkon.entities.user;

import com.talkon.talkon.entities.base.Auditable;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "users")
@Entity(name = "account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends Auditable {
   @OneToOne
   User user;
   Integer talkCoins;

}


