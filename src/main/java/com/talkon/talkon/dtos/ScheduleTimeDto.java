package com.talkon.talkon.dtos;

import com.talkon.talkon.dtos.base.BaseGenericDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ScheduleTimeDto implements BaseGenericDto {

    @NotNull
    LocalDateTime start;

    @NotNull
    LocalDateTime end;
}
