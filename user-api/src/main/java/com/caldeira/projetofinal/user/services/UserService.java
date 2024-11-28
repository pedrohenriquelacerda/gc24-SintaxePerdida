package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRequestValidator userRequestValidator;

    public UserService(UserRepository userRepository, UserRequestValidator userRequestValidator) {
        this.userRepository = userRepository;
        this.userRequestValidator = userRequestValidator;
    }

    public List<UserResponseModel> getAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> new UserResponseModel(userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getCreationDate()))
                .collect(Collectors.toList());
    }
}
