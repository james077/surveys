package com.robinfood.surveysapi.delegate.impl;

import com.robinfood.surveysapi.constants.ErrorMessages;
import com.robinfood.surveysapi.constants.GeneralConstants;
import com.robinfood.surveysapi.dto.*;
import com.robinfood.surveysapi.exception.InvalidSurveyException;
import com.robinfood.surveysapi.service.*;
import com.robinfood.surveysapi.delegate.ISurveyDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SurveyDelegate implements ISurveyDelegate {

    private final ISurveyService surveyService;
    private final IQuestionService questionService;
    private final IAnswerService answerService;
    private final ICustomerService customerService;

    public ResponseEntity<SurveyDto> getSurvey(int id){
        return ResponseEntity.ok(surveyService.getSurveyById(id));
    }

    public ResponseEntity<String> registerSurvey(SurveyAnsweredDto surveyAnsweredDto){
        CustomerDto customerDto = surveyAnsweredDto.getCustomer();
        surveyAnsweredDto.getQuestions().forEach(questionDto ->{
            QuestionDto questionDtoDb = questionService.getQuestionById(questionDto.getId());
            questionDto.setOpen(questionDtoDb.getOpen());
            validateAnswers(questionDto.getAnswers());
            if(questionDto.getOpen()){
                AnswerDto answerDto = questionDto.getAnswers().get(0);
                answerDto.setQuestionId(questionDtoDb.getId());
                answerDto.setId(answerService.save(answerDto));
                customerDto.addAnswers(answerDto);
            }else{
                questionDto.getAnswers().forEach(answerDto -> {
                    validateAnswerExist(questionDtoDb.getAnswers(),answerDto.getId());
                    customerDto.addAnswers(answerDto);
                });
            }
        });
        customerService.save(customerDto);
        return ResponseEntity.ok(GeneralConstants.SUCCESS);
    }

    private void validateAnswers(List answers){
        if(answers==null || answers.isEmpty())
            throw InvalidSurveyException.builder()
                    .items(0)
                    .customError(ErrorMessages.UNANSWERED)
                    .build();
    }

    private void validateAnswerExist(List<AnswerDto> answers, Integer id){
        if(!answers.stream().anyMatch(a -> a.getId().equals(id)))
            throw  InvalidSurveyException.builder()
                    .items(0)
                    .customError(ErrorMessages.INVALID_ANSWER)
                    .build();
    }

}
