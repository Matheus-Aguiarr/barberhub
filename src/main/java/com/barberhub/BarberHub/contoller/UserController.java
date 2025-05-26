package com.barberhub.BarberHub.contoller;

import com.barberhub.BarberHub.dto.UserDTO;
import com.barberhub.BarberHub.dto.UserRequestDTO;
import com.barberhub.BarberHub.model.UserModel;
import com.barberhub.BarberHub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
       return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDTO user) {
        String userCreated = userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserRequestDTO userUpdated, @PathVariable Long id) {
        UserDTO newUser = userService.updateUserById(userUpdated, id);
        return ResponseEntity.ok(newUser);
    }

}
