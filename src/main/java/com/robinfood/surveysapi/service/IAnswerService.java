package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.dto.AnswerDto;


public interface IAnswerService {

    int save(AnswerDto answerDto);
    AnswerDto getAnswerById(Integer id);

}
