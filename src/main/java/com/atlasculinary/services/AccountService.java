package com.atlasculinary.services;


import com.atlasculinary.entities.Account;

import java.util.UUID;

public interface AccountService {
    Account getAccountById(UUID accountId);
    Boolean isAdmin(UUID accountId);
}
