package com.example.newTest.service;

import com.example.newTest.Repository.UserRepository;
import com.example.newTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean validateUser(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser != null && foundUser.getPassword().equals(user.getPassword());
    }

//    /**비밀번호 암호화**/
//    public void register(User user) {
//        //비번 평문저장 (BCryptPasswordEncoder가 안됨. 인터넷에 나와있는대로 gradle에 implementation한상탠데...
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//    /**사용자 조회 **/
//    public ResponseEntity<String> login(User user){
//        User foundUser = userRepository.findByUsername(user.getUsername());
//        if(foundUser != null && foundUser.getPassword().equals(user.getPassword())){
//            return ResponseEntity.ok("Login : " + user.getUsername());
//        }else{
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }
}
