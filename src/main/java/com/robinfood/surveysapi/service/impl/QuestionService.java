package com.robinfood.surveysapi.service.impl;

import com.robinfood.surveysapi.domain.Question;
import com.robinfood.surveysapi.dto.QuestionDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.IQuestionMapper;
import com.robinfood.surveysapi.repository.IQuestionRepository;
import com.robinfood.surveysapi.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QuestionService implements IQuestionService {

    private final IQuestionRepository questionRepository;
    private final IQuestionMapper questionMapper;

    @Override
    public QuestionDto getQuestionById(Integer id) {
        log.info("Getting question with id "+ id);
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent())
            return questionMapper.entityToDto(question.get());

        throw NoContentException.builder().id(id).build();
    }


}
