//package com.atlasculinary.services;
//
//import com.atlasculinary.entities.Account;
//import com.atlasculinary.repositories.AccountRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//  private final AccountRepository accountRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    Account account = accountRepository.findByEmail(email)
//        .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản với email: " + email));
//
//    Set<GrantedAuthority> authorities = account.getAccountRoleMapSet()
//        .stream()
//        .map(roleMap -> new SimpleGrantedAuthority("ROLE_" + roleMap.getRole().getRoleName()))
//        .collect(Collectors.toSet());
//
//    return User.builder()
//        .username(account.getEmail())
//        .password(account.getPassword())
//        .authorities(authorities)
//        .accountExpired(false)
//        .accountLocked(account.getStatus().name().equals("BLOCKED"))
//        .credentialsExpired(false)
//        .disabled(account.getStatus().name().equals("DELETED"))
//        .build();
//  }
//}
