package com.talkon.talkon.dtos.user.member.mentee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.talkon.talkon.dtos.base.GenericDto;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MenteeDtoForGetAll extends GenericDto {
    private String username;
    private String photoPath;

    @Builder(builderMethodName = "childBuilder")
    public MenteeDtoForGetAll(String id, String username, String photoPath) {
        super(id);
        this.username = username;
        this.photoPath = photoPath;
    }
}
