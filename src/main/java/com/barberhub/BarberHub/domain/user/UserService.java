package com.barberhub.BarberHub.domain.user;

import com.barberhub.BarberHub.dto.user.UserDTO;
import com.barberhub.BarberHub.dto.user.UserRequestDTO;
import com.barberhub.BarberHub.infra.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
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
        return  new UserDTO(searchUser);
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
