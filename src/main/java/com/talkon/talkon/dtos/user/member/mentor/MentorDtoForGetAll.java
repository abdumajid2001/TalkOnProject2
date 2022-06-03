package com.talkon.talkon.dtos.user.member.mentor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.talkon.talkon.dtos.base.GenericDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MentorDtoForGetAll extends GenericDto {
    private String username;
    private String photoPath;

    @Builder(builderMethodName = "childBuilder")
    public MentorDtoForGetAll(String id, String username, String photoPath) {
        super(id);
        this.username = username;
        this.photoPath = photoPath;
    }
}
