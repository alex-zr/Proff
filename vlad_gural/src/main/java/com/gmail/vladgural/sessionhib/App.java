package com.gmail.vladgural.sessionhib;

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
//        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
//            SimpleClient simpleClient = em.find(SimpleClient.class, 1L);
//            System.out.println(simpleClient);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        //task1
        Query query = em.createQuery("SELECT c FROM SimpleClient c",SimpleClient.class);
        List<SimpleClient> list = (List<SimpleClient>) query.getResultList();
        for (SimpleClient sk: list)
            System.out.println(sk);

        //task2
        SimpleClient c = null;
        c = em.find(SimpleClient.class,3l);
        em.getTransaction().begin();
        try{
            c.setName("Victor");
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }

    em.close();
    return;
    }


}


