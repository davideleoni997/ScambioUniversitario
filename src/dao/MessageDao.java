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
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()){
    	
    	
    		// STEP 1: dichiarazioni
    		ResultSet rs = null;
        
        
            // STEP 2: loading dinamico del driver mysql
    		Class.forName(CONNECTOR);

           
            
          String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender = '"+ user +"' OR `to` ='"+ user +"' GROUP BY sender ORDER BY `date` DESC;";
          rs = stmt.executeQuery(sql);

            
           
        
    	
          return getMessageList(rs);
    	}
    }
	
    
    private static Message[] getMessageList(ResultSet rs) throws SQLException {
    	Message[] messages = new Message[100];
    	
    	if (!rs.first()) // rs not empty
            return new Message[0];
    	
    	

        // riposizionamento del cursore
        rs.first();

        int i=0;

        Message msg = getInfo(rs);
        
        messages[i] = msg;
        i++;
        
        while(rs.next()) {
        	
        	
            msg = getInfo(rs);
            messages[i] = msg;
            i++;
        }

        // STEP 6: Clean-up dell'ambiente
        rs.close();
		
        return messages;
	}


	public static Message[] conversation(Integer sender) throws SQLException, ClassNotFoundException {
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()){
    	
    	
    	// STEP 1: dichiarazioni
       
        ResultSet rs = null;
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            
            
            String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender = '"+ sender +"' OR `to` ='"+ sender +"' ORDER BY `date` ASC;";
            rs = stmt.executeQuery(sql);

            
    	
        return getMessageList(rs);
    	}
    }
    
 private static Message getInfo(ResultSet rs) throws SQLException {
	 Integer sender = rs.getInt(COLUMN_SENDER);
     Integer to = rs.getInt(COLUMN_TO);
     String desc = rs.getString(COLUMN_DESC);
     Date date = rs.getDate(COLUMN_DATE);
		
     return new Message(sender,to,desc,date);
	}


   public static Boolean newMessage(Integer sender, Integer to, String desc) throws ClassNotFoundException, SQLException {
	   try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); PreparedStatement pst = conn.prepareStatement("INSERT into messages(sender,`to`,`desc`,`date`) values(?,?,?,?)")){
    	
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);
      
            
            pst.setInt(1, sender);
            pst.setInt(2, to);
            pst.setString(3, desc);
            pst.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            
            pst.executeUpdate();
                
         
        
    	
    	return true;
	   }
    }
}
