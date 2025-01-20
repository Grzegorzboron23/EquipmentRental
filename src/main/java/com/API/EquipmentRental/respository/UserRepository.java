package com.API.EquipmentRental.respository;

import com.API.EquipmentRental.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findByUsernameLikeIgnoreCase(String username, Pageable pageable);

    Page<User> findByEmailLikeIgnoreCase(String email, Pageable pageable);

    User findByEmailIgnoreCase(String email);

    User findByUsernameIgnoreCase(String username);
}
