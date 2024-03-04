package org.tolga.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tolga.dto.request.AuthLoginRequestDto;
import org.tolga.dto.request.RegisterRequestDto;
import org.tolga.dto.response.AuthLoginResponseDto;
import org.tolga.dto.response.RegisterResponseDto;
import org.tolga.exception.AuthServiceException;
import org.tolga.exception.ErrorType;
import org.tolga.service.AuthService;

import static org.tolga.constant.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if (!dto.getPassword().equals(dto.getRePassword())){
            throw new AuthServiceException(ErrorType.PASSWORD_MISMATCH);
        }
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<AuthLoginResponseDto> login(@RequestBody @Valid AuthLoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
