package com.gmail.vladgural.jpahw2;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("JPAHW2");
            em = emf.createEntityManager();

            Client cl1 = new Client("Vlad","BB123456");
            Account ac1 = new Account();
            Account ac2 = new Account();

            cl1.addAccount(ac1);
            cl1.addAccount(ac2);

            em.getTransaction().begin();
            try{
                em.persist(cl1);
                em.getTransaction().commit();
            }catch (Exception e){
                em.getTransaction().rollback();
            }

        } finally {
            scanner.close();
            em.close();
            emf.close();
        }

    }
}