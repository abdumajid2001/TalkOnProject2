package com.talkon.talkon.dtos.review;

import com.talkon.talkon.dtos.base.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewUpdateDto extends GenericDto {
    String body;
}
