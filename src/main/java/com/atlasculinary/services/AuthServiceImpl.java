package com.atlasculinary.services;

import com.atlasculinary.dtos.LoginRequest;
import com.atlasculinary.dtos.LoginResponse;
import com.atlasculinary.dtos.SignUpRequest;
import com.atlasculinary.entities.Account;
import com.atlasculinary.entities.AccountRoleMap;
import com.atlasculinary.entities.Role;
import com.atlasculinary.enums.AccountStatus;
import com.atlasculinary.repositories.AccountRepository;
import com.atlasculinary.repositories.AccountRoleMapRepository;
import com.atlasculinary.repositories.RoleRepository;
import com.atlasculinary.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AccountRepository accountRepository;
  private final RoleRepository roleRepository;
  private final AccountRoleMapRepository accountRoleMapRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  @Override
  public void signUp(SignUpRequest signUpRequest) {
    if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new RuntimeException("Email đã tồn tại trong hệ thống");
    }

    Account account = new Account();
    account.setEmail(signUpRequest.getEmail());
    account.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    account.setStatus(AccountStatus.ACTIVE);

    Role role = roleRepository.findByRoleName(signUpRequest.getRole().name())
            .orElseThrow(() -> new RuntimeException("Role không tồn tại"));

    Account savedAccount = accountRepository.save(account);

    AccountRoleMap accountRoleMap = new AccountRoleMap();
    accountRoleMap.setAccount(savedAccount);
    accountRoleMap.setRole(role);
    accountRoleMap.setAccountId(savedAccount.getAccountId());
    accountRoleMap.setRoleId(role.getRoleId());
    accountRoleMapRepository.save(accountRoleMap);
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getEmail(),
              loginRequest.getPassword()
          )
      );

      Account account = accountRepository.findByEmail(loginRequest.getEmail())
          .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

      if (account.getStatus() == AccountStatus.BLOCKED) {
        throw new RuntimeException("Tài khoản đã bị khóa");
      }
      if (account.getStatus() == AccountStatus.DELETED) {
        throw new RuntimeException("Tài khoản đã bị xóa");
      }

      String token = jwtUtil.generateToken(account.getEmail());

      var roleMapList = accountRoleMapRepository.findByAccountIdWithRole(account.getAccountId());
      
      var roles = roleMapList.stream()
        .map(roleMap -> {
          return roleMap.getRole().getRoleName();
        })
        .collect(Collectors.toList());

      return LoginResponse.builder()
          .token(token)
          .email(account.getEmail())
          .fullName(account.getFullName())
          .avatarUrl(account.getAvatarUrl())
          .roles(roles)
          .build();

    } catch (AuthenticationException e) {
      throw new RuntimeException("Email hoặc mật khẩu không chính xác");
    }
  }
}
