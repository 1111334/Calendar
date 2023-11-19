package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//ctrl alt o (remove)
//import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

   // public UserController(UserService userService) {
   //     this.userService = userService;
   // }

    @PostMapping(value = "/insert-user")
    public String insertUser(@RequestBody User user) {
        userService.insertUser(user);
        return "insert new user";
    }

    @GetMapping(value = "/view")
    public List<User> viewUser() {
        return userService.viewUser();
    }

    @PutMapping(value = "/put")
    public ResponseEntity<String> putUser(@RequestBody User updatedUser) {
        userService.putUser(updatedUser);
        try {
            return ResponseEntity.ok("Utente aggiornato con successo");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Utente non aggiornato");
        }
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok("Utente eliminato con successo");
    }

}
