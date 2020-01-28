package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.Report;

public class ReportDao {

	private static final String ERROR_CLASS = "UtenteDao";
	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
    
    private ReportDao() {
        throw new IllegalStateException("Utility class");
      }
	
	public static List<Report> getReports() {
		List<Report> reports = new LinkedList<>();
		
		Statement stmt = null;
	    Connection conn = null;
	    ResultSet rs = null;
	    try {
	    
	    Class.forName(CONNECTOR);
		
	    // STEP 3: apertura connessione
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 4: creazione ed esecuzione della query
        stmt = conn.createStatement();
        String sql = "SELECT id, insId, description, reportFrom FROM reports";
                 
        rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return reports;

        // riposizionamento del cursore
        rs.first();
      
        reports.add(getInfo(rs));
        
        
        while(rs.next()) {
           reports.add(getInfo(rs));
            
        }
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
		
		
		return reports;
		
	}
	
	private static Report getInfo(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
        Integer idInsertion = rs.getInt("insId");
        String desc = rs.getString("description");
        Integer repId = rs.getInt("reportFrom");
		return new Report(id,idInsertion,desc,repId);
	}

	public static Report[] mockupReports() {
		Report[] reports = new Report[100];
		
		reports[0]=new Report(0,2,"Quack",1);
		reports[1]=new Report(1,2,"Lorem",3);
		reports[2]=new Report(2,2,"Ipsum",4);
		
		return reports;
	}
	
	public static boolean newReport(Integer insReported,String desc,Integer reporter) {
		

    	
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into reports(insId,description,reportFrom) values(?,?,?)");
            
           
            
            pst.setInt(1, insReported);
            pst.setString(2, desc);
            pst.setInt(3, reporter);
            
            pst.executeUpdate();
            pst.close();
            
            
            
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
    	
    	return true;
    
	}

	public static void removeReport(Integer id) {
			Connection conn = null;
	        PreparedStatement pst = null;
	        try {
	            // STEP 2: loading dinamico del driver mysql
	            Class.forName(CONNECTOR);

	            // STEP 3: apertura connessione
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);

	            // STEP 4: creazione ed esecuzione della query
	            //!!!RICORDA ID AUTOINCREMENT!!!
	            pst = conn.prepareStatement("DELETE from reports where id = ?");
	            
	           
	            
	            pst.setInt(1, id);
	            
	            
	            pst.executeUpdate();
	            pst.close();
	            
	            
	        } catch (Exception e) {
	            // Errore nel loading del driver
	        	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
	           
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
