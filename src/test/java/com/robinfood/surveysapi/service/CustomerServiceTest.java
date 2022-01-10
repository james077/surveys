package com.robinfood.surveysapi.service;

import com.robinfood.surveysapi.domain.Customer;
import com.robinfood.surveysapi.dto.CustomerDto;
import com.robinfood.surveysapi.exception.NoContentException;
import com.robinfood.surveysapi.mapper.ICustomerMapper;
import com.robinfood.surveysapi.repository.ICustomerRepository;
import com.robinfood.surveysapi.service.impl.CustomerService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private ICustomerRepository customerRepository;

	@Mock
	private ICustomerMapper customerMapper;


    private Customer customer;
    private CustomerDto customerDto;

	@BeforeEach
	public void init(){
        customer = new Customer();
        customer.setName("Maria Antonieta");
        customer.setId(1);
        customerDto = CustomerDto.builder()
                .id(1)
                .name("Maria Antonieta").build();
	}

	@Test
	public void saveAnswer_shouldSaveAnswer() {
        when(customerMapper.dtoToEntity(customerDto)).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        int response = customerService.save(customerDto);

        Assert.assertEquals(response,customer.getId().intValue());
	}

    @Test
    public void getCustomerById_whenExist_shouldReturnCustomer() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(new Customer()));
        when(customerMapper.entityToDto(any(Customer.class))).thenReturn(new CustomerDto());

        CustomerDto response = customerService.getCustomerById(1);

        Assert.assertNotNull(response);
    }

    @Test
    public void getCustomerById_whenNotExist_shouldThrowNoContentException() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        NoContentException thrown = assertThrows(
                NoContentException.class,
                () ->customerService.getCustomerById(1)
        );

        Assert.assertEquals(thrown.getId(),1);
    }

}
