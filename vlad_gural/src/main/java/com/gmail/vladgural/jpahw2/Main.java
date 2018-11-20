package com.gmail.vladgural.jpahw2;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("JPAHW2");
            em = emf.createEntityManager();

            createDonor();

            while (true) {
                System.out.println("1: Add Client");
                System.out.println("2: Add Account");
                System.out.println("3: Put money to account");
                System.out.println("4: Get money from account");
                System.out.println("5: Transaction money from account 1 to account 2");
                System.out.println("9: Show Clients and accounts");
                String str = scanner.nextLine();

                switch (str) {
                    case "1":
                        addClient();
                        break;
                    case "2":
                        addAccount();
                        break;
                    case "3":
                        transaction(TrType.OutTo);
                        break;
                    case "4":
                        transaction(TrType.FromOut);
                        break;
                    case "5":
                        transaction(TrType.FromTo);
                        break;
                    case "9":
                        showClientsAndAccounts();
                        break;
                    default:
                        return;
                }
            }

        } finally {
            scanner.close();
            em.close();
            emf.close();
        }

    }

    public static void createDonor() {
        Client cl = new Client("Donor", "OUTSIDE");
        em.getTransaction().begin();
        try {
            em.persist(cl);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        Account account = new Account();


        em.getTransaction().begin();
        try {
            cl.addAccount(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void addClient() {
        System.out.println();
        System.out.println("Enter client's name");
        String clName = scanner.nextLine();
        System.out.println("Enter client passport's #");
        String clPassport = scanner.nextLine();

        Client cl = new Client(clName, clPassport);
        em.getTransaction().begin();
        try {
            em.persist(cl);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void addAccount() {
        Client client;
        Account account = new Account();

        System.out.println("Enter passport #");
        String passport = scanner.nextLine();

        try {
            Query query = em.createQuery("FROM Client c WHERE c.passport=:pasport");
            query.setParameter("pasport", passport);
            client = (Client) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Client not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            client.addAccount(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

    }

    public static void transaction(TrType trType) {
        String sAccIdFrom = null;
        long accIdFrom = 0;
        String sAccIdTo = null;
        long accIdTo = 0;

        switch (trType) {
            case OutTo:
                accIdFrom = 1;

                System.out.println("Enter \'id\' account \"TO\"");
                sAccIdTo = scanner.nextLine();
                accIdTo = Long.valueOf(sAccIdTo);

                break;

            case FromOut:
                System.out.println("Enter \'id\' account \"FROM\"");
                sAccIdFrom = scanner.nextLine();
                accIdFrom = Long.valueOf(sAccIdFrom);

                accIdTo = 1;

                break;

            case FromTo:
                System.out.println("Enter \'id\' account \"FROM\"");
                sAccIdFrom = scanner.nextLine();
                accIdFrom = Long.valueOf(sAccIdFrom);

                System.out.println("Enter \'id\' account \"TO\"");
                sAccIdTo = scanner.nextLine();
                accIdTo = Long.valueOf(sAccIdTo);

                break;
        }

        System.out.println("Enter anoumt of transaction");
        String sAmount = scanner.nextLine();
        double amount = Double.valueOf(sAmount);

        double amFrom = 0;
        double amTo = 0;

        Account accFrom = em.find(Account.class, accIdFrom);
        Account accTo = em.find(Account.class, accIdTo);

        if (accFrom == null || accTo == null) {
            System.out.println("Not found account");
            return;
        }

        amFrom = accFrom.getAmount();
        amTo = accTo.getAmount();

        if (trType != TrType.OutTo && amFrom < amount) {
            System.out.println("Not enough money on account");
            return;
        }

        amFrom -= amount;
        amTo += amount;

        Transaction transaction = new Transaction(amount, accFrom, accTo);

        em.getTransaction().begin();
        try {
            accFrom.setAmount(amFrom);
            accTo.setAmount(amTo);
            accFrom.addTransactionFrom(transaction);
            accTo.addTransactionTo(transaction);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void showClientsAndAccounts() {
        List<Client> clients = new ArrayList<>();
        Query query = em.createQuery("FROM Client");
        clients = query.getResultList();
        System.out.println();
        for (Client cl : clients) {
            System.out.println(cl);
        }
        System.out.println();
    }
}