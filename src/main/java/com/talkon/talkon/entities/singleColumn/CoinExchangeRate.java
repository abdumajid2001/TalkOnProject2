package com.talkon.talkon.entities.singleColumn;

import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "coin_exchange_rates")
public class CoinExchangeRate extends Auditable {
    /// cent da berilgan 1 ta coin narxi
    double rate;


}
