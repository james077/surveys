package com.robinfood.surveysapi.mapper;

import com.robinfood.surveysapi.domain.Survey;
import com.robinfood.surveysapi.dto.SurveyDto;
import org.mapstruct.*;


/**
 * @author James Martinez
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ISurveyMapper extends IMapperGeneric<Survey, SurveyDto> {

    @AfterMapping
    default void setDefaultAnswers(@MappingTarget SurveyDto.SurveyDtoBuilder dto) {
        dto.build().getQuestions().forEach(questionDto ->{
            if(questionDto.getOpen())
                questionDto.setAnswers(null);
        });
    }
}
