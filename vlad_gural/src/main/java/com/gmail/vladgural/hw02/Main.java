package com.gmail.vladgural.hw02;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/orderdb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Cod3281291";

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            try {
                while (true) {
                    System.out.println("1: add new customer");
                    System.out.println("2: add new product");
                    System.out.println("3: show customers");
                    System.out.println("4: show products");
                    System.out.println("5: create Order");
                    System.out.println("6: show orders");


                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addCustomer(sc);
                            break;
                        case "2":
                            addProduct(sc);
                            break;
                        case "3":
                            showCustomers();
                            break;
                        case "4":
                            showProducts();
                            break;
                        case "5":
                            createOrder(sc);
                            break;
                        case "6":
                            showOrders();
                            break;
                        default:
                            return;
                    }
                }

            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    private static void addCustomer(Scanner sc) throws SQLException {
        System.out.println("Enter customer name");
        String name = sc.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO customers (name, phone_number) VALUES (?,?)");
        try {
            ps.setString(1, name);
            ps.setString(2, phoneNumber);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void addProduct(Scanner sc) throws SQLException {
        String name = null;
        double price = 0;

        System.out.println("Enter product name");
        name = sc.nextLine();
        while (true) {
            System.out.println("Enter price of product");
            try {
                price = Double.valueOf(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorect number");
            }
        }
        PreparedStatement ps = conn.prepareStatement("INSERT INTO products (name, price) VALUES (?, ?)");
        try {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void showCustomers() throws SQLException {
        int[] lengthOfColumn = {4, 8, 18};


        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers")) {
            System.out.println("Customers table is");
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(String.format("%" + lengthOfColumn[i - 1] + "s", md.getColumnName(i)));
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(String.format("%" + lengthOfColumn[i - 1] + "s", rs.getString(i)));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    private static void showProducts() throws SQLException {
        int[] lengthOfColumn = {4, 14, 8};

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM products")) {
            System.out.println("Products table is");
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(String.format("%" + lengthOfColumn[i - 1] + "s", md.getColumnName(i)));
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(String.format("%" + lengthOfColumn[i - 1] + "s", rs.getString(i)));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }

    }

    private static void createOrder(Scanner sc) throws SQLException {
        String customerName = null;
        int customerID = 0;
        String productName = null;
        int productID = 0;
        double price = 0;
        int quantity = 0;
        double cost = 0;

        try (Statement st = conn.createStatement()) {
            while (true) {
                System.out.println("Enter name of customer");
                customerName = sc.nextLine();
                try (ResultSet rs = st.executeQuery("SELECT * FROM customers WHERE name='" + customerName + "'")) {
                    while (rs.next()) {
                        customerID = rs.getInt(1);
                    }
                    if (customerID != 0)
                        break;
                    System.out.println("Incorrect customer name");
                }
            }
            while (true) {
                System.out.println("Enter name of product");
                productName = sc.nextLine();
                try (ResultSet rs = st.executeQuery("SELECT * FROM products WHERE name='" + productName + "'")) {
                    while (rs.next()) {
                        productID = rs.getInt(1);
                        price = rs.getDouble(3);
                    }
                    if (productID != 0)
                        break;
                    System.out.println("Incorrect customer name");
                }
            }
            while (true) {
                System.out.println("Enter quantity of product");
                try {
                    quantity = Integer.valueOf(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Incorect number");
                }
            }

            cost = quantity * price;

            st.executeUpdate("INSERT INTO orders " +
                    "(customer_id, product_id, quantity, cost) " +
                    "VALUES " +
                    "(" + customerID + ", " + productID + ", " + quantity + ", " + cost + ")");

        }
    }

    private static void showOrders() throws SQLException {
        int[] widthOfColumn = {10, 15, 20, 20, 15};
        String[] nameOfColumn = {"Order #", "Customer name", "Product name", "Product quantity", "Order cost"};
        String query = "SELECT orders.id, customers.name, products.name, orders.quantity, orders.cost " +
                "FROM customers, products, orders " +
                "WHERE customer_id = customers.id AND product_id = products.id " +
                "ORDER BY orders.id";


        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery(query)) {
                for (int i = 1; i <= 5; i++) {
                    System.out.print(String.format("%" + widthOfColumn[i - 1] + "s", nameOfColumn[i - 1]));
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= 5; i++) {
                        System.out.print(String.format("%" + widthOfColumn[i - 1] + "s", rs.getString(i)));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

}
