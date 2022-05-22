package com.talkon.talkon.dtos.chat;

import com.talkon.talkon.dtos.base.BaseGenericDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatCreateDto implements BaseGenericDto {

    String fromId;

    String currentUserId;

}
