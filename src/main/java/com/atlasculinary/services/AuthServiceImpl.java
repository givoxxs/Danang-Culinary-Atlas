package com.atlasculinary.services;

import com.atlasculinary.dtos.SignUpRequest;
import com.atlasculinary.entities.Account;
import com.atlasculinary.entities.AccountRoleMap;
import com.atlasculinary.entities.Role;
import com.atlasculinary.enums.AccountStatus;
import com.atlasculinary.repositories.AccountRepository;
import com.atlasculinary.repositories.AccountRoleMapRepository;
import com.atlasculinary.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AccountRepository accountRepository;
  private final RoleRepository roleRepository;
  private final AccountRoleMapRepository accountRoleMapRepository;
  private final PasswordEncoder passwordEncoder;

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
}
