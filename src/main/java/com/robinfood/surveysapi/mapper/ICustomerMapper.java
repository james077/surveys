package com.robinfood.surveysapi.mapper;



import com.robinfood.surveysapi.domain.Customer;
import com.robinfood.surveysapi.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * @author James Martinez
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ICustomerMapper extends IMapperGeneric<Customer, CustomerDto> {

}
