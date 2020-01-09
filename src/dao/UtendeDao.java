package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.Utente;

public class UtendeDao {
    private static String PASS = "root";
    private static String USER = "root";
    private static String DB_URL = "jdbc:mariadb://localhost:3306/scambio";

    public static Utente findByNameAndPassword(String username, String password) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        Utente u = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

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
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            String usernameLoaded = rs.getString("username");
            String passwordLoaded = rs.getString("password");
            Boolean company = rs.getBoolean("company");
            Integer id = rs.getInt("id");
            
            u = new Utente(usernameLoaded, passwordLoaded, nome, cognome);
            u.setCompany(company);
            u.setId(id);
            
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
        } catch (Exception e) {
            // Errore nel loading del driver
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",e);
        } finally {
        	try {
        		if(rs!=null)
        		rs.close();
        	}
        	catch(Exception e) {		
        	}
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
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
            Class.forName("org.mariadb.jdbc.Driver");

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
            id = rs.getInt("id");           
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
        } catch (Exception e) {
            // Errore nel loading del driver
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",e);
        } finally {
        	try {
        		if(rs!=null)
        		rs.close();
        	}
        	catch(Exception e) {		
        	}
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
            	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
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
             Class.forName("org.mariadb.jdbc.Driver");

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
        	 Logger.getGlobal().log(Level.WARNING,"UtenteDao",e);
             return false;
         } finally {
         	try {
         		if(pst!=null)
         		pst.close();
         	}
         	catch(Exception e) {		
         	}
             
             try {
                 if (conn != null)
                     conn.close();
             } catch (SQLException se) {
            	 Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
             }
         }
     	
     	
    }
}
