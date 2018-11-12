package com.gmail.vladgural.jpahw1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            emf = Persistence.createEntityManagerFactory("JPAHW1");
            em = emf.createEntityManager();
            while(true){
                System.out.println();
                System.out.println("1: Enter new dish");
                System.out.println("2: Select by price");
                System.out.println("3: Select dishes with discount only");
                System.out.println("4: Select set of dishes with total weight about 1 kg");
                System.out.println();
                String inputStr = scanner.nextLine();

                switch (inputStr){
                    case "1":
                        addNewDish(scanner);
                        break;
                    case "2":
                        selectByPrice(scanner);
                        break;
                    case "3":
                        selectWithDiscount();
                        break;
                    case "4":
                        selectByWeight();
                        break;
                    default:
                        return;
                }
            }


        }finally {
            scanner.close();
            em.close();
            emf.close();
        }

    }

    private static void addNewDish(Scanner scanner){
        String nameOfDish = "";
        Double prise = 0.0;
        Double weight = 0.0;
        String sDiscount ="";
        Boolean discount = false;

        System.out.println();
        System.out.println("Enter name of dish");
        nameOfDish = scanner.nextLine();

        while (true) {
            System.out.println("Enter price of dish");
            try {
                prise = Double.valueOf(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Incorrect data");
            }
        }

        while (true) {
            System.out.println("Enter weight of dish");
            try {
                weight = Double.valueOf(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Incorrect data");
            }
        }

        System.out.println("Is there discount? (yes = true)");
        sDiscount = scanner.nextLine();
        if(sDiscount.equals("yes"))
            discount = true;
        System.out.println();

        em.getTransaction().begin();
        try{
            MenuOfRestaurant mor = new MenuOfRestaurant(nameOfDish, prise, weight, discount);
            em.persist(mor);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
    }

    private static void selectByPrice(Scanner scanner){
        Double minPrice = 0.0;
        Double maxPrice = 0.0;

        System.out.println();
        while (true) {
            System.out.println("Enter value \"MinPrice\"");
            try {
                minPrice = Double.valueOf(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Incorrect data");
            }
        }

        while (true) {
            System.out.println("Enter value \"MaxPrice\"");
            try {
                maxPrice = Double.valueOf(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Incorrect data");
            }
        }
        System.out.println();

        List<MenuOfRestaurant> list = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM MenuOfRestaurant m WHERE " +
                                            "m.price>=:minprice AND m.price<=:maxprice");
        query.setParameter("minprice", minPrice);
        query.setParameter("maxprice",maxPrice);
        list = query.getResultList();

        for(MenuOfRestaurant menu:list)
            System.out.println(menu);
        System.out.println();
    }

    private static void selectWithDiscount(){
        List<MenuOfRestaurant> list = new ArrayList<>();
        Query query = em.createQuery("SELECT m FROM MenuOfRestaurant m WHERE " +
                                            "m.discount=true");
        list = query.getResultList();
        System.out.println();
        for(MenuOfRestaurant menu:list)
            System.out.println(menu);
        System.out.println();
    }

    private static void selectByWeight(){
        Random ran = new Random();
        Double totalWeight = 0.0;
        Double differenceWeight = 1000.0;
        Integer fail = 15;
        List<MenuOfRestaurant> setList = new ArrayList<>();

        List<MenuOfRestaurant> list = new ArrayList<>();
        Query query = em.createQuery("FROM MenuOfRestaurant ");
        list = query.getResultList();

        int size = list.size();
        while (true){
            int i = (int) (ran.nextInt(size));
            MenuOfRestaurant mor = list.get(i);
            Double weight = mor.getWeight();
            if(weight<differenceWeight) {
                setList.add(mor);
                totalWeight+=weight;
                differenceWeight-=weight;
            }
            else
                fail--;
            if(fail==0)
                break;
        }
        System.out.println();
        System.out.println("Set of dishes is");
        for(MenuOfRestaurant mor:setList)
            System.out.println(mor);
        System.out.println("Total weight is " + totalWeight + " gm");
        System.out.println();
    }
}
