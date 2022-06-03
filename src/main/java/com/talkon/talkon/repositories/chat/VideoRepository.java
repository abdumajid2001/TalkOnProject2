package com.talkon.talkon.repositories.chat;

import com.talkon.talkon.entities.conversation.video.VideoCall;
import com.talkon.talkon.projections.history.HistoryProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends AbstractRepository<VideoCall,String> {


    @Query(nativeQuery = true,value = "select users.photo_path as picture,conversation.video_calls.video_link as videoLink," +
            " conversation.video_calls.start_date_time\n" +
            "as startDateTime, conversation.video_calls.end_date_time as endDateTime," +
            " conversation.video_calls.video_call_status\n" +
            "as videoCallStatus, conversation.video_calls.duration from conversation.video_calls\n" +
            "join users.mentees u_mentee on conversation.video_calls.mentee_id = u_mentee.id\n" +
            "join users.users on u_mentee.user_id = users.id\n" +
            "where conversation.video_calls.mentor_id = ?1")
    List<HistoryProjection> seeHistories(String mentorId, Pageable pageable);

    @Query(nativeQuery = true,value = "select users.photo_path as picture,video_calls.video_link as videoLink, video_calls.start_date_time\n" +
            "                        as startDateTime, video_calls.end_date_time as endDateTime, video_calls.video_call_status\n" +
            "                        as videoCallStatus, video_calls.duration from conversation.video_calls\n" +
            "join users.mentors m on video_calls.mentor_id = m.id\n" +
            "join users.users on m.user_id = users.id\n" +
            "where conversation.video_calls.id = ?1")
    List<HistoryProjection> seeMenteeVideoHistories(String id, Pageable pageable);
}
