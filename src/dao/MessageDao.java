package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import logic.Message;

import java.sql.Date;

public class MessageDao {
	
	
	private static final String COLUMN_DATE = "date";
	private static final String COLUMN_DESC = "desc";
	private static final String COLUMN_TO = "to";
	private static final String COLUMN_SENDER = "sender";
	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";

	private MessageDao() {
        throw new IllegalStateException("Utility class");
      }

	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
	
    public static Message[] messageList(Integer user) throws SQLException, ClassNotFoundException {
    	Message[] messages = new Message[100];
    	
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
            
            String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender = '"+ user +"' OR `to` ='"+ user +"' GROUP BY sender ORDER BY `date` DESC;";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return new Message[0];

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            Integer sender = rs.getInt(COLUMN_SENDER);
            Integer to = rs.getInt(COLUMN_TO);
            String desc = rs.getString(COLUMN_DESC);
            Date date = rs.getDate(COLUMN_DATE);
            

            int i=0;

            Message msg = new Message(sender,to,desc,date);
            
            messages[i] = msg;
            i++;
            
            while(rs.next()) {
            	sender= rs.getInt("title");
                desc = rs.getString(COLUMN_DESC);
                to = rs.getInt(COLUMN_TO);
                date = rs.getDate(COLUMN_DATE);
            	
                msg = new Message(sender,to,desc,date);
                messages[i] = msg;
                i++;
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        
    	
        return messages;
    }
	
    
    public static Message[] conversation(Integer sender) throws SQLException, ClassNotFoundException {
    	Message[] messages = new Message[100];
    	
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
            
            String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender = '"+ sender +"' OR `to` ='"+ sender +"' ORDER BY `date` ASC;";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return new Message[0];

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            sender = rs.getInt(COLUMN_SENDER);
            Integer to = rs.getInt(COLUMN_TO);
            String desc = rs.getString(COLUMN_DESC);
            Date date = rs.getDate(COLUMN_DATE);
            

            int i=0;

            Message msg = new Message(sender,to,desc,date);
            
            messages[i] = msg;
            i++;
            
            while(rs.next()) {
            	sender= rs.getInt(COLUMN_SENDER);
                desc = rs.getString(COLUMN_DESC);
                to = rs.getInt(COLUMN_TO);
                date = rs.getDate(COLUMN_DATE);
            	
                msg = new Message(sender,to,desc,date);
                messages[i] = msg;
                i++;
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        
    	
        return messages;
    }
    
    public static Boolean newMessage(Integer sender, Integer to, String desc) throws ClassNotFoundException, SQLException {
    	
    	
        Connection conn = null;
        PreparedStatement pst= null;
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into messages(sender,`to`,`desc`,`date`) values(?,?,?,?)");
            
         
            
            pst.setInt(1, sender);
            pst.setInt(2, to);
            pst.setString(3, desc);
            pst.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            
            pst.executeUpdate();
                
            pst.close();
        
    	
    	return true;
    }
}
