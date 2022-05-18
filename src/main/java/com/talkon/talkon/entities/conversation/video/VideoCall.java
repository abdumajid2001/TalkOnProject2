package com.talkon.talkon.entities.conversation.video;

import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.*;

import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.enums.VideoCallStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "conversation")
@Entity(name = "video_calls")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoCall extends Auditable {


    @Column(nullable = false)
    String videoLink;

    LocalDateTime startDateTime;

    LocalDateTime endDateTime;

    Integer duration;

    @Enumerated(EnumType.STRING)
    VideoCallStatus videoCallStatus;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    Mentee mentee;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    Mentor mentor;
}
