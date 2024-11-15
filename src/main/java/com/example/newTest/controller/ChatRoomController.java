package com.example.newTest.controller;

import com.example.newTest.model.ChatRoom;
import com.example.newTest.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    /** 채팅방 목록 조회 **/
    @GetMapping
    public List<ChatRoom> getAllChatRooms(){
        return chatRoomService.findAllRooms();
    }

    /** 채팅방 생성 **/
    @PostMapping
    public ResponseEntity<String> createRoom(@RequestParam String name, @RequestParam Long userId){
        chatRoomService.createRoom(name, userId);
        return ResponseEntity.ok("채팅방 생성 성공");
    }

    /** 채팅방 입장 **/
    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoom> getRoom(@PathVariable Long roomId){
        ChatRoom room = chatRoomService.findRoomById(roomId);
        if (room != null){
            return ResponseEntity.ok(room);
        }
        return ResponseEntity.status(404).body(null);
    }

    /** 채팅방 삭제 **/
    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId){
        chatRoomService.deleteRoomById(roomId);
        return ResponseEntity.ok("채팅방 삭제 성공");
    }
}
