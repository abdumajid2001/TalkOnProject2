package com.talkon.talkon.services.message;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.message.MessageCreateDto;
import com.talkon.talkon.dtos.message.MessageDto;
import com.talkon.talkon.dtos.message.MessageIdsDto;
import com.talkon.talkon.dtos.message.MessageUpdateDto;
import com.talkon.talkon.entities.conversation.chat.message.Message;
import com.talkon.talkon.mappers.message.MessageMapper;
import com.talkon.talkon.projections.message.MessageProjection;
import com.talkon.talkon.repositories.message.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;
    MessageMapper messageMapper;

    @Override
    public String create(MessageCreateDto dto) {
        return null;
    }

    @Override
    public void delete(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void update(MessageUpdateDto dto) {
        Optional<Message> byId = messageRepository.findById(dto.getId());
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("message not found");
        }
        Message message = byId.get();
        message.setBody(dto.getBody());
        messageRepository.save(message);
    }

    @Override
    public MessageDto get(String id) {
        return null;
    }

    @Override
    public List<MessageDto> getAll(BaseGenericCriteria criteria) {
        return null;
    }

    @Override
    public HttpEntity<?> getAllMessages(String chatId, int size, int page) {
        List<MessageProjection> allMessages = messageRepository.getAllMessages(chatId, size, page);
        return ResponseEntity.ok(allMessages);
    }

    @Override
    public MessageProjection sendMessageToUser(MessageCreateDto messageCreateDto) {
        Message message = messageMapper.fromCreateDto(messageCreateDto);
        message.setUpdatedAt(null);
        message.setCreatedAt(LocalDateTime.now());
        message.setDeleted(false);
        message.setStatus((short) 0);
        message.setCreatedBy(messageCreateDto.getFromId());
        messageRepository.save(message);
        return messageRepository.getMessageById(message.getId());
    }

    @Override
    public HttpEntity<?> makeMessageAsRead(String messageId) {
        messageRepository.makeMessageAsRead(messageId);
        return ResponseEntity.ok("success");
    }

    @Override
    public HttpEntity<?> makeMessagesAsRead(MessageIdsDto messageIds) {
        List<Message> allById = messageRepository.findAllById(messageIds.getIds());
        allById.forEach(message -> message.setRead(true));
        messageRepository.saveAll(allById);
        return ResponseEntity.ok("success");
    }

}
