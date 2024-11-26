package com.caldeira.projetofinal.user.validators;

import com.caldeira.projetofinal.user.models.UserRequestModel;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidator {
    public void validate(models.UserRequestModel model) {
        if (model == null) {
            throw  new IllegalArgumentException("UseRequestModel can not be null.");
        }

        if (model.getFirstName() == null || model.getFirstName().length() < 3) {
            throw new IllegalArgumentException("FirstName can not be null or less than 3 characters long.");
        }

        if (model.getLastName () == null || model.getLastName().length() < 3) {
            throw new IllegalArgumentException("LastName can not be null or less than 3 characters long.");
        }
    }
}
