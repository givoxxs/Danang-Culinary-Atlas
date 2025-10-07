package com.atlasculinary.services;

import com.atlasculinary.dtos.SignUpRequest;

public interface AuthService {
  void signUp(SignUpRequest signUpRequest);
}
