package com.robinfood.surveysapi.repository;

import com.robinfood.surveysapi.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author James Martinez
 */
@Repository
public interface ISurveyRepository extends JpaRepository<Survey, Integer> {

}