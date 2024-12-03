package com.caldeira.projetofinal.user.validators;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestValidatorTest {

    private UserRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new UserRequestValidator();
    }

    @Test
    void validate_WhenModelIsNull_ThenThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(null),
                "Expected to throw IllegalArgumentException for null model"
        );

        assertEquals("UseRequestModel can not be null.", exception.getMessage());
    }

    @Test
    void validate_WhenFirstNameIsNull_ThenThrowIllegalArgumentException() {
        UserRequestModel model = new UserRequestModel(null, "Doe");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(model),
                "Expected to throw IllegalArgumentException for null first name"
        );

        assertEquals("FirstName can not be null or less than 3 characters long.", exception.getMessage());
    }

    @Test
    void validate_WhenFirstNameIsTooShort_ThenThrowIllegalArgumentException() {
        UserRequestModel model = new UserRequestModel("Jo", "Doe");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(model),
                "Expected to throw IllegalArgumentException for short first name"
        );

        assertEquals("FirstName can not be null or less than 3 characters long.", exception.getMessage());
    }

    @Test
    void validate_WhenLastNameIsNull_ThenThrowIllegalArgumentException() {
        UserRequestModel model = new UserRequestModel("John", null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(model),
                "Expected to throw IllegalArgumentException for null last name"
        );

        assertEquals("LastName can not be null or less than 3 characters long.", exception.getMessage());
    }

    @Test
    void validate_WhenLastNameIsTooShort_ThenThrowIllegalArgumentException() {
        UserRequestModel model = new UserRequestModel("John", "Do");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(model),
                "Expected to throw IllegalArgumentException for short last name"
        );

        assertEquals("LastName can not be null or less than 3 characters long.", exception.getMessage());
    }

    @Test
    void validate_WhenModelIsValid_ThenDoNotThrowException() {
        UserRequestModel model = new UserRequestModel("John", "Doe");

        assertDoesNotThrow(() -> validator.validate(model));
    }
}