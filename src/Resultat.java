import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Created by TrineMarie on 09.03.2016.
 */
public class Resultat {
    public static int insert(DB db) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yy");

        while (true) {
            LocalDate ld = LocalDate.now();
            int resultat = scanner.nextInt();
            System.out.println("Enhet: ");
            String enhet = "";
            while (enhet.equals(""))
                enhet = scanner.nextLine();

            String prepareUpdate = "INSERT INTO resultat(dato, resultat, enhet) VALUES (?, ?, ?)";

            try {
                PreparedStatement stmt = db.conn.prepareStatement(prepareUpdate, Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, Date.valueOf(ld));
                stmt.setInt(2, resultat);
                stmt.setString(3, enhet);
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
            catch (Exception e) {
                System.out.println("Noe gikk galt. Prøv igjen!");
            }
        }
    }
}
