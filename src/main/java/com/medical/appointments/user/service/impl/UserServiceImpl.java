package com.medical.appointments.user.service.impl;

import com.medical.appointments.user.dto.UserDTO;
import com.medical.appointments.user.model.User;
import com.medical.appointments.user.repository.UserRepository;
import com.medical.appointments.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()));
        }
        return dto;
    }

    private User toEntity(UserDTO dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        // Roles should be set in controller/service using their repositories
        return entity;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = toEntity(userDTO);
        user.setEnabled(true);
        return toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) return null;
        User user = optional.get();
        BeanUtils.copyProperties(userDTO, user, "id");
        return toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(this::toDTO).orElse(null);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toDTO).orElse(null);
    }
}
