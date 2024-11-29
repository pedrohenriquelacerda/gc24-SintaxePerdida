package com.caldeira.projetofinal.user.controllers;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @InjectMocks
    UserController userController;
    @Mock
    private UserService userServiceMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private UserResponseModel UserResponseModelMock;
    @Mock
    private UserRequestModel UserRequestModelMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void UsersExist_WhenGetAllIsCalled_ThenReturnAllUsersWithSuccess() {
        List<UserResponseModel> mockUsers = Arrays.asList(
                new UserResponseModel(UUID.fromString("07e7b6bb-3976-4228-8a57-331b77e30f1f")
                        , "John Doe", "john@example.com", LocalDateTime.MIN),
                new UserResponseModel(UUID.fromString("d68217eb-4396-417d-902d-35cc50f13599")
                        , "Jane Smith", "jane@example.com", LocalDateTime.MIN)
        );

        when(userServiceMock.getAll()).thenReturn(mockUsers);

        ResponseEntity<List<UserResponseModel>> resultado = userController.getAll();

        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode()); // Verifica o status
        assertEquals(mockUsers, resultado.getBody());          // Verifica o corpo da resposta
        assertEquals(2, resultado.getBody().size());
    }

    @Test
    void UserExists_WhenGetByIdIsCalled_ThenReturnUserWithSuccess() {
            UUID mockId = UUID.randomUUID();

            UserResponseModel mockUser = new UserResponseModel(UUID.fromString("dffe8d8f-5845-4558-9596-320009d3d7d1"),
                    "John Doe", "john@example.com", LocalDateTime.MIN);

            doReturn(mockUser).when(userServiceMock).getById(mockId);

            ResponseEntity<UserResponseModel> response = userController.getById(mockId);

            assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica o status
            assertEquals(mockUser, response.getBody());            // Verifica o corpo da resposta
        }

    @Test
    void create_WhenValidModelIsProvided_ThenReturnCreatedUserWithSuccess() {
        UserRequestModel mockRequest = new UserRequestModel("John Doe", "john@example.com");
        UserResponseModel mockResponse = new UserResponseModel(UUID.randomUUID(), "John Doe", "john@example.com", LocalDateTime.now());

        doReturn(mockResponse).when(userServiceMock).create(mockRequest);

        ResponseEntity<UserResponseModel> response = userController.create(mockRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Verifica o status
        assertEquals(mockResponse, response.getBody());            // Verifica o corpo da resposta
    }

    @Test
    void update_WhenValidIdAndModelAreProvided_ThenReturnUpdatedUserWithSuccess() {
        UUID mockId = UUID.randomUUID();
        UserRequestModel mockRequest = new UserRequestModel("Jane Doe", "jane@example.com");
        UserResponseModel mockResponse = new UserResponseModel(mockId, "Jane Doe", "jane@example.com", LocalDateTime.now());

        doReturn(mockResponse).when(userServiceMock).update(mockId, mockRequest);

        ResponseEntity<UserResponseModel> response = userController.update(mockId, mockRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica o status
        assertEquals(mockResponse, response.getBody());        // Verifica o corpo da resposta
    }

    @Test
    void deleteById_WhenValidIdIsProvided_ThenReturnStatusOk() {
        UUID mockId = UUID.randomUUID();

        doReturn(true).when(userServiceMock).deleteById(mockId);

        ResponseEntity<UserResponseModel> response = userController.deleteById(mockId);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verifica o status
    }

    @Test
    void deleteById_WhenInvalidIdIsProvided_ThenReturnStatusNotFound() {
        UUID mockId = UUID.randomUUID();

        doThrow(new RuntimeException("User not found")).when(userServiceMock).deleteById(mockId);

        ResponseEntity<UserResponseModel> response = userController.deleteById(mockId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Verifica o status
    }
}