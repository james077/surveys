package com.robinfood.surveysapi.controller;


import com.robinfood.surveysapi.constants.ResourceMapping;
import com.robinfood.surveysapi.delegate.ISurveyDelegate;
import com.robinfood.surveysapi.dto.SurveyAnsweredDto;
import com.robinfood.surveysapi.dto.SurveyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(ResourceMapping.SURVEY)
public class SurveyController {

    private final ISurveyDelegate surveyDelegate;

    @GetMapping(value = ResourceMapping.ID_PARAMETER)
    public ResponseEntity<SurveyDto> getSurvey(@PathVariable int id) {
        return surveyDelegate.getSurvey(id);
    }

    @PostMapping(value = ResourceMapping.REGISTER)
    public ResponseEntity<String> registerSurvey(@RequestBody @Valid SurveyAnsweredDto surveyAnsweredDto) {
        return  surveyDelegate.registerSurvey(surveyAnsweredDto);
    }

}
