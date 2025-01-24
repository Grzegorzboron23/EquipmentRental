package com.API.EquipmentRental.enums;

public enum Role {

    ADMIN(3),
    MODERATOR(2),
    USER(1);

    final int power;

     Role(int power){
      this.power = power;
    }
}
