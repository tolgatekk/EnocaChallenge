package org.tolga.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.tolga.dto.request.CreateProductRequestDto;
import org.tolga.dto.request.PlaceOrderRequestDto;
import org.tolga.repository.entity.Order;
import org.tolga.repository.entity.Product;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {


    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
    Product createProductRequestDtoToProduct(CreateProductRequestDto dto);
    Order placeOrderRequestDtoToOrder(PlaceOrderRequestDto dto);




}
