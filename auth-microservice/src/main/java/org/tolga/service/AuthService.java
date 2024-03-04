package org.tolga.service;

import org.springframework.stereotype.Service;
import org.tolga.dto.request.AuthLoginRequestDto;
import org.tolga.dto.request.RegisterRequestDto;
import org.tolga.dto.response.AuthLoginResponseDto;
import org.tolga.dto.response.RegisterResponseDto;
import org.tolga.exception.AuthServiceException;
import org.tolga.exception.ErrorType;
import org.tolga.mapper.IAuthMapper;
import org.tolga.rabbitmq.model.RegisterModel;
import org.tolga.rabbitmq.producer.RegisterProducer;
import org.tolga.repository.IAuthRepository;
import org.tolga.repository.entity.Auth;
import org.tolga.repository.enums.EStatus;
import org.tolga.utility.JwtTokenManager;
import org.tolga.utility.ServiceManager;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository repository;
    private final RegisterProducer registerProducer;

    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository, RegisterProducer registerProducer, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.registerProducer = registerProducer;
        this.jwtTokenManager = jwtTokenManager;
    }
    public RegisterResponseDto register(RegisterRequestDto dto) {
        if (repository.existsByEmail(dto.getEmail())){
            throw new AuthServiceException(ErrorType.EMAIL_ALREADY_EXISTS);
        }
        if (repository.existsByPhoneNumber(dto.getPhoneNumber())){
            throw new AuthServiceException(ErrorType.PHONE_NUMBER_ALREADY_EXISTS);
        }

        Auth auth = IAuthMapper.INSTANCE.registerRequestDtoToAuth(dto);
        save(auth);

        RegisterModel registerModel = RegisterModel.builder()
                .authId(auth.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        registerProducer.convertAndSend(registerModel);


        return IAuthMapper.INSTANCE.authToRegisterResponseDto(auth);
    }
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Optional<Auth> optionalAuth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (optionalAuth.isEmpty()){
            throw new AuthServiceException(ErrorType.EMAIL_OR_PASSWORD_NOT_EXISTS);
        }
        if (optionalAuth.get().getStatus() != EStatus.ACTIVE) {
            throw new AuthServiceException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }

        Optional<String> optionalToken = jwtTokenManager.createToken(optionalAuth.get().getId());
        if (optionalToken.isEmpty()){
            throw new AuthServiceException(ErrorType.TOKEN_NOT_CREATED);
        }

        return AuthLoginResponseDto.builder()
                .id(optionalAuth.get().getId())
                .token(optionalToken.get())
                .build();
    }
}
