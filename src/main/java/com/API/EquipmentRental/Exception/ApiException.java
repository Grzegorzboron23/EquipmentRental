package com.API.EquipmentRental.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public record ApiException(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
}
