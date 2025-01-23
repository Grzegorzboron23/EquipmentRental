package com.API.EquipmentRental.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class GlobalHandleException {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleGlobalRequestException(ResourceNotFoundException e) {
        log.warn("ResourceNotFoundException occurred:", e);

        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        ApiException apiException = getApiException(e, badRequest);

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException occurred:", e);

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = getApiException(e, badRequest);

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        log.warn("Unexpected exception occurred:", e);

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = getApiException(e, internalServerError);

        return new ResponseEntity<>(apiException, internalServerError);
    }

    @ExceptionHandler(value = {PriceException.class})
    public ResponseEntity<Object> handlePriceException(Exception e) {
        log.warn("Price exception occurred:", e);

        HttpStatus internalServerError = HttpStatus.BAD_REQUEST;
        ApiException apiException = getApiException(e, internalServerError);

        return new ResponseEntity<>(apiException, internalServerError);
    }

    private static ApiException getApiException(Exception e, HttpStatus badRequest) {
        return new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
    }
}
