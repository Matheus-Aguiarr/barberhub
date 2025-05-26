package com.barberhub.BarberHub.service;

import com.barberhub.BarberHub.dto.UserDTO;
import com.barberhub.BarberHub.dto.UserRequestDTO;
import com.barberhub.BarberHub.exceptions.UserNotFoundException;
import com.barberhub.BarberHub.model.UserModel;
import com.barberhub.BarberHub.repository.UserRepository;
import org.apache.catalina.User;
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
        UserModel searchUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        UserDTO userDTO = new UserDTO(searchUser);
        return userDTO;
    }

    public UserDTO getUserByEmail(String email) {
        UserModel searchUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new UserDTO(searchUser);
    }

    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "User deleted with success";
    }

    public UserDTO updateUserById(UserRequestDTO userUpdated, Long id) {
        UserModel searchUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        searchUser.setName(userUpdated.getName());
        searchUser.setEmail(userUpdated.getEmail());
        searchUser.setPhone(userUpdated.getPhone());
        userRepository.save(searchUser);
        return new UserDTO(searchUser);
    }
}
