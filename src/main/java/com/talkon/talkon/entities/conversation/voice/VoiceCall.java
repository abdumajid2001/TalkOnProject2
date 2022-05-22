package com.talkon.talkon.entities.conversation.voice;

import com.talkon.talkon.entities.base.Auditable;
import com.talkon.talkon.entities.user.members.Mentee;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "conversation")
@Entity(name = "voice_calls")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoiceCall extends Auditable {

    Integer duration;

    @ManyToOne
    @JoinColumn(name = "mentee1_id")
    Mentee mentee1;

    @ManyToOne
    @JoinColumn(name = "mentee2_id")
    Mentee mentee2;
}
