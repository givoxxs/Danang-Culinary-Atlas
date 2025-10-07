package com.atlasculinary.controllers;

import com.atlasculinary.dtos.ApiResponse;
import com.atlasculinary.dtos.SignUpRequest;
import com.atlasculinary.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
    authService.signUp(signUpRequest);
    ApiResponse response = ApiResponse.success("Đăng ký tài khoản thành công");
    return ResponseEntity.ok(response);
  }
}
