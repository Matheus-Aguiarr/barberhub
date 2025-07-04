package com.barberhub.BarberHub.contoller;
import com.barberhub.BarberHub.dto.user.UserDTO;
import com.barberhub.BarberHub.dto.user.UserRequestDTO;
import com.barberhub.BarberHub.domain.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
       return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users/email/{userEmail}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String userEmail) {
        UserDTO dto = userService.getUserByEmail(userEmail);
        return ResponseEntity.ok(dto);
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
