/**
 * Created by TrineMarie on 08.03.2016.
 */

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Treningsdagbok {
    DB db = new DB();
    public Treningsdagbok() {
        db.connect();
        System.out.println("Connected to database...");
    }

    public void insertTreningsoktAndOvelse() {
        int tID = Treningsokt.insert(db);
        while (true) {
            int oID = Ovelse.insert(db, tID);
            if (oID == -1)
                break;
        }
    }

    public static void printMenu() {
        System.out.println("What do you what to do today?");
        System.out.println("Enter one of the following choices:");
        System.out.println("1: Insert information about treningsokt");
        System.out.println("2: Retrieve information about sessions the last week");
        System.out.println("3: Retrieve top 3 best knebøy-sessions");
        System.out.println("4: Exit");
        System.out.println();
        System.out.println("Enter \"1\", \"2\" or \"3\"");
    }

    public static void main(String[] args) {
        Treningsdagbok td = new Treningsdagbok();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            if (choice == 1) {
                td.insertTreningsoktAndOvelse();
            } else if (choice == 2) {
                //KODE EVEN
            } else if (choice == 3) {
                //KODE KATRINE
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            }
        }

        td.db.disconnect();
    }
}