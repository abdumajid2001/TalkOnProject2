package com.talkon.talkon.dtos.message;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageIdsDto implements BaseGenericDto {
    List<String> ids;
}
