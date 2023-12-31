
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;




public class databaseConnection {
    static Connection connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smdb", "root", "SQL123");
            return con;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
