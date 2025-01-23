package com.API.EquipmentRental.respository;

import com.API.EquipmentRental.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, UUID> {

    Page<Equipment> findEquipmentByName(String name, Pageable pageable);

    Page<Equipment> findEquipmentByPriceGreaterThanEqual(BigDecimal price, Pageable pageable);

    Page<Equipment> findEquipmentByPriceLessThanEqual(BigDecimal price, Pageable pageable);
}
