package com.talkon.talkon.listeners;


import com.talkon.talkon.projections.UserContactStatusProjection;
import com.talkon.talkon.repositories.chat.ChatRepository;
import com.talkon.talkon.repositories.user.user.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Component
@AllArgsConstructor
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    ChatRepository chatRepository;
    UserRepository userRepository;
    SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        String username = event.getUser().getName();
        logger.info(String.format("user connect %s", username));
        sendUserStatus(username, true, false);

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String username = event.getUser().getName();
        logger.warn(String.format("user disconnect %s", username));
        sendUserStatus(username, false, true);


    }


    private void sendUserStatus(String username, boolean status, boolean disconnected) {
        if (disconnected) {
            userRepository.changeStatusAndLastSeen(username, status);
        } else {
            userRepository.changeStatus(username, status);
        }
        UserContactStatusProjection userContactStatus = userRepository.getUserContactStatus(username);
        List<String> allContactId = userRepository.getAllContactId(username);
        for (String s : allContactId) {
            messagingTemplate.convertAndSendToUser(s, "/contact/status", userContactStatus);
        }
    }
}
