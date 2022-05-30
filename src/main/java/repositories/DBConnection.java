package repositories;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/eap";
    private static final String user = "radum";
    private static final String pass = "1234";

    public static String getUrl() { return url; }
    public static String getUser() { return user; }
    public static String getPass() { return pass; }
}
