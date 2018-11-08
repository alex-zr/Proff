package com.gmail.arsenycholexandra;

import org.hibernate.HibernateException;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

/*
Создать в БД (в ручную) 5 клиентов
В приложении:
1. Получить из базы и вывести на экран всех
2. Поменять имя клиенту с id 3
3. Удалить клиента с id 4
4. Добавить 2х клиентов в группу и вывести всех клиентов из группы на экран

 */
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
        showClients();
        changeName();
        //deleteClient();

    }

    public static void showClients(){
        Query query = em.createQuery("SELECT c FROM SimpleClient c", SimpleClient.class);
        List<SimpleClient> list = (List<SimpleClient>) query.getResultList();

        for (SimpleClient c : list)
            System.out.println(c);
    }

    public static void changeName(){
        Query query = em.createQuery("SELECT c FROM SimpleClient c WHERE id=3", SimpleClient.class);
        SimpleClient client = (SimpleClient) query.getSingleResult();
        System.out.println(client);
        client.setName("Mikhail");
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

//    public static void deleteClient(){
//        Query query = em.createQuery(" c FROM SimpleClient c WHERE id=4", SimpleClient.class);
//
//    }
}
