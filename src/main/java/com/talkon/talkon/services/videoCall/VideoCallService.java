package com.talkon.talkon.services.videoCall;

import com.talkon.talkon.dtos.videoCall.VideoCallCreateDto;
import com.talkon.talkon.services.base.BaseGenericService;
import org.springframework.http.HttpEntity;

import java.time.LocalDateTime;

public interface VideoCallService extends BaseGenericService {


    HttpEntity<?> saveVideoCall(VideoCallCreateDto videoCallCreateDto);

    HttpEntity<?> getVideoCalls(LocalDateTime start, LocalDateTime end);
}
