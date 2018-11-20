package ua.kiev.prog.jpa.sample1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPAHomeworkMenu");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add dish");
                    System.out.println("2: print dishes in the price range");
                    System.out.println("3: print dishes with discount");
                    System.out.println("4: choose set of the dishes with total weight equal or less then 1 kg");
                    System.out.println("5: view menu");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addDish(sc);
                            break;
                        case "2":
                            printDihesInPriceRange(sc);
                            break;
                        case "3":
                            printDishesWithDiscount(sc);
                            break;
                        case "4":
                            printDishesByWeight(sc);
                            break;
                        case "5":
                            viewMenu();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public static Dish findDishByName(String name, List<Dish> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDishName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    private static void printDishesByWeight(Scanner sc) {
        Query query = em.createQuery("SELECT c FROM Dish c ", Dish.class);
        List<Dish> list = (List<Dish>) query.getResultList();

        double totalWeight = 0;

        List<Dish> dishes = new ArrayList<>();
        while (true) {
            System.out.println("Enter the dish or \"exit\" to print order ");
            String str = sc.nextLine();
            if (str.equals("exit")) {
                break;
            }
            try {
                totalWeight += findDishByName(str, list).getDishWeight();
                if (totalWeight <= 1000) {
                    dishes.add(findDishByName(str, list));
                } else {
                    break;
                }
            } catch (NullPointerException e) {
                System.out.println("Such dish doesnt exist");
            }
        }

        for (Dish c : dishes) {
            System.out.println(c);
        }
    }

    private static void printDishesWithDiscount(Scanner sc) {
        Query query = em.createQuery("SELECT c FROM Dish c WHERE c.dishDiscount != 0", Dish.class);
        List<Dish> list = (List<Dish>) query.getResultList();

        for (Dish c : list) {
            System.out.println(c);
        }
    }

    private static void printDihesInPriceRange(Scanner sc) {
        System.out.println("Enter the minimal price");
        String strMinPrice = sc.nextLine();
        Double minPrice = Double.parseDouble(strMinPrice);
        System.out.println("Enter the maximal price");
        String strMaxPrice = sc.nextLine();
        Double maxPrice = Double.parseDouble(strMaxPrice);
        Query query = em.createQuery("SELECT c FROM Dish c WHERE (c.dishPrice < :maxPrice) AND (c.dishPrice > :minPrice)", Dish.class);
        query.setParameter("maxPrice", maxPrice);
        query.setParameter("minPrice", minPrice);
        List<Dish> list = (List<Dish>) query.getResultList();

        for (Dish c : list)
            System.out.println(c);
    }

    private static void addDish(Scanner sc) {
        System.out.print("Enter dish name: ");
        String dishName = sc.nextLine();
        System.out.print("Enter dish price: ");
        String strDishPrice = sc.nextLine();
        double dishPrice = Double.parseDouble(strDishPrice);
        System.out.print("Enter dish weight: ");
        String strDishWeight = sc.nextLine();
        double dishWeight = Double.parseDouble(strDishWeight);
        System.out.print("Enter dish discount: ");
        String strDishDiscount = sc.nextLine();
        double dishDiscount = Double.parseDouble(strDishDiscount);
        em.getTransaction().begin();
        try {
            Dish c = new Dish(dishName, dishPrice, dishWeight, dishDiscount);
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }


    private static void viewMenu() {
        Query query = em.createQuery("SELECT c FROM Dish c", Dish.class);
        List<Dish> list = (List<Dish>) query.getResultList();

        for (Dish c : list)
            System.out.println(c);
    }

}


