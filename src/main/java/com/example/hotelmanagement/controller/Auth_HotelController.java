package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.dto.request.LoginRequestDto;
import com.example.hotelmanagement.dto.request.UserRequestDto;
import com.example.hotelmanagement.dto.response.JwtResponse;
import com.example.hotelmanagement.entity.user.UserEntity;
import com.example.hotelmanagement.exception.RequestValidationException;
import com.example.hotelmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class Auth_HotelController {

    private final UserService userService;

    @PostMapping("/sign-Up")
    public ResponseEntity<UserEntity> signUp(
            @Valid @RequestBody UserRequestDto userDto,
            BindingResult bindingResult
            ) throws RequestValidationException {
        if (bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/sign-In")
    public ResponseEntity<JwtResponse> signIn(
            @RequestBody LoginRequestDto loginDto
    ){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshAccessToken(
            Principal principal
    ){
        return ResponseEntity.ok(userService.getNewAccessToken(principal));
    }
}
