package com.talkon.talkon.dtos.purchase;

import com.talkon.talkon.dtos.base.BaseGenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseCoinDto implements BaseGenericDto{
    @NotNull(message = "paytype berilishi shart") @NotBlank(message = "paytype bo'sh bo'lmasligi kerak shart") @NotEmpty(message = "paytype berilishi shart")
    String paytype;

    @NotNull(message = "coin berilishi shart") @Size(min = 1, max = 1000000, message = "coin min 1 max 1000000 shu oraliqda bo'lishi shart")
    Integer coin;

}
