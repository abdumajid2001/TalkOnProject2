package com.talkon.talkon.dtos.user.member.mentee;

import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Level;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class MenteeDto extends GenericDto {
    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private double longitude;

    private double latitude;

    Level level;

    Integer conversationCount;

    @Builder(builderMethodName = "childBuilder")
    public MenteeDto(String id, String firstName, String lastName, String email, String username, LocalDate dataOfBirth, double longitude, double latitude, Level level, Integer conversationCount) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.conversationCount = conversationCount;
    }
}
