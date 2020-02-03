package dao;

import java.sql.*;
import java.time.LocalDate;
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
	
	public static Order[] orderListFromDB(String user) throws SQLException, ClassNotFoundException {
		
		Order[] order= new Order[100];
	   
	    ResultSet rs = null;
	    
	    Class.forName(CONNECTOR);
	    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    Statement stmt = conn.createStatement();
        
        String sql = "SELECT id FROM utenti where username = '"
                + user + "';";
        rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	return new Order[0];
        }
        int id = rs.getInt("id");
        rs.close();
        
        sql = "SELECT idOrder, oggetto, prezzo FROM orders where buyer = '"
                + id + "' OR SELLER = '"+ id +"';";
        rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return order;

        // riposizionamento del cursore
        rs.first();

        // lettura delle colonne "by name"
        id = rs.getInt(COLUMN_IDORDER);
        String nome = rs.getString(COLUMN_OGGETTO);
        int prezzo = rs.getInt(COLUMN_PREZZO);
        int i=0;
        
        
        Item item = new Item(nome,prezzo);
        order[0]= new Order(item);
        order[0].setId(id);
        
        i++;
        while (rs.next())
        {
        	 
             nome = rs.getString(COLUMN_OGGETTO);
             prezzo = rs.getInt(COLUMN_PREZZO);
             item = new Item(nome,prezzo);
             
             id = rs.getInt(COLUMN_IDORDER);
             order[i]=new Order(item);
             order[i].setId(id);
             i++;
             
             
        }
        

        // STEP 6: Clean-up dell'ambiente
        rs.close();
        stmt.close();
        conn.close();
		
		
		
		return order;
		
	}
	
	public static Order getOrderInfo(Integer id) throws SQLException, ClassNotFoundException {
		
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

        // riposizionamento del cursore
        rs.first();

        // lettura delle colonne "by name"
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

        // STEP 6: Clean-up dell'ambiente
        rs.close();
        stmt.close();
        conn.close();
		
		return order;
		
	}
	
	public static boolean newOrder(int buyer,int seller, String oggetto, int prezzo) throws ClassNotFoundException, SQLException {
		
        
        
            // STEP 2: loading dinamico del driver mysql
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
		Connection conn = null;
        CallableStatement pst = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
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
            if(!newOrder(buyer,seller,oggetto,prezzo))
            	return false;}
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
            	Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,se);
            	return false;
            }
        }
    	
    	return true;
	}

	public static void payOrder(Integer id) throws SQLException, ClassNotFoundException {
		
		
       
            // STEP 2: loading dinamico del driver mysql
            Class.forName(CONNECTOR);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
       
            // STEP 4: creazione ed esecuzione della query
            PreparedStatement pst = conn.prepareStatement("UPDATE orders SET pagato = 1 WHERE idOrder = ?");
            pst.setInt(1, id);  
            pst.executeUpdate();
            
            pst.close();
            conn.close();
        
		
		
	}
	
}
