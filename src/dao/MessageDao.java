package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import logic.Message;


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
    	//Method to retrieve a list of messages where the user is the sender or the recipient
    		ResultSet rs = null;
        
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
    
	
    
    private static List<Message> getMessageList(ResultSet rs) throws SQLException, ClassNotFoundException {
    	//method that returns a list of messages from a resultSet
    	List<Message> messages = new LinkedList<>();
    	
    	if (!rs.first()) // rs not empty
            return messages;
    	
        rs.first();     
        
        messages.add(getInfo(rs));
        
        while(rs.next()) {       	
        	
        	messages.add(getInfo(rs));
        }

        rs.close();
		
        return messages;
	}


	public static List<Message> conversation(Integer sender,Integer to) throws SQLException, ClassNotFoundException {
    	//Method to get a conversation between two users
       
        ResultSet rs = null;
        
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender ='"+ sender +"'AND `to` ='"+to +"' ORDER BY `date` ASC;";
            rs = stmt.executeQuery(sql);

            List<Message> list = getMessageList(rs);
            rs.close();
            
            if(sender != to) {
            sql = "SELECT sender, `to`, `desc`, `date` FROM messages where sender ='"+ to +"'AND `to` ='"+ sender +"' ORDER BY `date` ASC;";
            rs = stmt.executeQuery(sql);
            list.addAll(getMessageList(rs));
            list.sort((m1,m2) -> m1.getDate().compareTo(m2.getDate()));

            rs.close();
            }
            stmt.close();
            conn.close();
    	
        return list;
    	}
    
    
 private static Message getInfo(ResultSet rs) throws SQLException, ClassNotFoundException {
	 // returns a message from a resultSet
	 Integer sender = rs.getInt(COLUMN_SENDER);
     Integer to = rs.getInt(COLUMN_TO);
     String desc = rs.getString(COLUMN_DESC);
     Timestamp date = rs.getTimestamp(COLUMN_DATE);
     Message msg = new Message(sender,to,desc,date);
     msg.setSenderName(UtenteDao.getUsernameById(sender));
     return msg;
	}


   public static Boolean newMessage(Integer sender, Integer to, String desc) throws ClassNotFoundException, SQLException {
	  //Method to create a new message
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst = conn.prepareStatement("INSERT into messages(sender,`to`,`desc`,`date`) values(?,?,?,?)");
            
            pst.setInt(1, sender);
            pst.setInt(2, to);
            pst.setString(3, desc);
            pst.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            pst.executeUpdate();    
            pst.close();
            conn.close();
    	return true;
	   
    }
}
