package com.API.EquipmentRental.controller;

import com.API.EquipmentRental.dto.EquipmentDto;
import com.API.EquipmentRental.dto.EquipmentSlimDto;
import com.API.EquipmentRental.service.EquipmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentServiceImpl equipmentService;

    @GetMapping("/findAll")
    public ResponseEntity<List<EquipmentDto>> getAllEquipment(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam String name) {
        return ResponseEntity.ok().body(equipmentService.getEquipmentByName(page - 1, name));
    }

    @GetMapping("/findByPriceGreaterThan")
    public ResponseEntity<List<EquipmentDto>> getEquipmentByGreaterPrice(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam BigDecimal price) {
        return ResponseEntity.ok().body(equipmentService.getEquipmentMoreExpensiveThan(page - 1, price));
    }

    @GetMapping("/findByPriceCheaperThan")
    public ResponseEntity<List<EquipmentDto>> getEquipmentByCheaperPrice(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam BigDecimal price) {
        return ResponseEntity.ok().body(equipmentService.getEquipmentCheaperThan(page - 1, price));
    }

    @PostMapping("/addEquipment")
    public ResponseEntity<EquipmentDto> createEquipment(@Valid EquipmentSlimDto equipmentSlimDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.addOrUpdateEquipment(equipmentSlimDto));
    }

    @PostMapping("/updateEquipment")
    public ResponseEntity<EquipmentDto> updateEquipment(@Valid EquipmentSlimDto equipmentSlimDto) {
        return ResponseEntity.ok().body(equipmentService.addOrUpdateEquipment(equipmentSlimDto));
    }
}
