package com.revolut.data;

import com.revolut.model.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class AccountRepository {

    @Inject
    private EntityManager em;

    public Account findById(Long id) {
        return em.find(Account.class, id);
    }

    public List<Account> findAllOrderedByClientId() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = cb.createQuery(Account.class);
        Root<Account> account = criteria.from(Account.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(account).orderBy(cb.asc(account.get("client")));
        return em.createQuery(criteria).getResultList();
    }
}
