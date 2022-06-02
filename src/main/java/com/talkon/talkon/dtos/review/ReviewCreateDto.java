package com.talkon.talkon.dtos.review;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewCreateDto implements BaseGenericDto {
    String body;
    String mentorId;
}
