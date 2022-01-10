package com.robinfood.surveysapi.repository;

import com.robinfood.surveysapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author James Martinez
 */
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}