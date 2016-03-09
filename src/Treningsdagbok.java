/**
 * Created by TrineMarie on 08.03.2016.
 */

import java.sql.*;
import java.util.Scanner;

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

    public USECASE1 {

    }

    public USECASE2 {

    }

    public USECASE3 {

    }

    public static void main(String[] args) {
        Treningsdagbok td = new Treningsdagbok();
        Statement stmt = null;
        stmt = conn.createStatement();
        Scanner scanner = new Scanner(System.in);

        if (!td.connect()) {
            System.out.println("Failed to connect.");
            return;
        }

        System.out.println("What do you what to do today?");
        System.out.println("Enter one of the following choices:");
        System.out.println("1: Insert information about treningsokt");
        System.out.println("2: Retrieve information about sessions the last week");
        System.out.println("3: Retrieve top 3 best knebøy-sessions");
        System.out.println("4: Exit");
        System.out.println();
        System.out.println("Enter \"1\", \"2\" or \"3\"");
        int choice = scanner.nextInt();

        while ((choice > 0) && (choice < 5)) {
            if (choice == 1) {
                System.out.println("Insert values:");
                System.out.println("treningsID?");
                int treningsID = scanner.nextInt();
                System.out.println("dato?");
                Date dato = scanner.nextLine();
                System.out.println("varighet?");
                int varighet = scanner.nextInt();
                System.out.println("form?");
                String form = scanner.nextLine();
                System.out.println("prestasjon?");
                String prestasjon = scanner.nextLine();
                System.out.println("notat?");
                String notat = scanner.nextLine();

                try {
                    stmt.executeUpdate("INSERT INTO treningsokt + VALUES(treningsID, dato, varighet, form, prestasjon, notat)");
                }
                catch (SQLException se) {
                    se.printStackTrace();
                }

                try {
                    System.out.println("Querying treningsokt...");
                    String sql = "SELECT * FROM treningsokt";
                    ResultSet result = td.query(sql);
                    while (result.next()) {
                        //retrieve by column name
                        int treningsID1 = result.getInt("treningsID");
                        Date dato1 = result.getDate("dato");
                        double varighet1 = result.getDouble("varighet");
                        int form1 = result.getInt("form");
                        int prestasjon1 = result.getInt("prestasjon");
                        String notat1 = result.getString("notat");
                        System.out.println(treningsID + " " + dato + " " + varighet + " " + form + " " + prestasjon + " " + notat);
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            } else if (choice == 2) {
                //KODE EVEN
            } else if (choice == 3) {
                //KODE KATRINE
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                return;
            }
        }
    }
}