package com.luv2code.AOPDemo.dao;

import com.luv2code.AOPDemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount(Account theAccount) {
        System.out.println(getClass()+": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // for academic purposes ... simulate an exception
        if(tripWire){
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> myAccounts=new ArrayList<>();
        // create sample accounts
        // add them to our accounts list
        myAccounts.add(new Account("Arpan","silver"));
        myAccounts.add(new Account("Satabda","gold"));
        myAccounts.add(new Account("Rahul","platinum"));
        return myAccounts;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }
}
