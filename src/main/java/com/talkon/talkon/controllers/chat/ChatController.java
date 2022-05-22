package com.talkon.talkon.controllers.chat;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.dtos.chat.ChatCreateDto;
import com.talkon.talkon.services.chat.ChatService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController extends AbstractController<ChatService> {

    protected ChatController(ChatService service) {
        super(service);
    }

    /**
     * create chat for conversation mentor and mentee
     * @param chatCreateDto:  from id
     * @return saved chat id
     *  url: api/v1/chat
     */
    @PostMapping(PATH+"/chat")
    public HttpEntity<?> saveChatRoom(@RequestBody ChatCreateDto chatCreateDto){
        String currentUserId = "";
        chatCreateDto.setCurrentUserId(currentUserId);
        return ResponseEntity.ok(service.create(chatCreateDto));
    }




}
