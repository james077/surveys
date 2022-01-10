package com.robinfood.surveysapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.robinfood.surveysapi.constants.GeneralConstants;
import com.robinfood.surveysapi.constants.ResourceMapping;
import com.robinfood.surveysapi.delegate.ISurveyDelegate;
import com.robinfood.surveysapi.delegate.impl.SurveyDelegate;
import com.robinfood.surveysapi.dto.CustomerDto;
import com.robinfood.surveysapi.dto.QuestionDto;
import com.robinfood.surveysapi.dto.SurveyAnsweredDto;
import com.robinfood.surveysapi.dto.SurveyDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters=false)
@ContextConfiguration(classes ={SurveyController.class})
@WebMvcTest
public class SurveyControllerTest {

    @MockBean
    private ISurveyDelegate surveyDelegate;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getSurvey_whenIsOK_shouldReturn200() throws Exception {
        SurveyDto surveyDto = SurveyDto.builder().name("Survey test").build();
        when(surveyDelegate.getSurvey(1)).thenReturn(ResponseEntity.ok(surveyDto));

        mvc.perform(get(ResourceMapping.SURVEY + "/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void registerSurvey_whenIsOk_shouldReturn200() throws Exception{
        SurveyAnsweredDto surveyAnsweredDto = SurveyAnsweredDto.builder()
                .customer(new CustomerDto())
                .questions(new ArrayList<>()).build();
        when(surveyDelegate.registerSurvey(surveyAnsweredDto)).thenReturn(ResponseEntity.ok(GeneralConstants.SUCCESS));
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(surveyAnsweredDto);

        mvc.perform(post(ResourceMapping.SURVEY + ResourceMapping.REGISTER)
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }
}

