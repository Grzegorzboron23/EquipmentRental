package com.API.EquipmentRental.respository;

import com.API.EquipmentRental.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    Page<Equipment> findByNameIgnoreCase(String name, Pageable pageable);

    Page<Equipment> findByPriceGreaterThanEqual(BigDecimal price, Pageable pageable);

    Page<Equipment> findByPriceLessThanEqual(BigDecimal price, Pageable pageable);

    @Query("SELECT e FROM User u JOIN u.equipment e WHERE u.id = :userId")
    Page<Equipment> findPagedEquipmentByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query(value = "SELECT e FROM equipment e" +
            " JOIN users_equipment ue ON e.id = ue.equipmentid" +
            " WHERE ue.userid =:userid" +
            " ORDER BY e.price DESC" +
            " LIMIT 1", nativeQuery = true)
    Equipment findMostExpensiveUserEquipment(@Param("userId") UUID userId);
}
