package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.Utente;

public class UtenteDao {
    private static final String ERROR_CLASS = "UtenteDao";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_COMPANY = "company";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_USERNAME = "username";
	private static final String COLUMN_COGNOME = "cognome";
	private static final String COLUMN_NOME = "nome";
	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
    
    private UtenteDao() {
        throw new IllegalStateException("Utility class");
      }

    public static Utente findByNameAndPassword(String username, String password) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        Utente u = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = "SELECT nome, username, password, cognome, company,id FROM utenti where username = '"
                    + username + "' AND password = '" + password + "';";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return null;

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per test e debug, non per codice in produzione

            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            String nome = rs.getString(COLUMN_NOME);
            String cognome = rs.getString(COLUMN_COGNOME);
            String usernameLoaded = rs.getString(COLUMN_USERNAME);
            String passwordLoaded = rs.getString(COLUMN_PASSWORD);
            Boolean company = rs.getBoolean(COLUMN_COMPANY);
            Integer id = rs.getInt(COLUMN_ID);
            
            u = new Utente(usernameLoaded, passwordLoaded, nome, cognome);
            u.setCompany(company);
            u.setId(id);
            
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
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
                if (stmt != null)
                    stmt.close();
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

        return u;
    }
    
    public static int getIdByUsername(String username) {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        int id = -1;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = "SELECT id FROM utenti where username = '"
                    + username +"';";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return -1;

            // lettura delle colonne "by name"
            id = rs.getInt(COLUMN_ID);           
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
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
                if (stmt != null)
                    stmt.close();
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

         return id;
    }

    public static Utente findByNameAndPasswordMockup(String username, String password) {
        if ("myusername".equals(username) && "mypassword".equals(password))
            return new Utente("myusername", "", "Tizio","Caio");
        else return null;
    }
    
    public static boolean update(Integer id,String nome, String cognome, String username, String password) {
    	 Connection conn = null;
         PreparedStatement pst = null;
         try {
             // STEP 2: loading dinamico del driver mysql
             Class.forName(CONNECTOR);

             // STEP 3: apertura connessione
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             // STEP 4: creazione ed esecuzione della query
             //!!!RICORDA ID AUTOINCREMENT!!!
             pst = conn.prepareStatement("UPDATE utenti set nome = ?,cognome = ?,username = ?,password = ? where id = ?");
             
            
             
             pst.setString(1, nome);
             pst.setString(2, cognome);
             pst.setString(3, username);
             pst.setString(4, password);
             pst.setInt(5, id);
             
             pst.executeUpdate();
             pst.close();
             
             return true;
             
         } catch (Exception e) {
             // Errore nel loading del driver
        	 Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
             return false;
         } finally {
         	try {
         		if(pst!=null)
         			pst.close();
         	}
         	catch(Exception e) {	
         		Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
         	}
             
             try {
                 if (conn != null)
                     conn.close();
             } catch (SQLException se) {
            	 Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,se);
             }
         }
     	
     	
    }
}