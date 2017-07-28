package com.revolut.service;

import com.revolut.model.Account;
import com.revolut.model.Client;
import com.revolut.model.Currency;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class MoneyTransfer {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @TransactionAttribute(REQUIRED)
    public void transfer(Client from, Client to, final Currency currency, BigDecimal amount) {
        Account fromAccount = null;
        for (Account fromAccountI : from.getAccounts())
            if (fromAccountI.getCurrency() == currency)
                fromAccount = fromAccountI;

        Account toAccount = null;
        for (Account toAccountI : to.getAccounts())
            if (toAccountI.getCurrency() == currency)
                toAccount = toAccountI;

        if (fromAccount == null)
            throw new RuntimeException(currency + " not found ad 'from' account");

        if (to == null)
            throw new RuntimeException(currency + " not found at 'to' account");

        if(fromAccount.getAmount().compareTo(amount) < 0 )
            throw new RuntimeException(amount + " Insufficient funds.");

        toAccount.setAmount(toAccount.getAmount().add(amount));
        fromAccount.setAmount(fromAccount.getAmount().subtract(amount));
        em.merge(toAccount);
        em.merge(fromAccount);
    }
}
