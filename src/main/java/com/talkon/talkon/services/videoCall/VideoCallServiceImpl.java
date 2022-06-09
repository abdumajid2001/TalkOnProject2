package com.talkon.talkon.services.videoCall;

import com.talkon.talkon.config.security.UserSession;
import com.talkon.talkon.dtos.videoCall.VideoCallCreateDto;
import com.talkon.talkon.entities.conversation.video.VideoCall;
import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.enums.VideoCallStatus;
import com.talkon.talkon.exceptions.user.MentorNotFoundException;
import com.talkon.talkon.projections.videoCallRepository.VideoCAllProjection;
import com.talkon.talkon.repositories.user.member.mentee.MenteeRepository;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import com.talkon.talkon.repositories.videoCallRepository.VideoCallRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class VideoCallServiceImpl implements VideoCallService {

    private VideoCallRepository videoCallRepository;
    private MenteeRepository menteeRepository;
    private MentorRepository mentorRepository;
    private UserSession userSession;

    @Override
    public HttpEntity<?> saveVideoCall(VideoCallCreateDto videoCallCreateDto) {
        Optional<Mentee> byId = menteeRepository.findById(videoCallCreateDto.getMenteeId());
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("Mentee not found");
        }
        Optional<Mentor> byId1 = mentorRepository.findById(videoCallCreateDto.getMentorId());
        if (!byId1.isPresent()) {
            throw new MentorNotFoundException("Mentor not found");
        }
        VideoCallStatus videoCallStatus = Enum.valueOf(VideoCallStatus.class, videoCallCreateDto.getVideoCallStatus());

        User user = userSession.getUser().getUser();



        Mentee mentee = byId.get();
        Mentor mentor = byId1.get();
        VideoCall videoCall = new VideoCall();
        videoCall.setMentee(mentee);
        videoCall.setMentor(mentor);
        videoCall.setCreatedAt(LocalDateTime.now());
        videoCall.setVideoLink(videoCallCreateDto.getVideoLink());
        videoCall.setVideoCallStatus(videoCallStatus);
        videoCall.setCreatedBy(user.getId());
        videoCall.setStartDateTime(videoCallCreateDto.getStartDateTime());
        videoCall.setEndDateTime(videoCallCreateDto.getEndDateTime());
        videoCall.setCoins(20);
        videoCall.setDuration(20);
        videoCallRepository.save(videoCall);
        return ResponseEntity.ok("Success");
    }

    @Override
    public HttpEntity<?> getVideoCalls(LocalDateTime start, LocalDateTime end) {
        User user = userSession.getUser().getUser();
        List<VideoCAllProjection> videoCalls = videoCallRepository.getVideoCalls(user.getId());
        return ResponseEntity.ok(videoCalls);
    }
}
