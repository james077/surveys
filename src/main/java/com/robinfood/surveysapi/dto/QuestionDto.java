package com.robinfood.surveysapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuestionDto {

    @NotNull
    private Integer id;
    @NotNull
    private Integer surveyId;
    private String description;
    @NotNull
    private Boolean open;
    @NotNull
    private List<AnswerDto> answers;


}
