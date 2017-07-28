package com.revolut.rest;

import com.revolut.data.ClientRepository;
import com.revolut.model.Currency;
import com.revolut.service.MoneyTransfer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Path("transfer")
@RequestScoped
public class MoneyTransferRestService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    MoneyTransfer moneyTransfer;

    @Inject
    ClientRepository clientRepository;

    @GET
    public void transfer(@QueryParam("from") Long from, @QueryParam("to") Long to, @QueryParam("currency") Currency currency, @QueryParam("amount") BigDecimal amount){
        moneyTransfer.transfer(clientRepository.findById(from), clientRepository.findById(to), currency, amount);
    }
}
