package com.robinfood.surveysapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {

    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    List<AnswerDto> answers;

    public void addAnswers(AnswerDto answerDto){
        if(this.answers == null){
            this.answers = new ArrayList<>();
        }
        this.answers.add(answerDto);
    }
}
