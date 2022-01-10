package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.dto.QuestionDto;


public interface IQuestionService {

    QuestionDto getQuestionById(Integer id);

}
