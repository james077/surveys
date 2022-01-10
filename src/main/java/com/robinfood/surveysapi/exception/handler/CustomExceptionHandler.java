package com.robinfood.surveysapi.exception.handler;

import com.robinfood.surveysapi.constants.ErrorMessages;
import com.robinfood.surveysapi.exception.InvalidSurveyException;
import com.robinfood.surveysapi.exception.NoContentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author James Martinez
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {NoContentException.class, InvalidSurveyException.class, DataIntegrityViolationException.class,
            ConstraintViolationException.class,  NullPointerException.class})
    public final ResponseEntity handleConflict(RuntimeException ex) {

        if (ex instanceof NoContentException) {
            NoContentException _ex = (NoContentException) ex;
            log.error(String.format("El item %s not exist ", _ex.getId()));
            return ResponseEntity.noContent().build();
        }
        if (ex instanceof InvalidSurveyException) {
            InvalidSurveyException _ex = (InvalidSurveyException) ex;
            log.info(_ex.getCustomError());
            return new ResponseEntity(_ex.getCustomError(),HttpStatus.CONFLICT);
        }
       if (ex instanceof DataIntegrityViolationException || ex instanceof ConstraintViolationException) {
           log.error(ErrorMessages.SQL_TRANSACTION, ex);
           return new ResponseEntity(ErrorMessages.SQL_TRANSACTION, HttpStatus.CONFLICT);
       }
        log.error(ErrorMessages.NOT_CONTROLLED_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
