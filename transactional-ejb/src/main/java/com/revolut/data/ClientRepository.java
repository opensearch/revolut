package com.revolut.data;

import com.revolut.model.Client;
import com.revolut.service.MoneyTransferException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class ClientRepository {

    @Inject
    private EntityManager em;

    public Client findById(Long id) {
        Client client =  em.find(Client.class, id);
        if(client == null )
            throw  new MoneyTransferException("Client not found: id="+id);
        return client;
    }

    public Client findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
        Root<Client> client = criteria.from(Client.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(client).where(cb.equal(client.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Client> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
        Root<Client> client = criteria.from(Client.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(client).orderBy(cb.asc(client.get("name")));
        return em.createQuery(criteria).getResultList();
    }
}
