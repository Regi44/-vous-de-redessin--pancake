package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    /**
     *
     */
    public static Connection con = null;

    public static Connection getConnection() {
        try {
            // Charger le pilote de la base de donnees
            Class.forName("com.mysql.jdbc.Driver");
            // Etablissement de la connection a la BD
            String url = "jdbc:mysql://localhost/gestion_minist";
            con = DriverManager.getConnection(url, "root", "");

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
