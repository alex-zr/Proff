package ua.kiev.prog.patterns.pool;

public class Main {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool("url", "login", "password");

        Connection conn1 = pool.get();
        Connection conn2 = pool.get();
        Connection conn3 = pool.get();
        System.out.println(pool);
        // Pool available=0, inUse=3

        pool.put(conn1);
        System.out.println(pool);
        // Pool available=1, inUse=2

        pool.put(conn2);
        pool.put(conn3);
        System.out.println(pool);
        // Pool available=3, inUse=0
    }
}
