package ua.kiev.prog.jsonToDb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import ua.kiev.prog.jpa.sample1.SimpleClient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.*;
import java.util.*;

public class JsonSimpleWriteExample {
    static EntityManagerFactory emf;
    static EntityManager em;


    public static void main(String[] args) throws IOException {


        String fileName = "c:\\java\\sample_data.json";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        JsonSimpleWriteExample jsonSimpleWriteExample = new JsonSimpleWriteExample();
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        for (Iterator<String> iter = lines.iterator(); iter.hasNext(); ) {
            String element = iter.next();
            Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
            StringReader str2 = new StringReader(element);
            JsonReader jsonReadereader = new JsonReader(str2);
            JTable jTable = gson.fromJson(jsonReadereader, JTable.class);
            if (jTable.getDatasetKey() != null) {
                emf = Persistence.createEntityManagerFactory("JPATest");
                em = emf.createEntityManager();
                try {
                    jsonSimpleWriteExample.add(jTable);
                } catch (ConstraintViolationException e) {
                    e.printStackTrace();

                }
            }


        }

        jsonSimpleWriteExample.getAll();
    }

    public void getAll() {
        try {
            TypedQuery<JTable> namedQuery = em.createNamedQuery("JTable.getAll", JTable.class);
            List<JTable> list = (List<JTable>) namedQuery.getResultList();

            for (JTable c : list) {
                System.out.println(c);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        //   return namedQuery.getResultList();
    }

    public void getClients() {
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            TypedQuery<SimpleClient> query = em.createQuery("select c from SimpleClient c", SimpleClient.class);
            List<SimpleClient> list = (List<SimpleClient>) query.getResultList();

            for (SimpleClient c : list) {
                System.out.println(c);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    public JTable add(JTable car) {
        em.getTransaction().begin();
        JTable carFromDB = em.merge(car);
        em.getTransaction().commit();
        return carFromDB;
    }
}
