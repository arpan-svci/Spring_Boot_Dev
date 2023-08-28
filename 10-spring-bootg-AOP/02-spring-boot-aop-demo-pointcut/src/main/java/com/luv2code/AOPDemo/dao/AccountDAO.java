package com.luv2code.AOPDemo.dao;

import com.luv2code.AOPDemo.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account theAccount);

    List<Account> findAccounts();
    List<Account> findAccounts(boolean tripWire);
}
