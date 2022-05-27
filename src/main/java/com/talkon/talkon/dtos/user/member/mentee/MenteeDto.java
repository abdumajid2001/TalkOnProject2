package com.talkon.talkon.dtos.user.member.mentee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.talkon.talkon.dtos.base.GenericDto;
import com.talkon.talkon.enums.Level;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenteeDto extends GenericDto {
    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private LocalDate dataOfBirth;

    private int timeZone;

    Level level;

    int conversationCount;

    private String photoPath;

    @Builder(builderMethodName = "childBuilder")
    public MenteeDto(String id, String firstName, String lastName, String email, String username, LocalDate dataOfBirth, int timeZone, Level level, int conversationCount, String photoPath) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.dataOfBirth = dataOfBirth;
        this.timeZone = timeZone;
        this.level = level;
        this.conversationCount = conversationCount;
        this.photoPath = photoPath;
    }
}
