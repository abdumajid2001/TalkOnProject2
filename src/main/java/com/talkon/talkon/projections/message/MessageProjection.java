package com.talkon.talkon.projections.message;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface MessageProjection {
    String getId();
    String getBody();
    LocalDateTime getCreatedAt();
    boolean getIsRead();
    String getSenderId();
    String getFileLink();
    LocalDateTime getUpdatedAt();
    @Value("#{target.parent==null? null : @messageRepository.getParentMessage(target.parent)}")
    ParentMessageProjection getParent();
}
