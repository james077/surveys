package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.dto.CustomerDto;


public interface ICustomerService {

    Integer save(CustomerDto customerDto);

    CustomerDto getCustomerById(Integer id);

}
