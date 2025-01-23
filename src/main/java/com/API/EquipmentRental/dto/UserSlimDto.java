package com.API.EquipmentRental.dto;

import javax.validation.constraints.NotBlank;

public class UserSlimDto {
    @NotBlank
    private String username;

    @NotBlank
    private String email;
}
