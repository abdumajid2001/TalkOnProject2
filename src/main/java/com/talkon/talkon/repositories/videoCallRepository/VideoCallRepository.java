package com.talkon.talkon.repositories.videoCallRepository;

import com.talkon.talkon.entities.conversation.video.VideoCall;
import com.talkon.talkon.projections.videoCallRepository.VideoCAllProjection;
import com.talkon.talkon.repositories.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoCallRepository extends AbstractRepository<VideoCall, String> {


    @Query(nativeQuery = true, value = "select * from users.users")
    List<VideoCAllProjection> getVideoCalls(String id);

}
