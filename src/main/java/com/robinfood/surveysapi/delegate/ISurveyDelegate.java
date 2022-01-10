package com.robinfood.surveysapi.delegate;

import com.robinfood.surveysapi.dto.SurveyAnsweredDto;
import com.robinfood.surveysapi.dto.SurveyDto;
import org.springframework.http.ResponseEntity;

public interface ISurveyDelegate {

    ResponseEntity<SurveyDto> getSurvey(int id);

    ResponseEntity<String> registerSurvey(SurveyAnsweredDto surveyAnsweredDto);
}
