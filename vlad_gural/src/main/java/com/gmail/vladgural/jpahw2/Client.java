package com.gmail.vladgural.jpahw2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "passport",unique = true)
    private String passport;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        account.setClient(this);

        if(!accounts.contains(account))
            accounts.add(account);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                '}');
        for(Account acc: accounts){
            sb.append("\n\r\t");
            sb.append("Account{" +
                        "id=" + acc.getId() +
                        " amount=" + acc.getAmount() +
                        '}');
        }
        return sb.toString();
    }
}
