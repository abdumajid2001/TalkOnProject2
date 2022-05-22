package com.talkon.talkon.dtos.user.member.mentee;

import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.enums.Level;
import com.talkon.talkon.enums.Role;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class MenteeDto extends GenericDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    @NotBlank
    private Integer timeZone;

    Level level;

    Integer conversationCount;
}
