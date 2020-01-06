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
	    
	    try {
	    
	    Class.forName("org.mariadb.jdbc.Driver");
		
	    // STEP 3: apertura connessione
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 4: creazione ed esecuzione della query
        stmt = conn.createStatement();
        String sql = "SELECT id, userId, desc FROM reports;";
                 
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return null;

        // riposizionamento del cursore
        rs.first();

        // lettura delle colonne "by name"
        Integer id = rs.getInt("id");
        Integer id_user = rs.getInt("userId");
        String desc = rs.getString("desc");
        int i=0;
        reports[i] = new Report(id,id_user,desc);
        i++;
        
        while(rs.next()) {
        	id = rs.getInt("id");
            id_user = rs.getInt("userId");
            desc = rs.getString("desc");
            
            reports[i] = new Report(id,id_user,desc);
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
		
		reports[0]=new Report(0,123,"Quack");
		reports[1]=new Report(1,122,"Lorem");
		reports[2]=new Report(2,124,"Ipsum");
		
		return reports;
	}
	
	public static boolean newReport(Integer userReported,String desc) {
		

    	Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            PreparedStatement pst = conn.prepareStatement("INSERT into Report(id,userId,desc) values(?,?)");
            
           
            
            pst.setInt(1, userReported);
            pst.setString(3, desc);
            
            pst.executeUpdate();
            
            
            
            
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
            return false;
        } finally {
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
