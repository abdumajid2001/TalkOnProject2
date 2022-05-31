package com.talkon.talkon.projections.schedule;

import java.sql.Time;

public interface ScheduleBodyProjection {

    String getId();

    Time getTime();

    Integer getCost();

    Integer getDuration();
}
