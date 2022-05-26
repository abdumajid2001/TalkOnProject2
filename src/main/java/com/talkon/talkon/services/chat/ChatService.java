package com.talkon.talkon.services.chat;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.chat.ChatCreateDto;
import com.talkon.talkon.dtos.chat.ChatDto;
import com.talkon.talkon.dtos.chat.ChatUpdateDto;
import com.talkon.talkon.dtos.message.MessageCreateDto;
import com.talkon.talkon.projections.message.MessageProjection;
import com.talkon.talkon.services.base.GenericCrudService;
import org.springframework.http.HttpEntity;

public interface ChatService extends GenericCrudService<ChatDto, ChatCreateDto, ChatUpdateDto, String, BaseGenericCriteria> {

    HttpEntity<?> getAllContacts(String currentId);


}
