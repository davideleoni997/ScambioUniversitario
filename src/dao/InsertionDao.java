package dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.Filters;
import logic.Insertion;

import java.sql.Date;

public class InsertionDao {

	private static String PASS = "root";
    private static String USER = "root";
    private static String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
	
    public static Insertion[] getReserach(String research, Filters filters) {
    	Insertion ins[] = new Insertion[100];
    	
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
            
            String sql = "SELECT title, descr, price, data FROM insertions where descr LIKE '%"
                    + research + "%' OR title LIKE '%"+ research + "%';";
            rs = stmt.executeQuery(sql);
           
     
            if (!rs.first()) // rs not empty
                return null;

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            String title = rs.getString("title");
            String desc = rs.getString("descr");
            String price = rs.getString("price");
            Date date = rs.getDate("data");
            int i=0;

            Insertion insert = new Insertion(title,desc,date,Integer.parseInt(price));
            
            ins[i] = insert;
            i++;
            
            while(rs.next()) {
            	title = rs.getString("title");
                desc = rs.getString("descr");
                price = rs.getString("price");
                date = rs.getDate("data");
            	
                insert = new Insertion(title,desc,date,Integer.parseInt(price));
                ins[i] = insert;
                i++;
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
        } catch (Exception e) {
            // Errore nel loading del driver
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",e);
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
            	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
            }
            
        }
    	
        return ins;
    }
    
    public static Insertion getDetail(Integer id) {
    	Insertion ins = new Insertion();
    	
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
            
            String sql = "SELECT title, descr, price, date, image1, image2, image3, seller,sold FROM insertions where id = '"+ id +"');";
            rs = stmt.executeQuery(sql);

            if (!rs.first()) // rs not empty
                return null;  //Should never happen

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            String title = rs.getString("title");
            String desc = rs.getString("desc");
            String price = rs.getString("price");
            Date date = rs.getDate("date");
            Blob images[] = new Blob[3];
            images[0] = rs.getBlob("image1");
            images[1] = rs.getBlob("image2");
            images[2] = rs.getBlob("image3");
            Integer seller = rs.getInt("seller");
            Boolean sold = rs.getBoolean("sold");
           
            
            ins = new Insertion(id,title,desc,date,Integer.parseInt(price),images,seller);
            ins.setSold(sold);
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
        } catch (Exception e) {
            // Errore nel loading del driver
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",e);
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
            	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
            }
        }
    	
        return ins;
    };
    
    public static Boolean newInsertion(String title, String desc, String price, File pics[],Integer seller) {
    	
    	
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into insertions(title,descr,data,price,image1,image2,image3,seller) values(?,?,?,?,?,?,?,?)");
            
            FileInputStream images[] = new FileInputStream[3];
            
            pst.setString(1, title);
            pst.setString(2, desc);
            pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pst.setString(4, price);
            if(pics!=null)
            for(int i=4; i< pics.length+4;i++) {
            images[i-4]=new FileInputStream(pics[i-4]);
            pst.setBinaryStream(i, images[i-4],images[i-4].available());
            }
            else {
            	pst.setNull(5,java.sql.Types.BLOB);
            	pst.setNull(6, java.sql.Types.BLOB);
            	pst.setNull(7, java.sql.Types.BLOB);
            }
            pst.setInt(8, seller);
            pst.executeUpdate();
            pst.close();
            if(pics!=null)
            for(int i=0;i<3;i++)
            	images[i].close();
            
            
            
        } catch (Exception e) {
            // Errore nel loading del driver
        	Logger.getGlobal().log(Level.WARNING,"UtenteDao",e);
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
            	Logger.getGlobal().log(Level.WARNING,"UtenteDao",se);
            }
        }
    	
    	return true;
    }
}
