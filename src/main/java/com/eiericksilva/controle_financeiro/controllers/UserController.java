package com.eiericksilva.controle_financeiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.eiericksilva.controle_financeiro.dto.UserDTO;
import com.eiericksilva.controle_financeiro.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public UserDTO findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping(value = "/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping(value = "/{userId}")
    public UserDTO update(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }


}
