package com.robinfood.surveysapi.delegate;

import com.robinfood.surveysapi.dto.*;
import com.robinfood.surveysapi.exception.InvalidSurveyException;
import com.robinfood.surveysapi.constants.ErrorMessages;
import com.robinfood.surveysapi.delegate.impl.SurveyDelegate;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.service.IAnswerService;
import com.robinfood.surveysapi.service.ICustomerService;
import com.robinfood.surveysapi.service.IQuestionService;
import com.robinfood.surveysapi.service.ISurveyService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyDelegateTest {

    @InjectMocks
    private SurveyDelegate surveyDelegate;

    @Mock
    private ISurveyService surveyService;

    @Mock
    private IQuestionService questionService;

    @Mock
    private IAnswerService answerService;

    @Mock
    private ICustomerService customerService;



    private QuestionDto question;
    private CustomerDto customer;
    private AnswerDto answer;
    private SurveyAnsweredDto surveyAnsweredDto;

    @BeforeEach
    public void init(){
        customer = CustomerDto.builder().name("test user").build();
        answer = AnswerDto.builder().id(1).description("Good").build();
        question = QuestionDto.builder()
                .id(1)
                .answers(Arrays.asList(answer))
                .open(false).build();
        surveyAnsweredDto = SurveyAnsweredDto.builder()
                .customer(customer)
                .questions(Arrays.asList(question))
                .build();
    }

    @Test
    public void getSurvey_whenIsOk_shouldReturnSurvey() {
        int id = 1;
        SurveyDto survey = SurveyDto.builder()
                .id(id)
                .name("test survey")
                .questions(Arrays.asList(new QuestionDto())).build();
        when(surveyService.getSurveyById(1)).thenReturn(survey);

        ResponseEntity<SurveyDto> response = surveyDelegate.getSurvey(id);

        Assert.assertNotNull(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getSurvey_whenNotExist_shouldThrowNoContentException() {
        int id = 1;
        when(surveyService.getSurveyById(1)).thenThrow(NoContentException.builder().id(id).build());

        NoContentException thrown = assertThrows(
                NoContentException.class,
                () ->surveyDelegate.getSurvey(id)
        );

        Assert.assertEquals(thrown.getId(),1);
    }

    @Test
    public void registerSurvey_whenIsOk_shouldSaveAndReturnSuccess() {
        when(questionService.getQuestionById(anyInt())).thenReturn(question);
        when(customerService.save(any())).thenReturn(1);

        ResponseEntity<String> response = surveyDelegate.registerSurvey(surveyAnsweredDto);

        Assert.assertNotNull(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void registerSurvey_whenAnswerIsOpen_shouldSaveAndReturnSuccess() {
        question.setOpen(true);
        when(questionService.getQuestionById(anyInt())).thenReturn(question);
        when(answerService.save(any())).thenReturn(1);
        when(customerService.save(any())).thenReturn(1);

        ResponseEntity<String> response = surveyDelegate.registerSurvey(surveyAnsweredDto);

        Assert.assertNotNull(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void registerSurvey_whenThereIsntAnswers_shouldThrowInvalidSurveyException() {
        question.setAnswers(null);
        when(questionService.getQuestionById(anyInt())).thenReturn(question);

        InvalidSurveyException thrown = assertThrows(
                InvalidSurveyException.class,
                () ->surveyDelegate.registerSurvey(surveyAnsweredDto)
        );

        Assert.assertEquals(thrown.getCustomError(),ErrorMessages.UNANSWERED);
    }

    @Test
    public void registerSurvey_whenAnswerIsOpenAndNotExist_shouldThrowException() {
        AnswerDto inconsistentAnswer = AnswerDto.builder().id(100).description("other Answer").build();
        QuestionDto questionDto = QuestionDto.builder()
                .id(1)
                .answers(Arrays.asList(inconsistentAnswer))
                .open(false).build();
        surveyAnsweredDto.setQuestions(Arrays.asList(questionDto));
        when(questionService.getQuestionById(anyInt())).thenReturn(question);

        InvalidSurveyException thrown = assertThrows(
                InvalidSurveyException.class,
                () ->surveyDelegate.registerSurvey(surveyAnsweredDto)
        );

        Assert.assertEquals(thrown.getCustomError(),ErrorMessages.INVALID_ANSWER);
    }

}
