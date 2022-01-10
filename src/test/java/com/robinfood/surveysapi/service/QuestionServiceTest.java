package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.domain.Question;
import com.robinfood.surveysapi.dto.QuestionDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.IQuestionMapper;
import com.robinfood.surveysapi.repository.IQuestionRepository;
import com.robinfood.surveysapi.service.impl.QuestionService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

	@InjectMocks
	private QuestionService questionService;

	@Mock
	private IQuestionRepository questionRepository;

	@Mock
	private IQuestionMapper questionMapper;

	@Test
    public void getQuestionById_whenExist_shouldReturnAnswer() {
        when(questionRepository.findById(anyInt())).thenReturn(Optional.of(new Question()));
        when(questionMapper.entityToDto(any(Question.class))).thenReturn(new QuestionDto());

        QuestionDto response = questionService.getQuestionById(1);

        Assert.assertNotNull(response);
    }

    @Test
    public void getQuestionById_whenNotExist_shouldThrowNoContentException() {
        when(questionRepository.findById(anyInt())).thenReturn(Optional.empty());

        NoContentException thrown = assertThrows(
                NoContentException.class,
                () ->questionService.getQuestionById(1)
        );

        Assert.assertEquals(thrown.getId(),1);
    }
}
