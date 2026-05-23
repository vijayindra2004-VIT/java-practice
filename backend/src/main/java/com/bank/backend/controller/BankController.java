package com.bank.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.backend.model.Account;
import com.bank.backend.model.Transaction;
import com.bank.backend.service.BankService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/account")
    public Account createAccount(@RequestParam String name) {
        return bankService.createAccount(name);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return bankService.getAllAccounts();
    }

    @PostMapping("/deposit")
    public Account deposit(@RequestParam Long id, @RequestParam double amount) {
        return bankService.deposit(id, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam Long id, @RequestParam double amount) {
        return bankService.withdraw(id, amount);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return bankService.getAllTransactions();
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id) {
        bankService.deleteAccount(id);
        return "Account deleted successfully";
    }
}