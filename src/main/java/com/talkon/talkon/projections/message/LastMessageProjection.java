package com.talkon.talkon.projections.message;

import java.time.LocalDateTime;

public interface LastMessageProjection {

    String getBody();
    LocalDateTime getCreatedAt();
}
