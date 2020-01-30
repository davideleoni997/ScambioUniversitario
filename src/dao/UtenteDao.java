package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


import bean.UserBean;
import javafx.scene.image.Image;
import logic.Utente;

public class UtenteDao {
   
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
	private static final String COLUMN_LOGO = "logo";
    
    private UtenteDao() {
       //Costruttore Utente dao
      }

    public static Utente findByNameAndPassword(String username, String password) throws SQLException, ClassNotFoundException {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
       
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = "SELECT nome, username, password, cognome, company, logo, id FROM utenti where username = '"
                    + username + "' AND password = '" + password + "';";
            rs = stmt.executeQuery(sql);
         Utente u = getInfo(rs);
            
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        

        return u;
    }
    
    private static Utente getInfo(ResultSet rs) throws SQLException {
    	 Utente u = null;

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
       
        Image imgLogo = null;
        if(Boolean.TRUE.equals(company))
        {	 Blob logo =rs.getBlob(COLUMN_LOGO);
        	 InputStream out = logo.getBinaryStream();
        	 imgLogo = new Image(out);}
        u = new Utente(usernameLoaded, passwordLoaded, nome, cognome,company,id,imgLogo);
		return u;
	}

	public static int getIdByUsername(String username) throws SQLException, ClassNotFoundException {
    	Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        int id = -1;
        
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
            
       

         return id;
    }

    public static String getUsernameById(Integer id) throws SQLException, ClassNotFoundException {
    	PreparedStatement pst = null;
        Connection conn = null;
        ResultSet rs = null;
        String username = "";
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            pst = conn.prepareStatement("SELECT username FROM utenti where id = ?");
            
            pst.setInt(1, id);
            
            rs = pst.executeQuery();

            if (!rs.first()) // rs not empty
                return username;

            // lettura delle colonne "by name"
            username = rs.getString(COLUMN_USERNAME);           
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            pst.close();
            conn.close();
            
        

         return username;
    }
    
    public static Utente findByNameAndPasswordMockup(String username, String password) {
        if ("myusername".equals(username) && "mypassword".equals(password))
            return new Utente("myusername", "", "Tizio","Caio");
        else return null;
    }
    
    public static boolean update(Integer id,String nome, String cognome, String username, String password) throws SQLException, ClassNotFoundException {
    	 Connection conn = null;
         PreparedStatement pst = null;
         
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
             
        
     	
     	
    }
    
    
    public static boolean newCompany(String nome, String username, String password, Boolean company,File logo) throws SQLException, ClassNotFoundException, IOException {
    	Connection conn = null;
        PreparedStatement pst = null;
        try(FileInputStream logoStream = new FileInputStream(logo); ) {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into utenti(nome,username,password,company,logo,matricola) VALUES(?,?,?,?,?,?)");
            pst.setString(1, nome);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setBoolean(4, company);
            pst.setBinaryStream(5, logoStream,logoStream.available());
            pst.setString(6, "");
            
            pst.executeUpdate();
            pst.close();
            
            return true;
        }
    	
   }
    
    
    public static boolean newStudent(String nome, String cognome, String username, String password, Boolean company,String matricola) throws SQLException, ClassNotFoundException {
      	 Connection conn = null;
           PreparedStatement pst = null;
           
               // STEP 2: loading dinamico del driver mysql
               Class.forName(CONNECTOR);

               // STEP 3: apertura connessione
               conn = DriverManager.getConnection(DB_URL, USER, PASS);
               // STEP 4: creazione ed esecuzione della query
               //!!!RICORDA ID AUTOINCREMENT!!!
               pst = conn.prepareStatement("INSERT into utenti(nome,cognome,username,password,company,logo,matricola) VALUES(?,?,?,?,?,?,?)");
               
        
              
               pst.setString(1, nome);
               pst.setString(2, cognome);
               pst.setString(3, username);
               pst.setString(4, password);
               pst.setBoolean(5, company);
               pst.setNull(6, java.sql.Types.BLOB);
               pst.setString(7, matricola);
               
               pst.executeUpdate();
               pst.close();
               
               return true;
               
           
       	
       	
      }

	public static UserBean userFromId(Integer id) throws SQLException, ClassNotFoundException {
		// STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = "SELECT nome, username, password, cognome, company, logo, id FROM utenti where id = '"+ id + "';";
            rs = stmt.executeQuery(sql);

          Utente  u =  getInfo(rs);    
            
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
       

        return u.tobean();
	}

	
}
