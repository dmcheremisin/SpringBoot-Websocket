package com.example.springboot.websocket.controller;

import com.example.springboot.websocket.model.InputMessage;
import com.example.springboot.websocket.model.OutputMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    @MessageMapping("/discussion/{topic}") // end-point to handle incoming messages
    @SendTo("/topic/messages") // end-point to propagate messages
    public OutputMessage send(@DestinationVariable("topic") String topic, InputMessage inputMessage) throws Exception {
        return new OutputMessage(inputMessage.getFrom(), inputMessage.getText(), topic, new Date());
    }

}