package ua.com.prog.callable;

import java.sql.*;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/testproc";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password";

    public static void main(String[] args) throws SQLException{
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            try (CallableStatement cs = conn.prepareCall("{CALL Test(?, ?, ?)}")) {
                cs.setInt(1, 20); // in
                cs.setInt(2, 30); // in
                cs.registerOutParameter(3, Types.INTEGER); // out

                cs.execute();

                System.out.println(cs.getInt(3));
            }
        }
    }
}