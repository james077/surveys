package com.robinfood.surveysapi.repository;


import com.robinfood.surveysapi.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author James Martinez
 */
@Repository
public interface IAnswerRepository extends JpaRepository<Answer, Integer> {

}