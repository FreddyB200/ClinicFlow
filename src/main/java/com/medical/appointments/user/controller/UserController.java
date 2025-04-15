package com.medical.appointments.user.controller;

import com.medical.appointments.user.dto.UserDTO;
import com.medical.appointments.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management endpoints")
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    public String getAllUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    @Operation(summary = "Show create user form")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "users/form";
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public String createUser(@ModelAttribute UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Show edit user form")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/form";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a user")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete a user")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    // REST API endpoints
    @RestController
    @RequestMapping("/api/users")
    @Tag(name = "User API", description = "User API endpoints")
    public static class UserRestController {
        private final UserService userService;
        public UserRestController(UserService userService) { this.userService = userService; }

        @GetMapping
        @Operation(summary = "Get all users (API)")
        public List<UserDTO> getAllUsers() {
            return userService.getAllUsers();
        }

        @GetMapping("/{id}")
        @Operation(summary = "Get user by ID (API)")
        public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
            UserDTO user = userService.getUserById(id);
            return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
        }

        @PostMapping
        @Operation(summary = "Create user (API)")
        public UserDTO createUser(@RequestBody UserDTO userDTO) {
            return userService.createUser(userDTO);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update user (API)")
        public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
            return userService.updateUser(id, userDTO);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete user (API)")
        public void deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
        }
    }
}
