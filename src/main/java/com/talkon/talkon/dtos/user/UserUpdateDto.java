package com.talkon.talkon.dtos.user;

import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends GenericDto {
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;
    // TODO: 18/05/22  /*regex yoz*/

    @Email
    private String email;

    @Column(unique = true)
    private String username;

    private Role role;

    private Integer timeZone;

}
