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

            long id = 3L;
            changeClientName(id);
            SimpleClient simpleClient = em.find(SimpleClient.class, id);
            System.out.println(simpleClient);

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    private static void showAllClients() {
        Query query = em.createQuery("SELECT c FROM SimpleClient c", SimpleClient.class);
        List<SimpleClient> clients = (List<SimpleClient>) query.getResultList();

        for (SimpleClient c : clients)
            System.out.println(c);
    }

    private static void changeClientName(long id) {
        SimpleClient client;
        try {
            Query query = em.createQuery("SELECT c FROM SimpleClient c where c.id=:id", SimpleClient.class);
            query.setParameter("id", id);
            client = (SimpleClient) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Client not found");
            return;
        } catch (NonUniqueResultException e) {
            System.out.println("Non unique result");
            return;
        }

        em.getTransaction().begin();
        try {
            client.setName("Vadim");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

}
