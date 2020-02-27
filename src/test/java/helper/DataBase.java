package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DataBase {

    DataBase dbs = new DataBase();

    static String JDBC_DRIVER = "org.postgresql.Driver";
    static String USER = "root";
    static String PASS = "fly12345";
    public static String data;



    public static void execute_query_dbs(String hostName, String dbsName, String query) {
        try {
            String url = "jdbc:postgresql://" + hostName + "/" + dbsName;

            // no need to call Class.forName(driver) from JDK 6 onward
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                System.out.println("ResultSet is empty");
            } else {
                do {
                    data = rs.getString("id");
                }
                while (rs.next());
            }
        } catch (Exception e) {
            System.out.println("ResultSet is empty " + e.getMessage());

        }
    }

    public static void execute_update(String hostName, String dbsName, String query) {
        try {
            String url = "jdbc:postgresql://" + hostName + "/" + dbsName;

            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);
            if (rs > 0) {
                System.out.println("inserted");
            } else {

                System.out.println("not inserted");


            }
        } catch (Exception e) {
            System.out.println("ResultSet is empty " + e.getMessage());

        }
    }
}
