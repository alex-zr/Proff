package com.gmail.vladgural.jpahw2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "accountFrom", cascade = CascadeType.ALL)
    private List<Transaction> transactionsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "accountTo", cascade = CascadeType.ALL)
    private List<Transaction> transactionsTo = new ArrayList<>();

    public Account() {
    }

    public void addTransactionFrom(Transaction transaction) {
        transactionsFrom.add(transaction);
        transaction.setAccountFrom(this);
    }

    public void addTransactionTo(Transaction transaction) {
        transactionsTo.add(transaction);
        transaction.setAccountTo(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", client=" + client +
                '}';
    }
}
