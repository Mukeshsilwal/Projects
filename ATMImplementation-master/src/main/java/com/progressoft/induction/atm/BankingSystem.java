package com.progressoft.induction.atm;

import java.math.BigDecimal;
import java.util.List;

public interface BankingSystem {

    BigDecimal getAccountBalance(String accountNumber);

    void debitAccount(String accountNumber, BigDecimal amount);
}
