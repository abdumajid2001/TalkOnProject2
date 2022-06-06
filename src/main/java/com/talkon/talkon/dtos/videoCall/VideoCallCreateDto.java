package com.talkon.talkon.dtos.videoCall;

import com.talkon.talkon.dtos.base.BaseGenericDto;

import java.time.LocalDateTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoCallCreateDto implements BaseGenericDto {

    String videoLink;

    String menteeId;

    String mentorId;

    LocalDateTime startDateTime;

    LocalDateTime endDateTime;

    String videoCallStatus;
}
