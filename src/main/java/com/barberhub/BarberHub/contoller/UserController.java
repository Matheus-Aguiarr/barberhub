package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.UserRequestDTO;
import com.barberhub.BarberHub.model.UserModel;
import com.barberhub.BarberHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ToDo: Testar as requisicoes, criar dtos, finalizar o CRUD dos users.

@RestController
public class UserController {

    @Autowired
    public final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO user) {
        String userCreated = userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }

}
