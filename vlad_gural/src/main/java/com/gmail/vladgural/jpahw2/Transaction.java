package com.gmail.vladgural.jpahw2;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "id_account_from")
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name ="id_account_to")
    private  Account accountTo;

    public Transaction() {
    }

    public Transaction(double amount, Account accountFrom, Account accountTo) {
        this.amount = amount;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
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

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }
}
