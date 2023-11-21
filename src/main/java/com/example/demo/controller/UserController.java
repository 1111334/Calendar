package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(value = "/api/users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        if (user != null) {
            // Se l'utente è stato trovato, convertirlo in un DTO e restituirlo nella risposta
            UserDTO userDTO = UserDTO.fromUser(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            // Se l'utente non è stato trovato, restituire una risposta 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/put")
    public ResponseEntity<String> putUser(@RequestBody User updatedUser) {
        userService.putUser(updatedUser);
        try {
            return ResponseEntity.ok("Utente aggiornato con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Utente non aggiornato");
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok("Utente eliminato con successo");
    }

    User user = new User();
    UserDTO userDTO = UserDTO.fromUser(user);


    //User user =  new User();
    //UserDTO userDTO = UserDTO.toUser(user);


}
