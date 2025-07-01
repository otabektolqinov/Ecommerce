package com.company.ecommerce.controller;

import com.company.ecommerce.dto.HttpApiResponse;
import com.company.ecommerce.dto.request.UserRequestDto;
import com.company.ecommerce.dto.response.UserResponseDto;
import com.company.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<UserResponseDto>> createUser(@RequestBody @Valid UserRequestDto dto){
        HttpApiResponse<UserResponseDto> response = userService.createUser(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<HttpApiResponse<UserResponseDto>> getUsersById(@RequestParam("userId") Long id){
        HttpApiResponse<UserResponseDto> response = userService.getUsersById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpApiResponse<UserResponseDto>> updateUserById(
            @RequestParam("userId") Long id,
            @RequestBody UserRequestDto dto
    ){
        HttpApiResponse<UserResponseDto> response = userService.updateUserById(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpApiResponse<String>> createUser(@RequestParam("userId") Long id){
        HttpApiResponse<String> response = userService.deleteUserById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
