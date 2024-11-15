package com.example.newTest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Getter
@Setter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChatRoom chatRoom;

    private String sender;
    private String content;
    private LocalDateTime timestamp;
}
