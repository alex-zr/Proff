package ua.kiev.prog.jpa.sessionhib;

import org.hibernate.HibernateException;
//import org.hibernate.Query;
import sun.nio.ch.SelectorImpl;
import ua.kiev.prog.jpa.sample1.SimpleClient;
import ua.kiev.prog.jpa.sample2.Group;

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
        String name="Maks";

        App app = new App();
        app.getClients();
        app.setClientName(3L,"Yarik");
        System.out.println(app.getClientByName("Yarik"));
        app.deleteClient(4L);
        Group group = new Group();
        SimpleClient client1 = new SimpleClient("Valera",16);
        SimpleClient client2 = new SimpleClient("Kostya",19);
        group.addClient(client1);
        group.addClient(client2);
        //group.addClient(client2);
        System.out.println(group.getClients());
        //app.getClients(group.getId());

    }
    //private void addToGroup ()
    public void getClients () {
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            TypedQuery<SimpleClient> query = em.createQuery("select c from SimpleClient c",SimpleClient.class);
            List<SimpleClient> list = (List<SimpleClient>) query.getResultList();

            for (SimpleClient c: list) {
                System.out.println(c);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
    public void getClients (Long groupId) {
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            TypedQuery<SimpleClient> query = em.createQuery("select c from SimpleClient c " +
                    "where c.id = :group_id",SimpleClient.class);
            query.setParameter("group_id", groupId);
            List<SimpleClient> list = (List<SimpleClient>) query.getResultList();

            for (SimpleClient c: list) {
                System.out.println(c);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }
    public SimpleClient getClientByName (String name){
        SimpleClient c = null;
        try {
            Query query = em.createQuery("SELECT c FROM SimpleClient c " +
                    "WHERE c.name = :name", SimpleClient.class);
            query.setParameter("name", name);
            c = (SimpleClient) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Client not found!");
            //return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            //return с;
        }

        return c;
    }

    public void setClientName (Long id, String name) {
        SimpleClient c = em.find(SimpleClient.class, id);
        if (c == null) {
            System.out.println("Client not found!");
            return;
        }
        em.getTransaction().begin();
        try {
            c.setName(name);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }

    }
    /*public void setClientName (String oldName, String newName) {
        SimpleClient c = null;
        c = getClientByName(oldName);
        em.getTransaction().begin();
        try {
            c.setName(newName);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }

    } */
    /*private void deleteClient(String name) {
        //System.out.print("Enter client id: ");
        //String sId = sc.nextLine();
        //long id = Long.parseLong(sId);
        SimpleClient c = null;
        c = getClientByName(name);
        //SimpleClient c = em.find(SimpleClient.class, id);
        if (c == null) {
            System.out.println("Client not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    } */
    private static void deleteClient(Long id) {
        //System.out.print("Enter client id: ");
        //String sId = sc.nextLine();
        //long id = Long.parseLong(sId);

        SimpleClient c = em.find(SimpleClient.class, id);
        if (c == null) {
            System.out.println("Client not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

}
