package lessonTask;

import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/*
Создать в БД (в ручную) 5 клиентов
В приложении:
1. Получить из базы и вывести на экран всех
2. Поменять имя клиенту с id 3
3. Удалить клиента с id 4
4. Добавить 2х клиентов в группу и вывести всех клиентов из группы на экран

 */
public class AppMy {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();

            //Task1
            System.out.println(getData());

            //Task2
            changeClientName(3L, "Petro");

            //Task3
            deleteClientBy_ID(4L);

            //Task4
            GroupMy groupMy = new GroupMy("man");
            groupMy.addClient(getClientBy_Id(1));
            groupMy.addClient(getClientBy_Id(2));
            showClientsFromGroup(groupMy);


        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    private static List<SimpleClientMy> showClientsFromGroup(GroupMy groupMy) {
        List<SimpleClientMy> clientMyList = groupMy.getClients();
        System.out.println(clientMyList);
        return clientMyList;
    }

    private static SimpleClientMy getClientBy_Id(long id) {
        SimpleClientMy clientMy = em.find(SimpleClientMy.class, id);
        return clientMy;
    }

    private static List<SimpleClientMy> getData() {
        List<SimpleClientMy> clients = em.createQuery("select c from SimpleClientMy c").getResultList();
        return clients;
    }

    private static void changeClientName(Long id, String name) {
        SimpleClientMy simpleClientMy = getClientBy_Id(id);
        em.getTransaction().begin();
        try {
            simpleClientMy.setName(name);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteClientBy_ID(Long id) {
        SimpleClientMy simpleClientMy1 = getClientBy_Id(id);
        em.getTransaction().begin();
        try {
            em.remove(simpleClientMy1);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}

