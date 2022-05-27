package com.talkon.talkon.services.chat;

import com.talkon.talkon.criteria.base.BaseGenericCriteria;
import com.talkon.talkon.dtos.chat.ChatCreateDto;
import com.talkon.talkon.dtos.chat.ChatDto;
import com.talkon.talkon.dtos.chat.ChatUpdateDto;
import com.talkon.talkon.entities.conversation.chat.Chat;
import com.talkon.talkon.entities.user.members.Mentee;
import com.talkon.talkon.entities.user.members.Mentor;
import com.talkon.talkon.projections.chat.ChatProjection;
import com.talkon.talkon.repositories.chat.ChatRepository;
import com.talkon.talkon.repositories.message.MessageRepository;
import com.talkon.talkon.repositories.user.member.mentee.MenteeRepository;
import com.talkon.talkon.repositories.user.member.mentor.MentorRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;
    UserRepository userRepository;
    MentorRepository mentorRepository;
    MenteeRepository menteeRepository;
    MessageRepository messageRepository;


    @Override
    public String create(ChatCreateDto dto) {
        try {
            Chat chat = new Chat();
            if (menteeRepository.existsById(dto.getFromId())) {
                chat.setMentee(menteeRepository.getById(dto.getFromId()));
                if(mentorRepository.existsById(dto.getCurrentUserId())) {
                    Mentor mentor = mentorRepository.getById(dto.getCurrentUserId());
                    chat.setMentor(mentor);
                    chat.setCreatedBy(mentor.getUser().getId());
                }
            }else if(mentorRepository.existsById(dto.getCurrentUserId())){
                chat.setMentor(mentorRepository.getById(dto.getCurrentUserId()));
                if(menteeRepository.existsById(dto.getCurrentUserId())){
                    Mentee mentee = menteeRepository.getById(dto.getCurrentUserId());
                    chat.setMentee(mentee);
                    chat.setCreatedBy(mentee.getUser().getId());
                }
            }else return "teacher or stuent not found";

            if(chat.getMentee()!=null && chat.getMentor()!=null){
                chat.setCreatedAt(LocalDateTime.now());
                chat.setStatus((short)0);
                chatRepository.save(chat);
                return chat.getId();
            }
            return "the current user is not allowed to chat";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void delete(String id) {
        chatRepository.deleteById(id);
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


    @Override
    public HttpEntity<?> getAllContacts(String currentId) {
        List<ChatProjection> allContacts = chatRepository.getAllContacts(currentId);
        return ResponseEntity.ok(allContacts);
    }




}
