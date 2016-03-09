import java.sql.*;

/**
 * Created by TrineMarie on 09.03.2016.
 */
public class DB {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/eogrotte_tdagbok";

    //database credentials
    static final String USER = "eogrotte_bruker";
    static final String PASSWORD = "penguin";

    //database connection
    static Connection conn = null;

    public boolean connect() {
        try {
            //register JDBC driver
            Class.forName(JDBC_DRIVER);

            //open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet query(String sql) {
        Statement stmt = null;
        try {
            //execute a query
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            return result;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
}
