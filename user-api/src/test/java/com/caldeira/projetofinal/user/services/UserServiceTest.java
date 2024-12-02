package com.caldeira.projetofinal.user.services;

import static org.junit.jupiter.api.Assertions.*;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import com.caldeira.projetofinal.user.models.response.UserResponseModel;
import com.caldeira.projetofinal.user.repositories.UserRepository;
import com.caldeira.projetofinal.user.validators.UserRequestValidator;
import com.caldeira.projetofinal.user.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;



class UserServiceTest {

    @InjectMocks
    private UserService userServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserRequestValidator userRequestValidatorMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private UserEntity createUserEntity(UUID id, String firstName, String lastName) {
        UserEntity userEntity = new UserEntity(UUID.randomUUID(), "John", "Doe", LocalDateTime.now());
        userEntity.setId(id);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setCreationDate(LocalDateTime.now());
        return userEntity;
    }

    @Test
    void getAll_WhenUsersExist_ThenReturnListOfUserResponseModels() {
        List<UserEntity> mockEntities = Arrays.asList(
                createUserEntity(UUID.randomUUID(), "John", "Doe"),
                createUserEntity(UUID.randomUUID(), "Jane", "Smith")
        );

        when(userRepositoryMock.findAll()).thenReturn(mockEntities);

        List<UserResponseModel> response = userServiceMock.getAll();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals("John", response.get(0).getFirstname());
        assertEquals("Jane", response.get(1).getFirstname());
    }

    @Test
    void getById_WhenUserExists_ThenReturnUserResponseModel() {
        UUID mockId = UUID.randomUUID();
        UserEntity mockEntity = createUserEntity(mockId, "John", "Doe");

        when(userRepositoryMock.findById(mockId)).thenReturn(Optional.of(mockEntity));

        UserResponseModel response = userServiceMock.getById(mockId);

        assertNotNull(response);
        assertEquals(mockId, response.getId());
        assertEquals("John", response.getFirstname());
    }

    @Test
    void getById_WhenUserDoesNotExist_ThenReturnNull() {
        UUID mockId = UUID.randomUUID();

        when(userRepositoryMock.findById(mockId)).thenReturn(Optional.empty());

        UserResponseModel response = userServiceMock.getById(mockId);

        assertNull(response);
    }

    @Test
    void create_WhenValidRequestIsProvided_ThenReturnCreatedUserResponseModel() {
        UserRequestModel mockRequest = new UserRequestModel("John", "Doe");
        UUID mockId = UUID.randomUUID();

        UserEntity mockEntity = createUserEntity(mockId, "John", "Doe");

        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(mockEntity);
        doNothing().when(userRequestValidatorMock).validate(mockRequest);

        UserResponseModel response = userServiceMock.create(mockRequest);

        assertNotNull(response);
        assertEquals(mockId, response.getId());
        assertEquals("John", response.getFirstname());
    }

    @Test
    void update_WhenUserExistsAndRequestIsValid_ThenReturnUpdatedUserResponseModel() {
        UUID mockId = UUID.randomUUID();
        UserRequestModel mockRequest = new UserRequestModel("Jane", "Smith");

        // Criando o mock do UserEntity
        UserEntity mockEntity = createUserEntity(mockId, "John", "Doe");
        UserEntity updatedEntity = createUserEntity(mockId, "Jane", "Smith");

        when(userRepositoryMock.findById(mockId)).thenReturn(Optional.of(mockEntity));
        doNothing().when(userRequestValidatorMock).validate(mockRequest);
        doReturn(updatedEntity).when(userRepositoryMock).save(mockEntity);

        UserResponseModel response = userServiceMock.update(mockId, mockRequest);

        assertNotNull(response);
        assertEquals("Jane", response.getFirstname());
        assertEquals("Smith", response.getLastname());
    }

    @Test
    void update_WhenUserDoesNotExist_ThenReturnNull() {
        UUID mockId = UUID.randomUUID();
        UserRequestModel mockRequest = new UserRequestModel("Jane", "Smith");

        when(userRepositoryMock.findById(mockId)).thenReturn(Optional.empty());

        UserResponseModel response = userServiceMock.update(mockId, mockRequest);

        assertNull(response);
    }

    @Test
    void deleteById_WhenUserExists_ThenReturnTrue() {
        UUID mockId = UUID.randomUUID();

        when(userRepositoryMock.existsById(mockId)).thenReturn(true);
        doNothing().when(userRepositoryMock).deleteById(mockId);

        boolean result = userServiceMock.deleteById(mockId);

        assertTrue(result);
        verify(userRepositoryMock, times(1)).deleteById(mockId);
    }

    @Test
    void deleteById_WhenUserDoesNotExist_ThenReturnFalse() {
        UUID mockId = UUID.randomUUID();

        when(userRepositoryMock.existsById(mockId)).thenReturn(false);

        boolean result = userServiceMock.deleteById(mockId);

        assertFalse(result);
        verify(userRepositoryMock, never()).deleteById(mockId);
    }
}
