package com.robinfood.surveysapi.service.impl;

import com.robinfood.surveysapi.domain.Customer;
import com.robinfood.surveysapi.dto.CustomerDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.ICustomerMapper;
import com.robinfood.surveysapi.repository.ICustomerRepository;
import com.robinfood.surveysapi.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    @Override
    public Integer save(CustomerDto customerDto) {
        log.info("Saving customer...");
        Customer customer = customerMapper.dtoToEntity(customerDto);
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        log.info("Getting customer with id "+ id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            return customerMapper.entityToDto(customer.get());

        throw NoContentException.builder().id(id).build();
    }

}
