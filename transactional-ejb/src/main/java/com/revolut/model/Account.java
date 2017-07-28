package com.revolut.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id")
    private Client client;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal amount;;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
