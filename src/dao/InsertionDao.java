package dao;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
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
import java.util.List;

import bean.InsertionBean;
import javafx.scene.image.Image;
import logic.BasicInformations;
import logic.Filters;
import logic.Insertion;

import java.sql.Date;

public class InsertionDao {

	
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
    
    public static List<InsertionBean> getReserach(String research, Filters filters) throws SQLException, ClassNotFoundException {
    	LinkedList<InsertionBean> ins = new LinkedList<>();
    	
    	if (filters == null)
    		filters = new Filters();
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()){
        
        
        ResultSet rs = null;
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            

            // STEP 4: creazione ed esecuzione della query
          
            
            String sql = "SELECT title, descr, price, data, id, image1, image2, image3, seller, sold FROM insertions where title LIKE '%"+ research + "%' ";
            
            if(!filters.getUniversity().isEmpty())
            	sql = sql + "AND university LIKE '%" + filters.getUniversity() + "%' ";
            
            if(!filters.getCity().isEmpty())
            	sql = sql + "AND city LIKE '%" + filters.getCity() + "%' ";
            
            if(!filters.getSubject().isEmpty())
            	sql = sql + "AND subject LIKE '%" + filters.getSubject() + "%' ";
            
            if(!filters.getBook())
            	sql = sql + "AND book = 0 ";
            
            if(!filters.getNotes())
            	sql = sql + "AND notes = 0 ";
            
            if (filters.getDate().equals(logic.Filters.Date.NEW))
            	sql = sql + "ORDER BY data desc";
            else
            	sql = sql + "ORDER BY data asc";
            
            
            rs = stmt.executeQuery(sql);
           
     
            if (!rs.first()) // rs not empty
                return ins;

   
            // riposizionamento del cursore
            rs.first();

            ins.add(getInfo(rs));
            
            while(rs.next()) {
            	
            ins.add(getInfo(rs));
            }

            // STEP 6: Clean-up dell'ambiente
            rs.close();
            
        
            return ins;
    	}
    }
    
    private static InsertionBean getInfo(ResultSet rs) throws SQLException, ClassNotFoundException {
    	// lettura delle colonne "by name"
        String title = rs.getString(COLUMN_TITLE);
        String desc = rs.getString(COLUMN_DESCR);
        String price = rs.getString(COLUMN_PRICE);
        Date date = rs.getDate(COLUMN_DATA);
        Integer id = rs.getInt(COLUMN_ID);
        LinkedList<Blob> images = new LinkedList<>();
        Blob blob;
        if(( blob = rs.getBlob(COLUMN_IMAGE1)) != null)
        	images.add(blob);
        if(( blob = rs.getBlob(COLUMN_IMAGE2)) != null)
        	images.add(blob);
        if(( blob = rs.getBlob(COLUMN_IMAGE3)) != null)
        	images.add(blob);
        Integer seller = rs.getInt(COLUMN_SELLER);
        Boolean sold = rs.getBoolean(COLUMN_SOLD);
        LinkedList<Image> imagesList = new LinkedList<>();
       
        for(Blob img : images) {
        		
        		InputStream imgIn = img.getBinaryStream();
        		Image listItem = new Image(imgIn);
        		imagesList.add(listItem);
        		
        	
        }
        
       BasicInformations basic = new BasicInformations(title,desc,date,Integer.parseInt(price));
       return new InsertionBean(basic,id,imagesList,UtenteDao.getUsernameById(seller),seller,sold);
        
		
	}

	public static Insertion getDetail(Integer id) throws SQLException, ClassNotFoundException {
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()){
    	Insertion ins;
    	
    	// STEP 1: dichiarazioni
        
        ResultSet rs = null;
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            
            
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
           
            BasicInformations basic = new BasicInformations(title,desc,date,Integer.parseInt(price));
            ins = new Insertion(id,basic,images,seller);
            ins.setSold(sold);
            // STEP 6: Clean-up dell'ambiente
            rs.close();
            
        
    	
        return ins;
		}
    }
    
    public static Boolean newInsertion(BasicInformations basic, List<File> pics,Integer seller,Filters filter) throws SQLException, ClassNotFoundException, IOException {
    	
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); PreparedStatement pst = conn.prepareStatement("INSERT into insertions(title,descr,data,price,image1,image2,image3,seller,university,city,subject,book,notes) values(?,?,?,?,?,?,?,?,?,?,?,?,?)")){
        
        
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);
         
           
            
            pst.setString(1, basic.getTitle());
            pst.setString(2, basic.getDesc());
            pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pst.setString(4, String.valueOf(basic.getPrice()));
            pst.setNull(5,java.sql.Types.BLOB);
        	pst.setNull(6, java.sql.Types.BLOB);
        	pst.setNull(7, java.sql.Types.BLOB);
            for(File f : pics)
            	{
            	FileInputStream image = new FileInputStream(f);
            	if(pics.indexOf(f) == 0)
            		pst.setBinaryStream(5, image,image.available());
            	
            	if(pics.indexOf(f) == 1)
            		pst.setBinaryStream(6, image,image.available());
        		
        		if(pics.indexOf(f) == 2)
        			pst.setBinaryStream(7, image,image.available());
        		if(pics.indexOf(f) > 2)
        			break;
    			
    		}
            pst.setInt(8, seller);
            pst.setString(9, filter.getUniversity());
            pst.setString(10, filter.getCity());
            pst.setString(11, filter.getSubject());
            pst.setBoolean(12, filter.getBook());
            pst.setBoolean(13, filter.getNotes());
            pst.executeUpdate();
            
            

        
    	
    	return true;
    	}
    }

	public static void ban(Integer id) throws ClassNotFoundException, SQLException {
		Connection conn = null;
        PreparedStatement pst = null;
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            pst = conn.prepareStatement("DELETE from insertions where id = ?");            
           
            pst.setInt(1, id);                       
            pst.executeUpdate();
                
            
        
	}
    
    
}
