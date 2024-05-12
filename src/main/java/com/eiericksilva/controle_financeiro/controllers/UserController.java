package com.eiericksilva.controle_financeiro.controllers;

import com.eiericksilva.controle_financeiro.dto.UserDTO;
import com.eiericksilva.controle_financeiro.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /*CREATE*/
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    /*READ*/
    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public UserDTO findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    /*UPDATE*/
    @PutMapping(value = "/{userId}")
    public UserDTO update(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    /*DELETE*/
    @DeleteMapping(value = "/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
