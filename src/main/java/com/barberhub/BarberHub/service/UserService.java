package com.barberhub.BarberHub.service;

import com.barberhub.BarberHub.dto.UserDTO;
import com.barberhub.BarberHub.dto.UserRequestDTO;
import com.barberhub.BarberHub.model.UserModel;
import com.barberhub.BarberHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    public String createUser(UserRequestDTO user) {
        UserModel userModel = new UserModel();
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userRepository.save(userModel);
        return "Usuario criado com sucesso.";
    }

    public UserDTO getUserById(Long id) {
        UserModel searchUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO(searchUser);
        return userDTO;
    }
}
