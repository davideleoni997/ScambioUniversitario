package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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
	
    public static List<Message> messageList(Integer user) throws SQLException, ClassNotFoundException {
    	
    	
    	
    		// STEP 1: dichiarazioni
    		ResultSet rs = null;
        
        
            // STEP 2: loading dinamico del driver mysql
    		Class.forName(CONNECTOR);
    		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    		Statement stmt = conn.createStatement();
            
          String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender = '"+ user +"' OR `to` ='"+ user +"' GROUP BY sender ORDER BY `date` DESC;";
          rs = stmt.executeQuery(sql);

          
           List<Message> list = getMessageList(rs);
           stmt.close();
           conn.close();
    	
          return list;
    	}
    
	
    
    private static List<Message> getMessageList(ResultSet rs) throws SQLException {
    	List<Message> messages = new LinkedList<>();
    	
    	if (!rs.first()) // rs not empty
            return messages;
    	
    	

        // riposizionamento del cursore
        rs.first();
      
        
        messages.add(getInfo(rs));
        
        while(rs.next()) {
        	
        	
        	messages.add(getInfo(rs));
        }

        // STEP 6: Clean-up dell'ambiente
        rs.close();
		
        return messages;
	}


	public static List<Message> conversation(Integer sender) throws SQLException, ClassNotFoundException {
    	
    	
    	
    	// STEP 1: dichiarazioni
       
        ResultSet rs = null;
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender = '"+ sender +"' OR `to` ='"+ sender +"' ORDER BY `date` ASC;";
            rs = stmt.executeQuery(sql);

            List<Message> list = getMessageList(rs);
            stmt.close();
            conn.close();
    	
        return list;
    	}
    
    
 private static Message getInfo(ResultSet rs) throws SQLException {
	 Integer sender = rs.getInt(COLUMN_SENDER);
     Integer to = rs.getInt(COLUMN_TO);
     String desc = rs.getString(COLUMN_DESC);
     Date date = rs.getDate(COLUMN_DATE);
		
     return new Message(sender,to,desc,date);
	}


   public static Boolean newMessage(Integer sender, Integer to, String desc) throws ClassNotFoundException, SQLException {
	  
    	
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst = conn.prepareStatement("INSERT into messages(sender,`to`,`desc`,`date`) values(?,?,?,?)");
            
            pst.setInt(1, sender);
            pst.setInt(2, to);
            pst.setString(3, desc);
            pst.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            
            pst.executeUpdate();
                
         
        
    	
    	return true;
	   
    }
}
