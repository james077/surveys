package com.robinfood.surveysapi.repository;


import com.robinfood.surveysapi.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author James Martinez
 */
@Repository
public interface IQuestionRepository extends JpaRepository<Question, Integer> {

}