package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.domain.Answer;
import com.robinfood.surveysapi.domain.Question;
import com.robinfood.surveysapi.dto.AnswerDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.IAnswerMapper;
import com.robinfood.surveysapi.repository.IAnswerRepository;
import com.robinfood.surveysapi.repository.IQuestionRepository;
import com.robinfood.surveysapi.service.impl.AnswerService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
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
public class AnswerServiceTest {

	@InjectMocks
	private AnswerService answerService;

	@Mock
	private IAnswerRepository answerRepository;

	@Mock
	private IAnswerMapper answerMapper;

	@Mock
    private IQuestionRepository questionRepository;

    private Answer answer;
    private AnswerDto answerDto;

	@BeforeEach
	public void init(){
        answer = new Answer();
        answer.setId(1);
        answerDto = AnswerDto.builder()
                .id(1)
                .questionId(1)
                .description("This is an answer test").build();
	}

	@Test
	public void save_WhenFoundQuestion_shouldSaveAnswer() {
        int question_id = 1;
        when(questionRepository.findById(question_id)).thenReturn(Optional.of(new Question()));
        when(answerMapper.dtoToEntity(answerDto)).thenReturn(answer);
        when(answerRepository.save(any(Answer.class))).thenReturn(answer);

        int response = answerService.save(answerDto);

        Assert.assertEquals(response,answer.getId().intValue());
	}

    @Test
    public void save_WhenNotFoundQuestion_shouldSaveAnswer() {
        int question_id = 1;
        when(questionRepository.findById(question_id)).thenReturn(Optional.empty());

        NoContentException thrown = assertThrows(
                NoContentException.class,
                () ->answerService.save(answerDto)
        );

        Assert.assertEquals(thrown.getId(), answerDto.getQuestionId().intValue());
    }

    @Test
    public void getAnswerById_whenExist_shouldReturnAnswer() {
        when(answerRepository.findById(anyInt())).thenReturn(Optional.of(new Answer()));
        when(answerMapper.entityToDto(any(Answer.class))).thenReturn(new AnswerDto());

        AnswerDto response = answerService.getAnswerById(1);

        Assert.assertNotNull(response);
    }

    @Test
    public void getAnswerById_whenNotExist_shouldThrowNoContentException() {
        when(answerRepository.findById(anyInt())).thenReturn(Optional.empty());

        NoContentException thrown = assertThrows(
                NoContentException.class,
                () ->answerService.getAnswerById(1)
        );

        Assert.assertEquals(thrown.getId(),1);
    }

}
