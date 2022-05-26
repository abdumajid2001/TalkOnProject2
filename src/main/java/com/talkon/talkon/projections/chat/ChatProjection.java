package com.talkon.talkon.projections.chat;

import com.talkon.talkon.projections.message.LastMessageProjection;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface ChatProjection {

    String getFirstName();
    String getLastName();
    String getChatId();
    String getUserId();
    LocalDateTime getLastSeen();
    boolean getIsOnline();
    int getNewMessageCount();
    String getImageLink();

    @Value("#{@messageRepository.getLastMessage(target.chatId)}")
    LastMessageProjection getLastMessage();
}
