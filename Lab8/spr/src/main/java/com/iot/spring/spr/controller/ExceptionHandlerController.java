package com.iot.spring.spr.controller;

import com.iot.spring.spr.DTO.MessageDTO;
import com.iot.spring.spr.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchCategoryException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such category not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchSportsmanException.class)
    ResponseEntity<MessageDTO> handleNoSushPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such sportsman not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchSportException.class)
    ResponseEntity<MessageDTO> handleNoSushBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such sport not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsSportsmanForCategoryException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are sportsmans for this category"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsSportForSportmanException.class)
    ResponseEntity<MessageDTO> handleExistsBooksForPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are sport for this sportsman"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsSportsmanForSportException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are sportsmans for this sport"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsSportInSportsmanException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsBookInPersonExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The sportsman already contain this sport"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SportAbsentException.class)
    ResponseEntity<MessageDTO> handleBookAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this sport is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SportsmanHasNotSportException.class)
    ResponseEntity<MessageDTO> handlePersonHasNotBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The person hasn't this sport"), HttpStatus.NOT_FOUND);
    }

   

}
