package com.talkon.talkon.projections.history;

import com.talkon.talkon.enums.VideoCallStatus;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

public interface HistoryProjection {

    String getPicture();

    String getVideoLink();

    LocalDateTime getStartDateTime();

    LocalDateTime getEndDateTime();

    VideoCallStatus getVideoCallStatus();

    int getDuration();
}
