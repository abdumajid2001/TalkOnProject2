package com.talkon.talkon.config.websocket;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.services.chat.ChatService;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractController<ChatService> implements WebSocketMessageBrokerConfigurer{


    protected WebSocketConfig(ChatService service) {
        super(service);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(PATH+"/chat");
        config.setApplicationDestinationPrefixes(PATH);   //"/app"
        config.setUserDestinationPrefix(PATH+"/chat");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(PATH+"/ws").withSockJS();
    }
}
