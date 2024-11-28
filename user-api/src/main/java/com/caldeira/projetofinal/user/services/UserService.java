package com.caldeira.projetofinal.user.services;

import com.caldeira.projetofinal.user.entities.UserEntity;
import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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

    public UserResponseModel getById(UUID id) {
        return userRepository.findById(id)
                .map(userEntity -> new UserResponseModel(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getCreationDate()))
                .orElse(null);
    }

    public UserResponseModel create(UserRequestModel requestModel) {
        userRequestValidator.validate(requestModel);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setFirstName(requestModel.getFirstName());
        userEntity.setLastName(requestModel.getLastName());
        userEntity.setCreationDate(LocalDateTime.now());

        UserEntity savedUser = userRepository.save(userEntity);

        return new UserResponseModel(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getCreationDate()
        );
    }

}
