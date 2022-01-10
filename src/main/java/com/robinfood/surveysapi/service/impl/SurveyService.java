package com.robinfood.surveysapi.service.impl;

import com.robinfood.surveysapi.domain.Survey;
import com.robinfood.surveysapi.dto.SurveyDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.ISurveyMapper;
import com.robinfood.surveysapi.repository.ISurveyRepository;
import com.robinfood.surveysapi.service.ISurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SurveyService implements ISurveyService {

    private final ISurveyRepository surveyRepository;
    private final ISurveyMapper surveyMapper;

    @Override
    public SurveyDto getSurveyById(Integer id) {
        log.info("Getting Survey with id "+ id);
        Optional<Survey> survey = surveyRepository.findById(id);
        if (survey.isPresent())
            return surveyMapper.entityToDto(survey.get());

        throw NoContentException.builder().id(id).build();
    }

}
