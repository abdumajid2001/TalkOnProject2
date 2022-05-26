package com.talkon.talkon.dtos.user.member.mentee;

import com.talkon.talkon.dtos.base.GenericDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MenteeUpdateDto extends GenericDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private int timeZone;
}
