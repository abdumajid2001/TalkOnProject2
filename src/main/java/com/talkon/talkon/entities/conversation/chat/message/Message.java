package com.talkon.talkon.entities.conversation.chat.message;

import com.talkon.talkon.entities.base.Auditable;
import javax.persistence.*;

import com.talkon.talkon.entities.conversation.chat.Chat;
import com.talkon.talkon.entities.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "conversation")
@Entity(name = "messages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message extends Auditable {

    @Column(columnDefinition = "text")
    String body;

    String filePath;

    Integer parent;

    boolean isRead;

    @ManyToOne
    User from;

    @ManyToOne
    Chat chat;
}