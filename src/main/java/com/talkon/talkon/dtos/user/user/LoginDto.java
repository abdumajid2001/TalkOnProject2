package com.talkon.talkon.dtos.user.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    @Pattern(regexp = "[+](998)[0-9]{9}",message = "Phone Number Xato kiritilgan")
    private String phoneNumber;

    private String code;

}
