package org.tolga.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.tolga.dto.request.CustomerRequestDto;
import org.tolga.dto.response.CustomerResponseDto;
import org.tolga.repository.entity.Customer;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);
   Customer customerRequestDtoToCustomer(CustomerRequestDto dto);

   CustomerResponseDto customerToCustomerResponseDto(Customer customer);






}
