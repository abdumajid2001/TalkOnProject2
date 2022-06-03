package com.talkon.talkon.dtos.schedule;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto implements BaseGenericDto {


    @NotEmpty
    private List<@NotNull ScheduleTimeDto> scheduleTimes;


}
