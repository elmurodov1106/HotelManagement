package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.dto.request.UserRequestDto;
import com.example.hotelmanagement.entity.user.UserEntity;
import com.example.hotelmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PutMapping("/update")
    public ResponseEntity<UserEntity> update(
            @RequestBody UserRequestDto userRequestDto,
            Principal principal
    ){
        return ResponseEntity.ok(userService.update(userRequestDto,principal));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            Principal principal
    ){
        userService.delete(principal);
        return ResponseEntity.ok("Successfully deleted");
    }

}
