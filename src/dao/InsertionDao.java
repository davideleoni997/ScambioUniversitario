package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import javafx.scene.image.Image;
import logic.Filters;
import logic.Insertion;

import java.sql.Date;

public class InsertionDao {

	private static final String ERROR_CLASS = "UtenteDao";
	private static final String COLUMN_DATA = "data";
	private static final String COLUMN_DESCR = "descr";
	private static final String COLUMN_PRICE = "price";
	private static final String COLUMN_TITLE = "title";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";
    private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_IMAGE1 = "image1";
	private static final String COLUMN_IMAGE2 = "image2";
	private static final String COLUMN_IMAGE3 = "image3";
	private static final String COLUMN_SELLER = "seller";
	private static final String COLUMN_SOLD = "sold";
	
    private InsertionDao() {
        throw new IllegalStateException("Utility class");
      }
    
    public static LinkedList<InsertionBean> getReserach(String research, Filters filters) {
    	LinkedList<InsertionBean> ins = new LinkedList<>();

    	
    	// STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            
            String sql = "SELECT title, descr, price, data, id, image1, image2, image3, seller, sold FROM insertions where descr LIKE '%"
                    + research + "%' OR title LIKE '%"+ research + "%';";
            rs = stmt.executeQuery(sql);
           
     
            if (!rs.first()) // rs not empty
                return ins;

   
            // riposizionamento del cursore
            rs.first();

            // lettura delle colonne "by name"
            String title = rs.getString(COLUMN_TITLE);
            String desc = rs.getString(COLUMN_DESCR);
            String price = rs.getString(COLUMN_PRICE);
            Date date = rs.getDate(COLUMN_DATA);
            Integer id = rs.getInt(COLUMN_ID);
            Blob image1 = rs.getBlob(COLUMN_IMAGE1);
            Blob image2 = rs.getBlob(COLUMN_IMAGE2);
            Blob image3 = rs.getBlob(COLUMN_IMAGE3);
            Integer seller = rs.getInt(COLUMN_SELLER);
            Boolean sold = rs.getBoolean(COLUMN_SOLD);
            
            InputStream out1;
            Image img1 = null;
            InputStream out2;
            Image img2 = null;
            InputStream out3;
            Image img3 = null;
            
            if(image1 != null) {
            	out1 = image1.getBinaryStream();
            	img1 = new Image(out1);}
            if(image2 != null) {
            	out2 = image2.getBinaryStream();
            	img2 = new Image(out2);}
            if(image3 != null) {
            	out3 = image3.getBinaryStream();
            	img3 = new Image(out3);}
            
            InsertionBean insert = new InsertionBean(title,desc,date,Integer.parseInt(price),id,img1,img2,img3,UtenteDao.getUsernameById(seller),seller,sold);
            
            ins.add(insert);
            
            while(rs.next()) {
            	img1 = null;
            	img2 = null;
            	img3 = null;
            	title = rs.getString(COLUMN_TITLE);
                desc = rs.getString(COLUMN_DESCR);
                price = rs.getString(COLUMN_PRICE);
                date = rs.getDate(COLUMN_DATA);
                id = rs.getInt(COLUMN_ID);
                image1 = rs.getBlob(COLUMN_IMAGE1);
                image2 = rs.getBlob(COLUMN_IMAGE2);
                image3 = rs.getBlob(COLUMN_IMAGE3);
                seller = rs.getInt(COLUMN_SELLER);
                sold = rs.getBoolean(COLUMN_SOLD);
                
                if(image1 != null) {
                    out1 = image1.getBinaryStream();
                    img1 = new Image(out1);}
                if(image2 != null) {
                    out2 = image2.getBinaryStream();
                    img2 = new Image(out2);}
                if(image3 != null) {
                    out3 = image3.getBinaryStream();
                    img3 = new Image(out3);}
                
                insert = new InsertionBean(title,desc,date,Integer.parseInt(price),id,img1,img2,img3,UtenteDao.getUsernameById(seller),seller,sold);
                ins.add(insert);
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
            Class.forName(CONNECTOR);

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
            String title = rs.getString(COLUMN_TITLE);
            String desc = rs.getString("desc");
            String price = rs.getString(COLUMN_PRICE);
            Date date = rs.getDate("date");
            Blob[] images = new Blob[3];
            images[0] = rs.getBlob(COLUMN_IMAGE1);
            images[1] = rs.getBlob(COLUMN_IMAGE2);
            images[2] = rs.getBlob(COLUMN_IMAGE3);
            Integer seller = rs.getInt(COLUMN_SELLER);
            Boolean sold = rs.getBoolean("sold");
           
            
            ins = new Insertion(id,title,desc,date,Integer.parseInt(price),images,seller);
            ins.setSold(sold);
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
    	
        return ins;
    }
    
    public static Boolean newInsertion(String title, String desc, String price, File[] pics,Integer seller) {
    	
    	
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("INSERT into insertions(title,descr,data,price,image1,image2,image3,seller) values(?,?,?,?,?,?,?,?)");
            
            FileInputStream[] images = new FileInputStream[3];
            
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
    
    
}
