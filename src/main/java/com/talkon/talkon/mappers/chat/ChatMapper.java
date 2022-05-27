package com.talkon.talkon.mappers.chat;

import com.talkon.talkon.dtos.chat.ChatCreateDto;
import com.talkon.talkon.dtos.chat.ChatDto;
import com.talkon.talkon.dtos.chat.ChatUpdateDto;
import com.talkon.talkon.entities.conversation.chat.Chat;
import com.talkon.talkon.mappers.base.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChatMapper extends AbstractMapper<Chat, ChatDto, ChatCreateDto, ChatUpdateDto> {

    Chat fromCreateDto(ChatCreateDto dto, @MappingTarget Chat chat);


}
