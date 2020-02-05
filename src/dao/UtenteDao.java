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
        //Check if a username and password matches a user and return an instance of it
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
       
            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT nome, username, password, cognome, company, logo, id FROM utenti where username = '"
                    + username + "' AND password = '" + password + "';";
            rs = stmt.executeQuery(sql);
         Utente u = getInfo(rs);
            
            rs.close();
            stmt.close();
            conn.close();
        

        return u;
    }
    
    private static Utente getInfo(ResultSet rs) throws SQLException {
    	//return a user from a result set
    	 Utente u = null;

        if (!rs.first()) // rs not empty
            return null;

        boolean moreThanOne = rs.first() && rs.next();
        assert !moreThanOne;

        rs.first();

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
		//Get the id of a user using its username
    	Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        int id = -1;
        
            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT id FROM utenti where username = '"
                    + username +"';";
        
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return -1;

            id = rs.getInt(COLUMN_ID);           
            
            rs.close();
            stmt.close();
            conn.close();     

         return id;
    }

    public static String getUsernameById(Integer id) throws SQLException, ClassNotFoundException {
    	//method to return a user's username using its id
    	PreparedStatement pst = null;
        Connection conn = null;
        ResultSet rs = null;
        String username = "";
        
            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            pst = conn.prepareStatement("SELECT username FROM utenti where id = ?");
            
            pst.setInt(1, id);
            
            rs = pst.executeQuery();

            if (!rs.first()) // rs not empty
                return username;

            username = rs.getString(COLUMN_USERNAME);           
            
            rs.close();
            pst.close();
            conn.close();     

         return username;
    }
   
    
    public static boolean update(Integer id,String nome, String cognome, String username, String password) throws SQLException, ClassNotFoundException {
    		//method to update an existing user in the db
    		Connection conn = null;
    		PreparedStatement pst = null;
         
             Class.forName(CONNECTOR);

             conn = DriverManager.getConnection(DB_URL, USER, PASS);

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
    	//Method to create a new user of type company in the DB
    	Connection conn = null;
        PreparedStatement pst = null;
        try(FileInputStream logoStream = new FileInputStream(logo); ) {
            
            Class.forName(CONNECTOR);

            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
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
      	 	//method to create a new user of the type student
    		Connection conn = null;
      	 	PreparedStatement pst = null;

               Class.forName(CONNECTOR);

               conn = DriverManager.getConnection(DB_URL, USER, PASS);

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
		//method that returns a bean using a user id
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        
            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT nome, username, password, cognome, company, logo, id FROM utenti where id = '"+ id + "';";
            rs = stmt.executeQuery(sql);

          Utente  u =  getInfo(rs);    

            rs.close();
            stmt.close();
            conn.close();
       
        return u.tobean();
	}

	public static boolean isCompany(String seller) throws SQLException, ClassNotFoundException {
		//Method that returns true if the username matches a company
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT company FROM utenti where username = '"+ seller + "';";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return false;
            
            Boolean ret = rs.getBoolean(COLUMN_COMPANY);    
            
            rs.close();
            stmt.close();
            conn.close();
       
        return ret;
	}

	public static Image getLogo(String seller) throws SQLException, ClassNotFoundException {
		//method that returns the logo of a company, only used if the user is a company
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;

            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT logo FROM utenti where username = '"+ seller + "';";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                throw new SQLException();
            
            Blob ret = rs.getBlob("logo");
            InputStream in = ret.getBinaryStream();
            Image img = new Image(in);
            
            rs.close();
            stmt.close();
            conn.close();   

        return img;
	}
	
}
