package com.robinfood.surveysapi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author James Martinez
 */
@Getter
@AllArgsConstructor
@Builder
public class InvalidSurveyException extends RuntimeException {

    private int items;
    private String customError;

}


