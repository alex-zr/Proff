package ua.kyiv.prog.db.sessionhib;

import org.hibernate.HibernateException;

import javax.persistence.*;
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
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();

            showAllClients();
            System.out.println();

            long id = 3;
            String name = "Gleb";
            changeClientName(id, name);
            SimpleClient simpleClient = em.find(SimpleClient.class, id);
            System.out.println(simpleClient);
            System.out.println();

            id = 4;
            removeClient(id);
            System.out.println();

            SimpleClient client1 = getClient(1L);
            SimpleClient client2 = getClient(2L);
            Group group = getGroup(2L);
            addClientToGroup(client1, group);
            addClientToGroup(client2, group);

            showClients(group);

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void showAllClients() {
        Query query = em.createQuery("SELECT c FROM SimpleClient c", SimpleClient.class);
        List<SimpleClient> clients = (List<SimpleClient>) query.getResultList();

        for (SimpleClient c : clients)
            System.out.println(c);
    }

    private static void changeClientName(long id, String name) {
        SimpleClient client = getClient(id);
        if (client != null) {
            em.getTransaction().begin();
            try {
                client.setName(name);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        }
    }

    private static void removeClient(long id) {
        SimpleClient client = getClient(id);
        if (client != null) {
            em.getTransaction().begin();
            try {
                em.remove(client);
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
            }
        } else {
            System.out.println("Client not found!");
        }
    }

    private static void addClientToGroup(SimpleClient client, Group group) {
        em.getTransaction().begin();
        try {
            client.setGroup(group);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    private static SimpleClient getClient(long id) {
        SimpleClient client = em.find(SimpleClient.class, id);

        if (client == null)
            System.out.println("Client with id=" + id + "not found");

        return client;
    }

    private static Group getGroup(long id) {
        Group group = em.find(Group.class, id);

        if (group == null)
            System.out.println("Group with id=" + id + " not found");

        return group;
    }

    private static void showClients(Group group) {

    }

}
