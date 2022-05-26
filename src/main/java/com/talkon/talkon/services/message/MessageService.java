package com.talkon.talkon.services.message;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.message.MessageCreateDto;
import com.talkon.talkon.dtos.message.MessageDto;
import com.talkon.talkon.dtos.message.MessageIdsDto;
import com.talkon.talkon.dtos.message.MessageUpdateDto;
import com.talkon.talkon.projections.message.MessageProjection;
import com.talkon.talkon.services.base.GenericCrudService;
import org.springframework.http.HttpEntity;

public interface MessageService extends GenericCrudService<MessageDto, MessageCreateDto, MessageUpdateDto, String, BaseGenericCriteria> {

    HttpEntity<?> getAllMessages(String chatId, int size, int page);

    MessageProjection sendMessageToUser(MessageCreateDto messageCreateDto);

    HttpEntity<?> makeMessageAsRead(String messageId);

    HttpEntity<?> makeMessagesAsRead(MessageIdsDto messageIds);

}
