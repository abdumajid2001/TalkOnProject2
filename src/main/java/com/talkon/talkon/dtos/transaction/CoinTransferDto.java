package com.talkon.talkon.dtos.transaction;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoinTransferDto {

    String videoCallId;

    Double commissionFee;
}
