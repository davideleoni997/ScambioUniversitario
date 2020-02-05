package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import logic.Report;

public class ReportDao {

	
	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
    
    private ReportDao() {
        throw new IllegalStateException("Utility class");
      }
	
	public static List<Report> getReports() throws ClassNotFoundException, SQLException {
		//Method to get the reports in the system
		List<Report> reports = new LinkedList<>();
				
	    ResultSet rs = null;	    
	    
	    Class.forName(CONNECTOR);
	    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
	    Statement stmt = conn.createStatement();
	    
        String sql = "SELECT id, insId, description, reportFrom FROM reports";
                 
        rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return reports;

        rs.first();
      
        reports.add(getInfo(rs));
        
        
        while(rs.next()) {
           reports.add(getInfo(rs));
            
        }

        rs.close();
        stmt.close();
        conn.close();		
		
		return reports;		
	}
	
	private static Report getInfo(ResultSet rs) throws SQLException {
		//return a report from a ResultSet
		Integer id = rs.getInt("id");
        Integer idInsertion = rs.getInt("insId");
        String desc = rs.getString("description");
        Integer repId = rs.getInt("reportFrom");
		return new Report(id,idInsertion,desc,repId);
	}
	
	public static boolean newReport(Integer insReported,String desc,Integer reporter) throws SQLException, ClassNotFoundException {
			//Method to create a new report
            Class.forName(CONNECTOR);          
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst = conn.prepareStatement("INSERT into reports(insId,description,reportFrom) values(?,?,?)");
            
            pst.setInt(1, insReported);
            pst.setString(2, desc);
            pst.setInt(3, reporter);
            
            pst.executeUpdate();
            pst.close();
            conn.close();       
    	
    	return true;
		
	}

	public static void removeReport(Integer id) throws ClassNotFoundException, SQLException {
				//method tor emove a report from the DB
	            Class.forName(CONNECTOR);
	            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            PreparedStatement pst = conn.prepareStatement("DELETE from reports where id = ?");
	            
	            pst.setInt(1, id);            
	            
	            pst.executeUpdate();
	            pst.close();
	            conn.close();	
		
	}
}
