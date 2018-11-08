package ua.kiev.prog.jpa.sessionhib;

import org.hibernate.HibernateException;
import ua.kiev.prog.jpa.sample1.SimpleClient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            SimpleClient simpleClient = em.find(SimpleClient.class, 1L);
            System.out.println(simpleClient);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
}
