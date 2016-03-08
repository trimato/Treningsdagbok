/**
 * Created by TrineMarie on 08.03.2016.
 */

import java.sql.*;

public class Treningsdagbok {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/eogrotte_tdagbok";

    //database credentials
    static final String USER = "eogrotte_bruker";
    static final String PASSWORD = "penguin";

    //database connection
    Connection conn = null;

    public boolean connect() {
        try {
            //register JDBC driver
            Class.forName(JDBC_DRIVER);

            //open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return true;
        }
        catch(SQLException se) {
            se.printStackTrace();
            return false;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        try {
            if (conn != null)
                conn.close();
        }
        catch(SQLException se) {
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
        }
        catch(SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Treningsdagbok td = new Treningsdagbok();

        if (!td.connect()) {
            System.out.println("Failed to connect.");
            return;
        }

        try {
            System.out.println("Querying...");
            String sql = "SELECT * FROM ovelse";
            ResultSet result = td.query(sql);
            while (result.next()) {
                //retrieve by column name
                int ovelseID = result.getInt("ovelseID");
                String navn = result.getString("navn");
                String beskrivelse = result.getString("beskrivelse");
                int sett = result.getInt("sett");
                int repetisjoner = result.getInt("repetisjoner");
                int belastning = result.getInt("belastning");
                int gruppeID = result.getInt("gruppeID");
                int resultatID = result.getInt("resultatID");
                System.out.println(ovelseID + " " + navn + " " + beskrivelse + " " + sett + " " + repetisjoner + " " + belastning + " " + gruppeID + " " + resultatID);
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        td.disconnect();
        System.out.println("Goodbye!");
    }
}