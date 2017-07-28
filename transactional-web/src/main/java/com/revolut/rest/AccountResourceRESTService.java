package com.revolut.rest;


import com.revolut.data.AccountRepository;
import com.revolut.model.Account;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/accounts")
@RequestScoped
public class AccountResourceRESTService {


    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private AccountRepository accountRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> listAllClients() {
        return accountRepository.findAllOrderedByClientId();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account lookupAccountById(@PathParam("id") long id) {
        Account account = accountRepository.findById(id);
        if (account == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return account;
    }
}
