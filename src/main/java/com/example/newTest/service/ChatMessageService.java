package com.example.newTest.service;

import com.example.newTest.Repository.ChatMessageRepository;
import com.example.newTest.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getAllChatMessages(Long roomId) {
        return chatMessageRepository.findByChatRoomIdOrderByTimestamp(roomId);
    }

    public void saveMessage(ChatMessage message){
        chatMessageRepository.save(message);
    }

}
