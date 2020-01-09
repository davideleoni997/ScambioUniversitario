package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.Report;

public class ReportDao {

	private static String PASS = "root";
    private static String USER = "root";
    private static String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
	
	public static Report[] getReports() {
		Report[] reports = new Report[100];
		
		Statement stmt = null;
	    Connection conn = null;
	    ResultSet rs = null;
	    try {
	    
	    Class.forName("org.mariadb.jdbc.Driver");
		
	    // STEP 3: apertura connessione
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 4: creazione ed esecuzione della query
        stmt = conn.createStatement();
        String sql = "SELECT id, insId, desc, reportFrom FROM reports;";
                 
        rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return null;

        // riposizionamento del cursore
        rs.first();

        // lettura delle colonne "by name"
        Integer id = rs.getInt("id");
        Integer id_insertion = rs.getInt("insId");
        String desc = rs.getString("desc");
        Integer repId = rs.getInt("reportFrom");
        
        int i=0;
        reports[i] = new Report(id,id_insertion,desc,repId);
        i++;
        
        while(rs.next()) {
        	id = rs.getInt("id");
            id_insertion = rs.getInt("insId");
            desc = rs.getString("desc");
            repId = rs.getInt("reportFrom");
            
            reports[i] = new Report(id,id_insertion,desc,repId);
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
		
		
		return reports;
		
	}
	
	public static Report[] mockupReports() {
		Report reports[] = new Report[100];
		
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
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into Report(insId,desc,reportFrom) values(?,?,?)");
            
           
            
            pst.setInt(1, insReported);
            pst.setString(2, desc);
            pst.setInt(3, reporter);
            
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
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    	
    	return true;
    
	}
}
