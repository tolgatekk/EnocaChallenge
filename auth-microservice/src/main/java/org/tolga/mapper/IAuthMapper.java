package org.tolga.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.tolga.dto.request.RegisterRequestDto;
import org.tolga.dto.response.RegisterResponseDto;
import org.tolga.repository.entity.Auth;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth registerRequestDtoToAuth(RegisterRequestDto dto);

    RegisterResponseDto authToRegisterResponseDto(Auth auth);


}
