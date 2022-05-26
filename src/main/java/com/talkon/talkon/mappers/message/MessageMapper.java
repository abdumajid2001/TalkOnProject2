package com.talkon.talkon.mappers.message;

import com.talkon.talkon.dtos.message.MessageCreateDto;
import com.talkon.talkon.dtos.message.MessageDto;
import com.talkon.talkon.dtos.message.MessageUpdateDto;
import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.mappers.base.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageMapper extends AbstractMapper<Message, MessageDto, MessageCreateDto, MessageUpdateDto> {

    Message fromCreateDto(MessageCreateDto dto);

    Message fromUpdateDto(MessageUpdateDto dto);
}
