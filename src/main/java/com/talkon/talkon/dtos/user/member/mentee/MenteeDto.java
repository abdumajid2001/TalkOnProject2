package com.talkon.talkon.dtos.user.member.mentee;

import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Level;
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

    @NotBlank
    private Integer timeZone;

    Level level;

    Integer conversationCount;

    public MenteeDto(String id, String firstName, String lastName, String email, String username, LocalDate dataOfBirth, Integer timeZone, Level level, Integer conversationCount) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.timeZone = timeZone;
        this.level = level;
        this.conversationCount = conversationCount;
    }
}
