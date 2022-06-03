package com.talkon.talkon.projections.review;

import java.time.LocalDateTime;

public interface ReviewProjection {

    String getMenteeId();

    String getUsername();

    String getBody();

    String getPhotoPath();

    LocalDateTime getCreatedDate();
}
