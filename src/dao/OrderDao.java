package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.Item;
import logic.Order;

public class OrderDao {
	
	private static final String ERROR_CLASS = "UtenteDao";
	private static final String COLUMN_PREZZO = "prezzo";
	private static final String COLUMN_OGGETTO = "oggetto";
	private static final String COLUMN_IDORDER = "idOrder";
	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";

	private OrderDao() {
        throw new IllegalStateException("Utility class");
      }
	
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";	
	
	public static List<Order> orderListFromDB(String user) throws SQLException, ClassNotFoundException {
		//method to get a list of orders using a username
		 List<Order> order = new LinkedList<>();
		
	   
	    ResultSet rs = null;
	    
	    Class.forName(CONNECTOR);
	    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    Statement stmt = conn.createStatement();
        
	   //Need to get the id of the user first
		String sql = "SELECT id FROM utenti where username = '"
	            + user + "';";
	    rs = stmt.executeQuery(sql);
	    
	    if(!rs.first()) {
	    	return new LinkedList<>();
	    }
	    
	    
	    int id = rs.getInt("id");
	   
	    //using the id we get the orders
	    sql = "SELECT idOrder, oggetto, prezzo FROM orders where buyer = '"
	                + id + "' OR SELLER = '"+ id +"';";
	    rs = stmt.executeQuery(sql);
	       

	    if (!rs.first()) // rs not empty
	        return new LinkedList<>();
	     
	     rs.first();
	     
	    
	     id = rs.getInt(COLUMN_IDORDER);
	     String nome = rs.getString(COLUMN_OGGETTO);
	     int prezzo = rs.getInt(COLUMN_PREZZO);
	        
	     Item item = new Item(nome,prezzo);
	     Order or = new Order(item);
	     or.setId(id);
	     order.add(or);
	        
	     while (rs.next())
	      {       	 
	        	
	            id = rs.getInt(COLUMN_IDORDER);
	            nome = rs.getString(COLUMN_OGGETTO);
	            prezzo = rs.getInt(COLUMN_PREZZO);	            
	            item = new Item(nome,prezzo);
	            or = new Order(item);
	            or.setId(id);      
	            order.add(or);
	        }
	              
	     rs.close();
	     stmt.close();
	     conn.close();
		
		 return order;
        
	}


	public static Order getOrderInfo(Integer id) throws SQLException, ClassNotFoundException {
		//method to get the info of a specific order
		Order order = null;			
	   
	    ResultSet rs = null;	   
	    
	    Class.forName(CONNECTOR);
	    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    Statement stmt = conn.createStatement();
	    
        String sql = "SELECT idOrder, oggetto, prezzo, data, seller, buyer, pagato FROM orders where idOrder = '"
                + id + "';";
        rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return order;

        rs.first();

        order = new Order();
        
        String nome = rs.getString(COLUMN_OGGETTO);
        int prezzo = rs.getInt(COLUMN_PREZZO);
        int seller = rs.getInt("seller");
        int buyer = rs.getInt("buyer");
        Date data = rs.getDate("data");
        Boolean pagato = rs.getBoolean("pagato");
        Item item = new Item(nome,prezzo);
        order.setId(id);
        order.setItem(item);
        order.setData(data);
        order.setPaid(pagato);
        rs.close();
        sql = "SELECT nome FROM utenti where id = '"
                + buyer + "';";
        rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	return null;
        }
        
        order.setBuyer(rs.getString("nome"));
        rs.close();
        sql = "SELECT nome FROM utenti where id = '"
                + seller + "';";
        rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	return null;
        }
        
        order.setSeller(rs.getString("nome"));


        rs.close();
        stmt.close();
        conn.close();
		
		return order;
		
	}
	
	public static boolean newOrder(int buyer,int seller, String oggetto, int prezzo) throws ClassNotFoundException, SQLException {
			//method to create a new order       
        
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst = conn.prepareStatement("INSERT into orders(buyer,seller,data,oggetto,prezzo) values(?,?,?,?,?)");
          
            pst.setInt(1, buyer);
            pst.setInt(2, seller);
            pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pst.setString(4, oggetto);
            pst.setInt(5, prezzo);
            
            pst.executeUpdate();
            
            
        pst.close();
        conn.close();
    	
    	return true;
		
	}
	
	
	public static boolean buyBook(int buyer,int seller, int inserzione,String oggetto, Integer prezzo) {
		//method used to buy a book(create a order) using the stored procedure compra
		//The procedure is a transaction that throws an exception if the object has already been bought
		//The transaction also helps with concurrency since if two users try to buy the same item
		//One will find that the item has been bought before creating the order and a SQLexception will be thrown
		Connection conn = null;
        CallableStatement pst = null;
        try {

            Class.forName(CONNECTOR);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            pst = conn.prepareCall("call compra(?,?,?,?,?)");
            
            Integer res = 0;
            
            pst.setInt(1, buyer);
            pst.setInt(2, seller);
            pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pst.setInt(4, inserzione);
            pst.setInt(5, res);
            pst.registerOutParameter(5, java.sql.Types.INTEGER);
            
            pst.executeUpdate();
            pst.close();
            if(res == 0) {
            newOrder(buyer,seller,oggetto,prezzo); //Method to create a order in the DB
            return true;}
            else
             return false;
            
            
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
            	//Item has already been bought, will return false
            	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,se);
            	return false;
            }
        }
    	
	}

	public static void payOrder(Integer id) throws SQLException, ClassNotFoundException {
			//Method used to pay the order after using a payment system
			//The payment system, being a black box, will just be a mockup
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       
            PreparedStatement pst = conn.prepareStatement("UPDATE orders SET pagato = 1 WHERE idOrder = ?");
            pst.setInt(1, id);  
            pst.executeUpdate();
            
            pst.close();
            conn.close();
        
		
		
	}
	
}
