package com.bank.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.backend.model.Account;
import com.bank.backend.model.Transaction;
import com.bank.backend.repository.AccountRepository;
import com.bank.backend.repository.TransactionRepository;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository,
                       TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(String name) {
        Account account = new Account();
        account.setName(name);
        account.setAccountNumber(String.valueOf(System.currentTimeMillis()));
        account.setBalance(0);

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setName(account.getName());
        transaction.setAccountNumber(account.getAccountNumber());
        transaction.setType("Deposit");
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction();
        transaction.setName(account.getName());
        transaction.setAccountNumber(account.getAccountNumber());
        transaction.setType("Withdraw");
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}