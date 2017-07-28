package com.revolut.service;

import com.revolut.model.Client;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class ClientRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Client> clientEventSrc;

    public void register(Client client) throws Exception {
        log.info("Registering " + client.getName());
        em.persist(client);
        clientEventSrc.fire(client);
    }
}
