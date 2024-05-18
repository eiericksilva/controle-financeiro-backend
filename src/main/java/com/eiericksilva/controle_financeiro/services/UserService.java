package com.eiericksilva.controle_financeiro.services;

import com.eiericksilva.controle_financeiro.dto.UserDTO;
import com.eiericksilva.controle_financeiro.dto.mapper.UserMapper;
import com.eiericksilva.controle_financeiro.entities.User;
import com.eiericksilva.controle_financeiro.exceptions.ResourceNotFoundException;
import com.eiericksilva.controle_financeiro.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    /*CREATE*/
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    /*READ*/
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> userMapper.toDTO(u))
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId));

        return userMapper.toDTO(user);
    }

    /*UPDATE*/
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User userFound = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId));

        BeanUtils.copyProperties(userDTO, userFound, "id");

        User updatedUser = userRepository.save(userFound);

        return userMapper.toDTO(updatedUser);
    }

    /*DELETE*/
    public void deleteUser(Long userId) {
        User userToRemove = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId));
        userRepository.delete(userToRemove);
    }

}
