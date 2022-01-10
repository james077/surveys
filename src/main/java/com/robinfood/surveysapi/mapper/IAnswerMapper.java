package com.robinfood.surveysapi.mapper;


import com.robinfood.surveysapi.domain.Answer;
import com.robinfood.surveysapi.domain.Question;
import com.robinfood.surveysapi.dto.AnswerDto;
import com.robinfood.surveysapi.dto.QuestionDto;
import org.mapstruct.*;

/**
 * @author James Martinez
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IAnswerMapper extends IMapperGeneric<Answer, AnswerDto> {

    @Mappings({
            @Mapping(source = "question.id", target = "questionId"),
    })
    AnswerDto entityToDto(Answer answer);
}
