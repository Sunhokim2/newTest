package com.example.newTest.service;

import com.example.newTest.Repository.ChatRoomRepository;
import com.example.newTest.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllRooms(){
        return chatRoomRepository.findAll();
    }

    public void createRoom(String name, Long ownerId){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setOwnerId(ownerId);
        chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findRoomById(Long roomId){
        return chatRoomRepository.findById(roomId).orElse(null);
    }

    public void deleteRoomById(Long roomId){
        chatRoomRepository.deleteById(roomId);
    }
}
