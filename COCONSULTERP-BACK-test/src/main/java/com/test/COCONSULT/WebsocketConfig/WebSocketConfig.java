package com.test.COCONSULT.WebsocketConfig;

import com.test.COCONSULT.Controllers.ChatWSController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ChatWSController.class);

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable broker for general chat topics
        //registry.enableSimpleBroker("/topic");

        // Enable broker for group chat rooms
        registry.enableSimpleBroker("/topic/groupChat");

        // Set application destination prefix for handling client messages
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register WebSocket endpoint for clients to connect
        registry.addEndpoint("/ws").setAllowedOriginPatterns("http://localhost:4200");
        logger.info("WebSocket endpoint registered");
    }
}
