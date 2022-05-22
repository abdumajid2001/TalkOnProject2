package com.talkon.talkon.dtos.user;

import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class UserDto extends GenericDto {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private Role role;

    private Integer timeZone;

}
