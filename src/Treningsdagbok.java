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

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //register JDBC driver
            Class.forName(JDBC_DRIVER);

            //open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            //execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM ovelse";
            ResultSet result = stmt.executeQuery(sql);
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
        catch(SQLException se) {
            //handle errors for JDBC
           se.printStackTrace();
        }
        catch(Exception e) {
            //handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            //finally block used for close resources
            try {
                if (stmt != null)
                    stmt.close();
            }
            catch(SQLException se2) {
                //nothing we can do
            }
            try {
                if (conn != null)
                    conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
            //end finally try
        }
        System.out.println("Goodbye!");
    }
}