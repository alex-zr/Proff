package com.gmail.arsenycholexandra;

import org.hibernate.HibernateException;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.management.LockInfo;
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
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        showClients();
        changeName(3L, "Stacy");
        deleteClient(2L);
        Group group = getGroup(2L);
        addClientToGroup(1L, group);
        addClientToGroup(3L, group);
        showClientsFromGroup(group);

    }

    public static void showClients() {
        Query query = em.createQuery("SELECT c FROM SimpleClient c", SimpleClient.class);
        List<SimpleClient> list = (List<SimpleClient>) query.getResultList();

        for (SimpleClient c : list)
            System.out.println(c);
    }

    public static void changeName(Long l, String name) {
        SimpleClient client = em.find(SimpleClient.class, l);
        System.out.println(client);
        try {
            em.getTransaction().begin();
            client.setName(name);
            em.getTransaction().commit();
            System.out.println("Name of client with id = " + l + " was changed in database.");
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void deleteClient(Long l) {
        SimpleClient client = em.find(SimpleClient.class, l);
        try {
            em.getTransaction().begin();
            em.remove(client);
            em.getTransaction().commit();
            System.out.println("Client with id = " + l + " is removed from database.");
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }

    }

    private static Group getGroup(Long l) {
        Group group = em.find(Group.class, l);
        return group;
    }

    public static void addClientToGroup(Long l, Group group) {
        SimpleClient client = em.find(SimpleClient.class, l);
        try {
            em.getTransaction().begin();
            client.setGroup(group);
            em.getTransaction().commit();
            System.out.println("Client with id = " + l + " added to group.");
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void showClientsFromGroup(Group group) {
        System.out.println("List of clients from group " + group.getName() + " : ");
        for (SimpleClient client : group.getClients()) {
            System.out.println(client);
        }
    }
}
