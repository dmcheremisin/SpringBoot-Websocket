package com.example.springboot.websocket.client;

import com.example.springboot.websocket.model.InputMessage;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class ClientStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("New session established : " + session.getSessionId());
        session.subscribe("/topic/messages", this);
        System.out.println("Subscribed to /topic/messages");
        session.send("/app/discussion/greeting", new InputMessage("Client", "Bonjour"));
        System.out.println("Greeting sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
            Throwable exception) {
        System.err.println("Got an exception: " + exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return InputMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        InputMessage message = (InputMessage)payload;
        System.out.println(String.format("Received >>> from: %s message: %s", message.getFrom(), message.getText()));
    }

}