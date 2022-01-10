package com.robinfood.surveysapi.mapper;


import com.robinfood.surveysapi.domain.Question;
import com.robinfood.surveysapi.domain.Survey;
import com.robinfood.surveysapi.dto.QuestionDto;
import com.robinfood.surveysapi.dto.SurveyDto;
import org.mapstruct.*;

/**
 * @author James Martinez
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IQuestionMapper extends IMapperGeneric<Question, QuestionDto> {

    @Mappings({
            @Mapping(source = "survey.id", target = "surveyId"),
    })
    QuestionDto entityToDto(Question question);

}
