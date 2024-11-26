package com.caldeira.projetofinal.user.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseModel {
    UUID id;
    String firstname;
    String lastname;
    LocalDateTime creationDate;
}
