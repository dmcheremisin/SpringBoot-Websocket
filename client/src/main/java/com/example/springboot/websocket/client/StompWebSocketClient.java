package com.example.springboot.websocket.client;

import com.example.springboot.websocket.model.InputMessage;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class StompWebSocketClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
        List<Transport> transports = List.of(new WebSocketTransport(simpleWebSocketClient));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new ClientStompSessionHandler();
        StompSession stompSession = stompClient.connect("ws://localhost:8080/chat", sessionHandler).get();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String from = scanner.nextLine();
            String text = scanner.nextLine();
            stompSession.send("/app/discussion/client", new InputMessage(from, text));
        }
    }
}