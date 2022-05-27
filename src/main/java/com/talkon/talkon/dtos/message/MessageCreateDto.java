package com.talkon.talkon.dtos.message;

import com.talkon.talkon.dtos.base.BaseGenericDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageCreateDto implements BaseGenericDto {

    String chatId;
    String fromId;
    String filePath;
    String parent;
    String body;

}
