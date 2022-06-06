package com.talkon.talkon.controllers.videoCall;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.dtos.videoCall.VideoCallCreateDto;
import com.talkon.talkon.services.videoCall.VideoCallService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public class VideoCallController extends AbstractController<VideoCallService> {



    protected VideoCallController(VideoCallService service) {
        super(service);

    }



    @PostMapping(PATH+"/video-call")
    public HttpEntity<?> saveVideoCall(VideoCallCreateDto videoCallCreateDto){
        return service.saveVideoCall(videoCallCreateDto);
    }

    @GetMapping(PATH+"/video-call")
    public HttpEntity<?> getVideoCalls(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
            ){
        return service.getVideoCalls(start, end);
    }
}
