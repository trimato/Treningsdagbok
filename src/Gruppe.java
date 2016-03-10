/**
 * Created by TrineMarie on 09.03.2016.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Gruppe {
    public static HashMap<Integer, String> getAll(DB db) {
        HashMap<Integer, String> groups = new HashMap<Integer, String>();
        try {
            String sql = "SELECT * FROM gruppe";
            ResultSet result = db.query(sql);
            while (result.next()) {
                groups.put(result.getInt("gruppeID"), result.getString("navn"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return groups;
    }
}
