package com.talkon.talkon.dtos.user.member.mentor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Level;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MentorDto extends GenericDto {

    private int experience;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private double longitude;

    private double latitude;

    String aboutMediaLink;

    String aboutText;

    int ratingValue;

    String photoPath;

    @Builder(builderMethodName = "childBuilder")
    public MentorDto(String id, int experience, String firstName, String lastName, String email, String username, LocalDate dataOfBirth, double longitude, double latitude, String aboutMediaLink, String aboutText, int ratingValue, String photoPath) {
        super(id);
        this.experience = experience;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.longitude = longitude;
        this.latitude = latitude;
        this.aboutMediaLink = aboutMediaLink;
        this.aboutText = aboutText;
        this.ratingValue = ratingValue;
        this.photoPath = photoPath;
    }
}
