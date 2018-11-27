package ua.kiev.prog.patterns.pool;

public class ConnectionPool extends AbstractPool<Connection> {
    private String url;
    private String login;
    private String password;

    public ConnectionPool(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    @Override
    protected Connection create() {
        return new Connection();
        /*
        return DriverManager.getConnection(url, login, password);
         */
    }
}
