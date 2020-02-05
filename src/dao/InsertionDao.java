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

import java.sql.Date;

public class InsertionDao {

	
	private static final String NOTES = "notes";
	private static final String BOOK = "book";
	private static final String SUBJECT = "subject";
	private static final String CITY = "city";
	private static final String UNIVERSITY = "university";
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
    
    public static List<InsertionBean> getReserach(String research, logic.Filters.Date order) throws SQLException, ClassNotFoundException {
    		//method to get a list of isnertions ordered by Date ASC or DESC 
    		LinkedList<InsertionBean> ins = new LinkedList<>();
  
    		ResultSet rs = null;
       
           
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
          
            Statement stmt = conn.createStatement();

           
          
            
            String sql = "SELECT title, descr, price, data, id, image1, image2, image3, seller, sold, university, city, subject, book, notes FROM insertions where title LIKE '%"+ research + "%' ";
            
            if (order.equals(logic.Filters.Date.NEW))// ordered by date depending on order kind
            	sql = sql + "ORDER BY data desc";
            else
            	sql = sql + "ORDER BY data asc";
            
            
            rs = stmt.executeQuery(sql); //execute
           
     
            if (!rs.first()) // rs not empty
                return ins;

   
            //cursor on the first result
            rs.first();

            ins.add(getInfo(rs)); //insert a new insertionBean in the list
            
            while(rs.next()) {//Continue looking for results of the select until null 
            	
            ins.add(getInfo(rs));
            }

            
            rs.close();
            stmt.close();
            conn.close();
        
            return ins;
    	}
    
    
    private static InsertionBean getInfo(ResultSet rs) throws SQLException, ClassNotFoundException {
    	//Method to retrieve info about an insertion from a result set
    	
    	// read columns "by name"
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
       //Convert blob into images
        for(Blob img : images) {
        		
        		InputStream imgIn = img.getBinaryStream();
        		Image listItem = new Image(imgIn);
        		imagesList.add(listItem);
        		
        	
        }
        //Set the filters of the insertion
       Filters filter = new Filters();
       filter.setUniversity(rs.getString(UNIVERSITY));
       filter.setCity(rs.getString(CITY));
       filter.setSubject(rs.getString(SUBJECT));
       filter.setBook(rs.getBoolean(BOOK));
       filter.setNotes(rs.getBoolean(NOTES));
       //Set the basic info of the isnertion
       BasicInformations basic = new BasicInformations(title,desc,date,Integer.parseInt(price));
       return new InsertionBean(basic,id,imagesList,UtenteDao.getUsernameById(seller),seller,sold,filter);
        
		
	}

	public static InsertionBean getDetail(Integer id) throws SQLException, ClassNotFoundException {
			//method to get a specific insertion using the id
    		InsertionBean ins;
   
    		ResultSet rs = null;
                 
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT title, descr, price, data, id, image1, image2, image3, seller, sold, university, city, subject, book, notes FROM insertions where id = '"+ id +"';";
            rs = stmt.executeQuery(sql); //Execute

            if (!rs.first()) // rs not empty
                return null;  

            rs.first();

            // read columns "by name"
            String title = rs.getString(COLUMN_TITLE);
            String desc = rs.getString(COLUMN_DESCR);
            String price = rs.getString(COLUMN_PRICE);
            Date date = rs.getDate(COLUMN_DATA);
            List<Image> images = new LinkedList<>();
            if(rs.getBlob(COLUMN_IMAGE1)!=null) {
            	Image img = new Image(rs.getBlob(COLUMN_IMAGE1).getBinaryStream());
            	images.add(img);
            }
            if(rs.getBlob(COLUMN_IMAGE1)!=null) {
            	Image img = new Image(rs.getBlob(COLUMN_IMAGE2).getBinaryStream());
            	images.add(img);
            }
            if(rs.getBlob(COLUMN_IMAGE1)!=null) {
            	Image img = new Image(rs.getBlob(COLUMN_IMAGE3).getBinaryStream());
            	images.add(img);
            }
            Integer seller = rs.getInt(COLUMN_SELLER);
            Boolean sold = rs.getBoolean(COLUMN_SOLD);
           
            BasicInformations basic = new BasicInformations(title,desc,date,Integer.parseInt(price));
            Filters filter = new Filters();
            filter.setUniversity(rs.getString(UNIVERSITY));
            filter.setCity(rs.getString(CITY));
            filter.setSubject(rs.getString(SUBJECT));
            filter.setBook(rs.getBoolean(BOOK));
            filter.setNotes(rs.getBoolean(NOTES));
            ins = new InsertionBean(basic,id,images,UtenteDao.getUsernameById(id),seller,sold,filter);
            
            rs.close();
            stmt.close();
            conn.close();
    	
        return ins;
		}
    
    
    public static Boolean newInsertion(BasicInformations basic, List<File> pics,Integer seller,Filters filter) throws SQLException, ClassNotFoundException, IOException {
    		//Method to create a new insertion
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst = conn.prepareStatement("INSERT into insertions(title,descr,data,price,image1,image2,image3,seller,university,city,subject,book,notes) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            //Set-up the statement parameters
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
            //execute
            pst.executeUpdate();
            
            pst.close();
            conn.close();

        
    	
    	return true;
    	}
    

	public static void ban(Integer id) throws ClassNotFoundException, SQLException {
			//method to delete an insertion using the id
			Connection conn = null;
			PreparedStatement pst = null;
       
            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            pst = conn.prepareStatement("DELETE from insertions where id = ?");            
           
            pst.setInt(1, id);                       
            pst.executeUpdate();
                
            pst.close();
            conn.close();
        
	}

	public static List<InsertionBean> getMyInsertions(Integer user) throws SQLException, ClassNotFoundException {
		LinkedList<InsertionBean> ins = new LinkedList<>();
    		//method that returns the insertions in the DB of a single user       
        
        	ResultSet rs = null;
       
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);          
            
            String sql = "SELECT title, descr, price, data, id, image1, image2, image3, sold, university, city, subject,seller, book, notes FROM insertions where seller =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
           	pst.setInt(1, user);
           	rs = pst.executeQuery();
     
            if (!rs.first()) // rs not empty
                return ins;

            rs.first();

            ins.add(getInfo(rs));
            
            while(rs.next()) {
            	
            ins.add(getInfo(rs));
            }

            rs.close();
            pst.close();
            conn.close();
        
            return ins;
	}

	public static void update(InsertionBean ib) throws SQLException, ClassNotFoundException {
		//method to modify an insertion in the DB
		ResultSet rs = null;
	       
        Class.forName(CONNECTOR);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);     
        
        String sql = "UPDATE insertions SET title =?, descr =?, price =?, data =?, university=?, city =?, subject = ?, book =?, notes =? where id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        
       	pst.setString(1,ib.getBasic().getTitle());
       	pst.setString(2, ib.getBasic().getDesc());
       	pst.setInt(3, ib.getBasic().getPrice());
       	pst.setDate(4,java.sql.Date.valueOf(LocalDate.now()) );
       	pst.setString(5, ib.getFilter().getUniversity());
       	pst.setString(6, ib.getFilter().getCity());
       	pst.setString(7, ib.getFilter().getSubject());
       	pst.setBoolean(8, ib.getFilter().getBook());
       	pst.setBoolean(9, ib.getFilter().getNotes());
       	pst.setInt(10, ib.getId());
       	
       	rs = pst.executeQuery();

        rs.close();
        pst.close();
        conn.close();
		
	}
    
    
}
