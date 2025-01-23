package com.API.EquipmentRental.service;

import com.API.EquipmentRental.dto.EquipmentDto;
import com.API.EquipmentRental.dto.EquipmentSlimDto;

import java.math.BigDecimal;
import java.util.List;

public interface EquipmentService {

    List<EquipmentDto> getEquipmentMoreExpensiveThan(int page, BigDecimal price);

    List<EquipmentDto> getEquipmentCheaperThan(int page, BigDecimal price);

    List<EquipmentDto> getEquipmentByName(int page, String name);

    EquipmentDto addOrUpdateEquipment(EquipmentSlimDto equipmentSlimDto);
}
