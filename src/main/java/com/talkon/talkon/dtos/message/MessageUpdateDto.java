package com.talkon.talkon.dtos.message;

import com.talkon.talkon.dtos.base.GenericDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageUpdateDto extends GenericDto {
    String body;
}
