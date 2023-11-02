package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingSystemImpl implements BankingSystem {
   Map<String, BigDecimal> accountBalanceMap = new HashMap<String, BigDecimal>();
   EnumMap<Banknote,Integer> atmCashMap = new EnumMap<>(Banknote.class);

    public BankingSystemImpl() {
        atmCashMap.put(Banknote.FIFTY_JOD,10);
        atmCashMap.put(Banknote.TWENTY_JOD,20);
        atmCashMap.put(Banknote.TEN_JOD,100);
        atmCashMap.put(Banknote.FIVE_JOD,100);

        accountBalanceMap.put("123456789", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("111111111", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("222222222", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("333333333", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("444444444", BigDecimal.valueOf(1000.0));
    }

    public BigDecimal sumOfMoneyInAtm(){
        BigDecimal totalMoney = BigDecimal.ZERO;
        for (Map.Entry<Banknote, Integer> entry : atmCashMap.entrySet()) {
            Banknote banknote = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal denomination = new BigDecimal(String.valueOf(banknote.getValue()));
            totalMoney = totalMoney.add(denomination.multiply(BigDecimal.valueOf(quantity)));
        }
        return totalMoney;
    }


    @Override
    public BigDecimal getAccountBalance(String accountNumber){
        if(!accountBalanceMap.containsKey(accountNumber)){
            throw new AccountNotFoundException("Account not found");
        }
        BigDecimal balance = accountBalanceMap.get(accountNumber);
        if (balance == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return balance;
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        BigDecimal balance = accountBalanceMap.get(accountNumber);
        if (balance == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        balance = balance.subtract(amount);
        accountBalanceMap.put(accountNumber, balance);
    }
}
