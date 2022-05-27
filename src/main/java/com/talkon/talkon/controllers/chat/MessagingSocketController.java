package com.talkon.talkon.controllers.chat;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.dtos.message.MessageCreateDto;
import com.talkon.talkon.projections.message.MessageProjection;
import com.talkon.talkon.services.message.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessagingSocketController extends AbstractController<MessageService> {

    protected MessagingSocketController(MessageService service, SimpMessagingTemplate template) {
        super(service);
        this.template = template;
    }


    SimpMessagingTemplate template;

    @MessageMapping("/messages")
    public void sendMessage(@Payload MessageCreateDto messageCreateDto){
        MessageProjection messageProjection = service.sendMessageToUser(messageCreateDto);
        template.convertAndSendToUser(messageCreateDto.getChatId(),"/messages",messageProjection);
    }

}
