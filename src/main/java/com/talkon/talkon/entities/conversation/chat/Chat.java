package com.talkon.talkon.entities.conversation.chat;

import com.talkon.talkon.entities.base.Auditable;

import javax.persistence.*;

import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chats")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "mentor_id", "mentee_id" }) },schema = "conversation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat extends Auditable {

    @ManyToOne
    Mentor mentor;

    @ManyToOne
    Mentee mentee;


    @OneToMany(cascade = CascadeType.ALL)
    List<Message> messages = new ArrayList<>();
}
