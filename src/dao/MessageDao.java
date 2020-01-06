package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

import logic.Message;

import java.sql.Date;

public class MessageDao {

	private static String PASS = "root";
    private static String USER = "root";
    private static String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
	
    public static Message[] messageList(String user) {
    	Message messages[] = new Message[100];
    	
    	// STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            
            String sql = "SELECT sender, to, desc, date FROM messages where sender = '"+ user +"' OR to ='"+ user +"' GROUP BY sender ORDER BY date DESC;";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return null;

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            Integer sender = rs.getInt("sender");
            Integer to = rs.getInt("to");
            String desc = rs.getString("desc");
            Date date = rs.getDate("date");
            

            int i=0;

            Message msg = new Message(sender,to,desc,date);
            
            messages[i] = msg;
            i++;
            
            while(rs.next()) {
            	sender= rs.getInt("title");
                desc = rs.getString("desc");
                to = rs.getInt("to");
                date = rs.getDate("date");
            	
                msg = new Message(sender,to,desc,date);
                messages[i] = msg;
                i++;
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
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
                se.printStackTrace();
            }
        }
    	
        return messages;
    }
	
    
    public static Message[] conversation(Integer sender) {
    	Message messages[] = new Message[100];
    	
    	// STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            
            String sql = "SELECT sender, to, desc, date FROM messages where sender = '"+ sender +"' OR to ='"+ sender +"' ORDER BY date ASC;";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return null;

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            sender = rs.getInt("sender");
            Integer to = rs.getInt("to");
            String desc = rs.getString("desc");
            Date date = rs.getDate("date");
            

            int i=0;

            Message msg = new Message(sender,to,desc,date);
            
            messages[i] = msg;
            i++;
            
            while(rs.next()) {
            	sender= rs.getInt("title");
                desc = rs.getString("desc");
                to = rs.getInt("to");
                date = rs.getDate("date");
            	
                msg = new Message(sender,to,desc,date);
                messages[i] = msg;
                i++;
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
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
                se.printStackTrace();
            }
        }
    	
        return messages;
    }
    
public Boolean newMessage(Integer sender, Integer to, String desc) {
    	
    	Statement stmt = null;
        Connection conn = null;
        PreparedStatement pst= null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into messages(sender,to,desc,data) values(?,?,?,?)");
            
         
            
            pst.setInt(1, sender);
            pst.setInt(2, to);
            pst.setString(3, desc);
            pst.setDate(4, (Date) Date.from(Instant.now()));
            
            pst.executeUpdate();
                
            pst.close();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
            return false;
        } finally {
        	try {
        		if(pst!=null)
        		pst.close();
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
                se.printStackTrace();
            }
        }
    	
    	return true;
    }
}
