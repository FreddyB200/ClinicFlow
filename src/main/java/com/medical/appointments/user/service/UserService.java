package com.medical.appointments.user.service;

import com.medical.appointments.user.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserByUsername(String username);
    UserDTO getUserByEmail(String email);
}
