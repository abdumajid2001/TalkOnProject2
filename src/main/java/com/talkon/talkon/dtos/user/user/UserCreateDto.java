package com.talkon.talkon.dtos.user.user;

import com.talkon.talkon.dtos.base.BaseGenericDto;
import com.talkon.talkon.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto implements BaseGenericDto {
    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private Role role;

    @NotBlank
    private Integer timeZone;
}
