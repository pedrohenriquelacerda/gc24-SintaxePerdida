package com.caldeira.projetofinal.user.controllers;

import com.caldeira.projetofinal.user.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RestController
@RequestMapping("/user")
public class UserController {
    public final UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseModel>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseModel> getById(UUID id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseModel> create(@RequestBody UserRequestModel model) {
        UserResponseModel createdUser = userService.createUser(model);

        return new ResponseEntity<UserResponseModel>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseModel> update(@PathVariable UUID id, @RequestBody UserRequestModel model) {
        UserResponseModel updatedUser = userService.updateUser(id, model);

        return new ResponseEntity<UserResponseModel>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<UserResponseModel> deleteById(@PathVariable UUID id) {
        try {
            UserResponseModel deletedUser = userService.deleteById(id);
            return new ResponseEntity<UserResponseModel>(deletedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<UserResponseModel>(HttpStatus.NOT_FOUND);
        }
    }
}
