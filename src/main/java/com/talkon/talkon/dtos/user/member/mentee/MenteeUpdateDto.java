package com.talkon.talkon.dtos.user.member.mentee;

import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Level;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter
public class MenteeUpdateDto extends GenericDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    @NotBlank
    private Integer timeZone;
}
