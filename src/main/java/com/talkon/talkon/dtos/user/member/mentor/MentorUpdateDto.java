package com.talkon.talkon.dtos.user.member.mentor;

import com.talkon.talkon.dtos.base.GenericDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MentorUpdateDto extends GenericDto {

    private String mentorId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private double longitude;

    private double latitude;

    private String aboutText;

}
