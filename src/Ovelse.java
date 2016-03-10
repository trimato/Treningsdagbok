/**
 * Created by TrineMarie on 09.03.2016.
 */
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Ovelse {
    public static int insert(DB db, int tID) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yy");

        while (true) {
            System.out.println("Insert ovelse? (y/n): ");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("n")) {
                return -1;
            }

            System.out.println("Insert values.");
            System.out.println("Navn: ");
            String navn = scanner.nextLine();
            System.out.println("Beskrivelse: ");
            String beskrivelse = scanner.nextLine();
            System.out.println("Sett: ");
            int sett = scanner.nextInt();
            System.out.println("Repetisjoner: ");
            int repetisjoner = scanner.nextInt();
            System.out.println("Belastning: ");
            int belastning = scanner.nextInt();
            System.out.println("Velg gruppeID. ");
            HashMap<Integer, String> groups = Gruppe.getAll(db);
            for (Integer key : groups.keySet()) {
                System.out.println(key + ": " + groups.get(key));
            }
            int gruppeID = -1;
            while (true) {
                gruppeID = scanner.nextInt();
                if (groups.containsKey(gruppeID)) {
                    break;
                } else {
                    System.out.println("Ikke gyldig.");
                    System.out.println("Prøv på nytt: ");
                }
            }
            System.out.println("Resultat: ");
            int resultatID = Resultat.insert(db);

            String prepareUpdate = "INSERT INTO ovelse(navn, beskrivelse, sett, repetisjoner, belastning, gruppeID, resultatID) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement stmt = db.conn.prepareStatement(prepareUpdate, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, navn);
                stmt.setString(2, beskrivelse);
                stmt.setInt(3, sett);
                stmt.setInt(4, repetisjoner);
                stmt.setInt(5, belastning);
                stmt.setInt(6, gruppeID);
                stmt.setInt(7, resultatID);
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                System.out.println("Noe gikk galt. Prøv igjen!");
            }
        }
    }
}
