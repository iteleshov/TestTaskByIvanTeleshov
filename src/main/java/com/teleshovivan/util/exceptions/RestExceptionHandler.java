package com.teleshovivan.util.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Jager on 29.08.2016.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public void handleNotFoundException(NotFoundException ex) {
        LOGGER.debug(ex.getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public void handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        LOGGER.debug(ex.getMessage());
    }
}

