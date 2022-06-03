package com.talkon.talkon.dtos.user.user;


import com.talkon.talkon.dtos.base.GenericDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto extends GenericDto {

    private String firstname;

    private String phoneNumber;

    private String pathPicture;
}
