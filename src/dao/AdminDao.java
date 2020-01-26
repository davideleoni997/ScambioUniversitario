package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;



public class AdminDao {
	private static final String ERROR_CLASS = "AdminDao";
	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";

	private AdminDao() {
		//Non necessario, metodi statici
	}
	
	public static boolean adminLogin(String user, String pass) {
		// STEP 1: dichiarazioni
        PreparedStatement pst = null;
        Connection conn = null;
        ResultSet rs = null;
       
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            pst = conn.prepareStatement("SELECT user,pass from admins where user = ? and pass = ?");
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
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
        	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,se);
        } catch (Exception e) {
            // Errore nel loading del driver
        	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
        } finally {
        	try {
        		if(rs!=null)
        			rs.close();
        	}
        	catch(Exception e) {		
        		Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
        	}
            try {
                if (pst != null)
                    pst.close();
            } catch (SQLException se2) {
            	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,se2);
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,se);
            }
        }

        return true;
	}
	
}
