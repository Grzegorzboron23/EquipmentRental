package com.API.EquipmentRental.respository;

import com.API.EquipmentRental.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findByUsernameLikeIgnoreCase(String username, Pageable pageable);

    Page<User> findByEmailLikeIgnoreCase(String email, Pageable pageable);

    User findByEmailIgnoreCase(String email);

    User findByUsernameIgnoreCase(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.equipment e WHERE e.id = :equipmentId")
    Page<User> findPagedUserByEquipmentId(@Param("equipmentId") UUID equipmentId, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.equipment e WHERE e.name LIKE %:name%")
    Page<User> findPagedUserByEquipmentName(@Param("equipmentName") String equipmentName, Pageable pageable);

    @Query("SELECT COUNT(u) FROM User u JOIN u.equipment e WHERE e.id = :equipment")
    Integer countUserUsingEquipment(@Param("equipmentId") UUID equipmentId);
}
