package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.domain.Survey;
import com.robinfood.surveysapi.dto.SurveyDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.ISurveyMapper;
import com.robinfood.surveysapi.repository.ISurveyRepository;
import com.robinfood.surveysapi.service.impl.SurveyService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

	@InjectMocks
	private SurveyService surveyService;

	@Mock
	private ISurveyRepository surveyRepository;

	@Mock
	private ISurveyMapper surveyMapper;

	@Test
    public void getSurveyById_whenExist_shouldReturnAnswer() {
        when(surveyRepository.findById(anyInt())).thenReturn(Optional.of(new Survey()));
        when(surveyMapper.entityToDto(any(Survey.class))).thenReturn(new SurveyDto());

        SurveyDto response = surveyService.getSurveyById(1);

        Assert.assertNotNull(response);
    }

    @Test
    public void getSurveyById_whenNotExist_shouldThrowNoContentException() {
        when(surveyRepository.findById(anyInt())).thenReturn(Optional.empty());

        NoContentException thrown = assertThrows(
                NoContentException.class,
                () ->surveyService.getSurveyById(1)
        );

        Assert.assertEquals(thrown.getId(),1);
    }
}
