package com.robinfood.surveysapi.service.impl;

import com.robinfood.surveysapi.domain.Answer;
import com.robinfood.surveysapi.domain.Question;
import com.robinfood.surveysapi.dto.AnswerDto;
import com.robinfood.surveysapi.dto.QuestionDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.IAnswerMapper;
import com.robinfood.surveysapi.repository.IAnswerRepository;
import com.robinfood.surveysapi.repository.IQuestionRepository;
import com.robinfood.surveysapi.service.IAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AnswerService implements IAnswerService {

    private final IAnswerRepository answerRepository;
    private final IQuestionRepository questionRepository;
    private final IAnswerMapper answerMapper;

    @Override
    public int save(AnswerDto answerDto) {
        log.info("Saving answer...");
        Optional<Question> question = questionRepository.findById(answerDto.getQuestionId());
        if (!question.isPresent()) {
            throw NoContentException.builder()
                    .id(answerDto.getQuestionId())
                    .build();
        }
        Answer answer = answerMapper.dtoToEntity(answerDto);
        answer.setQuestion(question.get());
        answerRepository.save(answer);
        return answer.getId();
    }

    @Override
    public AnswerDto getAnswerById(Integer id) {
        log.info("Getting answer with id "+ id);
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent())
            return answerMapper.entityToDto(answer.get());

        throw NoContentException.builder().id(id).build();
    }


}
