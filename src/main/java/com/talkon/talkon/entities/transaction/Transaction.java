package com.talkon.talkon.entities.transaction;

import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.*;

import com.talkon.talkon.entities.user.Account;
import com.talkon.talkon.enums.PayType;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "transaction")
@Entity(name = "transactions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction extends Auditable {

    Double amount;

    @Enumerated(EnumType.STRING)
    PayType payType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

}
