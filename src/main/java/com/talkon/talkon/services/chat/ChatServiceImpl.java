package com.talkon.talkon.services.chat;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.chat.ChatCreateDto;
import com.talkon.talkon.dtos.chat.ChatDto;
import com.talkon.talkon.dtos.chat.ChatUpdateDto;
import com.talkon.talkon.entities.conversation.chat.Chat;
import com.talkon.talkon.repositories.chat.ChatRepository;
import com.talkon.talkon.repositories.mentor.MentorRepository;
import com.talkon.talkon.repositories.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;
    UserRepository userRepository;
    MentorRepository mentorRepository;


    @Override
    public String create(ChatCreateDto dto) {
        try {

            Chat chat = new Chat();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(ChatUpdateDto dto) {

    }

    @Override
    public ChatDto get(String id) {
        return null;
    }

    @Override
    public List<ChatDto> getAll(BaseGenericCriteria criteria) {
        return null;
    }
}
