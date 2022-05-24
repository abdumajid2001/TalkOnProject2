package com.talkon.talkon.dtos.user.member.mentor;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Level;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MentorCreateDto extends GenericDto {

    private int experience;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private double longitude;

    private double latitude;

}
