package com.example.courseologybackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserController {

    @Autowired // does all the mapping for us
    com.example.courseologybackend.UserRepository userRepository;

    @GetMapping("/user")
    public String getUser () {
        return "getting User";
    }

    @GetMapping("/users")
    public List<User> getUsers () {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public String addUser (@RequestBody User text) {
        this.userRepository.save(text);
        System.out.println("Added " + text);
        return "User saved okay";
    }

    @DeleteMapping("/users/{userId}")
    @Transactional
    public String deleteUser(@PathVariable int userId) {
        System.out.println("User = " + userId);
        int deleted = this.userRepository.deleteUserByUserId(userId);
        if (deleted > 0) {
            return "User removed okay";
        }
        return "User Id doesn't exist";
    }
}
