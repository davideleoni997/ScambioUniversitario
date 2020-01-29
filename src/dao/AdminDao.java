package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class AdminDao {

	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";

	private AdminDao() {
		//Non necessario, metodi statici
	}
	
	public static boolean adminLogin(String user, String pass) throws ClassNotFoundException, SQLException {
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);PreparedStatement pst =conn.prepareStatement("SELECT user,pass from admins where user = ? and pass = ?")){// STEP 1: dichiarazioni
        ResultSet rs = null;
       
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            
            // STEP 4: creazione ed esecuzione della query
          
            pst.setString(1, user);
            pst.setString(2, pass);
            rs = pst.executeQuery();

            if (!rs.first()) // rs not empty
                return false;

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione

            // riposizionamento del cursore
            rs.first();
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            pst.close();
            conn.close();       

            return true;
		}
	}
	
}
