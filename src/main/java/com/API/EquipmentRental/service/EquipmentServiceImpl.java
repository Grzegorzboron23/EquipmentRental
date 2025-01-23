package com.API.EquipmentRental.service;

import com.API.EquipmentRental.Exception.PriceException;
import com.API.EquipmentRental.Exception.ResourceNotFoundException;
import com.API.EquipmentRental.dto.EquipmentDto;
import com.API.EquipmentRental.dto.EquipmentSlimDto;
import com.API.EquipmentRental.mapstruct.MapStructMapper;
import com.API.EquipmentRental.model.Equipment;
import com.API.EquipmentRental.respository.EquipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    @Value("${page.size}")
    private int PAGE_SIZE;
    private final EquipmentRepository equipmentRepository;
    private final MapStructMapper mapStructMapper;


    @Override
    public List<EquipmentDto> getEquipmentMoreExpensiveThan(int page, BigDecimal price) {
        checkPrice(price);

        Pageable pageable = getPageable(page, "price", Sort.Direction.DESC);
        List<Equipment> equipmentList = equipmentRepository
                .findByPriceGreaterThanEqual(price, pageable)
                .toList();

        return getEquipmentDtos(equipmentList);
    }

    @Override
    public List<EquipmentDto> getEquipmentCheaperThan(int page, BigDecimal price) {
        checkPrice(price);

        Pageable pageable = getPageable(page, "price", Sort.Direction.ASC);
        List<Equipment> equipmentList = equipmentRepository
                .findByPriceLessThanEqual(price, pageable)
                .toList();

        return getEquipmentDtos(equipmentList);
    }

    @Override
    public List<EquipmentDto> getEquipmentByName(int page, String name) {
        if (name.isEmpty()) {
            throw new ResourceNotFoundException("Cannot find user with name " + name);
        }

        Pageable pageable = getPageable(page, "name", Sort.Direction.ASC);
        List<Equipment> equipmentList = equipmentRepository
                .findByNameIgnoreCase(name, pageable)
                .toList();

        return getEquipmentDtos(equipmentList);
    }

    @Override
    @Transactional
    public EquipmentDto addOrUpdateEquipment(EquipmentSlimDto equipmentSlimDto) {
        Equipment equipment = equipmentRepository
                .save(mapStructMapper.equipmentSlimDtoToEquipment(equipmentSlimDto));

        return mapStructMapper
                .equipmentToEquipmentDto(equipment);
    }

    private List<EquipmentDto> getEquipmentDtos(List<Equipment> equipmentList) {
        return mapStructMapper
                .equipmentListToEquipmentDtoList(equipmentList);
    }

    private PageRequest getPageable(int page, String properties, Sort.Direction direction) {
        return PageRequest
                .of(Math.max(page, 0), PAGE_SIZE, Sort.by(direction, properties));
    }

    private void checkPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(1.00)) < 0) {
            throw new PriceException("Price cannot be less than 1");
        }
    }
}
