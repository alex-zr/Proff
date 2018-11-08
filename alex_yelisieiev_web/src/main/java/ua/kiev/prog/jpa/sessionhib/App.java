package ua.kiev.prog.jpa.sessionhib;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import sun.nio.ch.SelectorImpl;
import ua.kiev.prog.jpa.sample1.SimpleClient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        //Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            TypedQuery<SimpleClient> query = em.createQuery("select c from SimpleClient c",SimpleClient.class);
            List<SimpleClient> list = (List<SimpleClient>) query.getResultList();

for (SimpleClient c: list) {
    System.out.println(c.toString());
}
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

    }

}
