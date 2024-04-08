package com.SpringBoot.SpringSecutiryBasics.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.SpringSecutiryBasics.Models.AccountTransactions;
import com.SpringBoot.SpringSecutiryBasics.Models.Customer;
import com.SpringBoot.SpringSecutiryBasics.Repository.AccountTransactionsRepository;
import com.SpringBoot.SpringSecutiryBasics.Repository.CustomerRepository;


@RestController
public class BalanceController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        if (customers != null && !customers.isEmpty()) {
            List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                    findByCustomerIdOrderByTransactionDtDesc(customers.get(0).getId());
            if (accountTransactions != null ) {
                return accountTransactions;
            }
        }
        return null;
    }
}