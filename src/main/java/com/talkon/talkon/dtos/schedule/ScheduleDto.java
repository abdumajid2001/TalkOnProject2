package com.talkon.talkon.dtos.schedule;

import com.talkon.talkon.dtos.ScheduleTimeDto;
import com.talkon.talkon.dtos.base.BaseGenericDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto implements BaseGenericDto {

    @NotEmpty
    List<@Valid ScheduleTimeDto> scheduleTimeDto;


}
