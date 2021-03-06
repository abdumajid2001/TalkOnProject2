package com.talkon.talkon.dtos.user.member.mentee;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.enums.Level;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MenteeCreateDto implements BaseGenericDto {

    private String id;

    private Level level;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private int timeZone;
}
