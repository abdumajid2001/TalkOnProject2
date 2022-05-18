package com.talkon.talkon.talkon.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDto implements Serializable {

    private Long expiryForAccessToken;

    private String accessToken;

    private Long expiryForRefreshToken;

    private String refreshToken;

    private Long issuedAd;
}
