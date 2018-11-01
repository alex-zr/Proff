package ua.kyiv.prog.db;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/rielt";
    static final String DB_USER = "user";
    static final String DB_PASSWORD = "password";
    private static int countRoom;

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

                while (true) {
                    System.out.println("1: set district? ");
                    System.out.println("2: set address? ");
                    System.out.println("3: set square? ");
                    System.out.println("4: set room count? ");
                    System.out.println("5: set price? ");
                    System.out.println("6: show result: ");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            // TODO
                            break;
                        case "2":
                            // TODO
                            break;
                        case "3":
                            // TODO
                            break;
                        case "4":
                            defineRoomCount(sc);
                            break;
                        case "5":
                            // TODO
                        case "6":
                            showResult();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void defineRoomCount(Scanner sc) throws SQLException {
        System.out.print("Enter room count: ");
        String sRoomCount = sc.nextLine();
        countRoom = Integer.parseInt(sRoomCount);
    }

    private static void showResult() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accomodation WHERE room_count=?");
        try {
            ps.setInt(1, countRoom);

            System.out.println("\nREPORT\n");
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
}