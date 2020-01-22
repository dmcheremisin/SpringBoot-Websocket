package com.example.springboot.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputMessage
{
    private String from;
    private String text;
    private String topic;
    private Date time;

}
