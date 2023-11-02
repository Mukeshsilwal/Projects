package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ATMImpl implements ATM {
    private final BankingSystemImpl bankingSystem=new BankingSystemImpl();
    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
      BigDecimal accountAmount=this.bankingSystem.getAccountBalance(accountNumber);
      BigDecimal atmAmount=this.bankingSystem.sumOfMoneyInAtm();
     if(accountAmount.compareTo(amount)>0&&atmAmount.compareTo(amount)<0){
         throw  new NotEnoughMoneyInATMException("ATM does not have enough balance");
     }
      if(accountAmount.compareTo(amount)<0){
          throw new InsufficientFundsException("Account does not have enough balance");
      }

        List<Banknote> banknotes = calculateBanknotes(amount);

        bankingSystem.debitAccount(accountNumber,amount);
        return banknotes;
    }

    private List<Banknote> calculateBanknotes(BigDecimal amount) {
        List<Banknote> banknotes = new ArrayList<>();
        List<Banknote> availableBanknotes = Arrays.asList(
                Banknote.FIFTY_JOD, Banknote.TWENTY_JOD, Banknote.TEN_JOD, Banknote.FIVE_JOD
        );

        BigDecimal remainingAmount = amount;

        for (Banknote banknote : availableBanknotes) {
            int banknotesCount = remainingAmount.divide(banknote.getValue(), 0, RoundingMode.FLOOR).intValue();
            if (banknotesCount > 0) {
                for (int i = 0; i < banknotesCount; i++) {
                    banknotes.add(banknote);
                }
                BigDecimal banknotesValue = banknote.getValue().multiply(BigDecimal.valueOf(banknotesCount));
                remainingAmount = remainingAmount.subtract(banknotesValue);
            }
        }
        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            throw new InsufficientFundsException("Cannot fulfill the withdrawal request with available banknotes.");
        }

        return banknotes;
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        return bankingSystem.getAccountBalance(accountNumber);
    }
}
