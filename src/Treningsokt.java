/**
 * Created by TrineMarie on 09.03.2016.
 */
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Treningsokt {
    public static int insert(DB db) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yy");

        while (true) {
            System.out.println("Insert values.");
            LocalDate ld = LocalDate.now();
            System.out.println("Varighet: ");
            double varighet = scanner.nextDouble();
            System.out.println("Form: ");
            int form = scanner.nextInt();
            System.out.println("Prestasjon: ");
            int prestasjon = scanner.nextInt();
            System.out.println("Notat: ");
            String notat = "";
            while (notat.equals(""))
                notat = scanner.nextLine();

            String prepareUpdate = "INSERT INTO treningsokt(dato, varighet, form, prestasjon, notat) VALUES (?, ?, ?, ?, ?)";

            try {
                PreparedStatement stmt = db.conn.prepareStatement(prepareUpdate, Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, Date.valueOf(ld));
                stmt.setDouble(2, varighet);
                stmt.setInt(3, form);
                stmt.setInt(4, prestasjon);
                stmt.setString(5, notat);
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
