package com.caldeira.projetofinal.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.caldeira.projetofinal.user.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
