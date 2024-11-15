package com.example.newTest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /** 방장의 사용자 ID **/
    private Long ownerId;
    private int maxParticipants = 5; //최대인원5명

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatMessage> messages = new ArrayList<>();

    /** 여기 아래 있는 것은 안씁니다. **/
    @ElementCollection
    private Set<Long> participants = new HashSet<>();

    public void addUser(Long userId) {
        participants.add(userId);
    }

    public void removeUser(Long userId) {
        participants.remove(userId);
    }
}
