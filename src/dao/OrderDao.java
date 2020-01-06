package dao;

import java.sql.*;
import java.time.LocalDate;

import logic.Item;
import logic.Order;

public class OrderDao {
	
	private static String PASS = "root";
    private static String USER = "root";
    private static String DB_URL = "jdbc:mariadb://localhost:3306/scambio";

	public static Order[] MockUpOrderList(String user) {
		
		Item item = new Item("asa",12);
		Item item2 = new Item("esa",11);
		
		Order order[]= new Order[] {new Order(item),new Order(item2)};
		order[0].setId(0);
		order[1].setId(1);
		
		return order;
	}
	
	
	
	public static Order[] OrderListFromDB(String user) {
		
		Order order[]= new Order[100];
	    Statement stmt = null;
	    Connection conn = null;
	    
	    try {
	    
	    Class.forName("org.mariadb.jdbc.Driver");
		
	    // STEP 3: apertura connessione
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 4: creazione ed esecuzione della query
        stmt = conn.createStatement();
        
        String sql = "SELECT id FROM utenti where username = '"
                + user + "';";
        ResultSet rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	return null;
        }
        int id = rs.getInt("id");
        
        
        sql = "SELECT idOrder, oggetto, prezzo FROM orders where buyer = '"
                + id + "' OR SELLER = '"+ id +"';";
        rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return order;

        // riposizionamento del cursore
        rs.first();

        // lettura delle colonne "by name"
        id = rs.getInt("idOrder");
        String nome = rs.getString("oggetto");
        int prezzo = rs.getInt("prezzo");
        int i=0;
        
        
        Item item = new Item(nome,prezzo);
        order[0]= new Order(item);
        order[0].setId(id);
        
        i++;
        while (rs.next())
        {
        	 
             nome = rs.getString("nome");
             prezzo = rs.getInt("prezzo");
             item = new Item(nome,prezzo);
             
             id = rs.getInt("idOrder");
             order[i]=new Order(item);
             order[i].setId(id);
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
		
		
		
		return order;
	}
	
	public static Order getOrderInfo(Integer id) {
		Order order = null;
			
	    Statement stmt = null;
	    Connection conn = null;
	    
	    try {
	    
	    Class.forName("org.mariadb.jdbc.Driver");
		
	    // STEP 3: apertura connessione
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // STEP 4: creazione ed esecuzione della query
        stmt = conn.createStatement();
        String sql = "SELECT idOrder, oggetto, prezzo, data, seller, buyer FROM orders where idOrder = '"
                + id + "';";
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.first()) // rs not empty
            return order;

        // riposizionamento del cursore
        rs.first();

        // lettura delle colonne "by name"
        order = new Order();
        
        String nome = rs.getString("oggetto");
        int prezzo = rs.getInt("prezzo");
        int seller = rs.getInt("seller");
        int buyer = rs.getInt("buyer");
        Date data = rs.getDate("data");
        Item item = new Item(nome,prezzo);
        order.setId(id);
        order.setItem(item);
        order.setData(data);
        sql = "SELECT nome FROM utenti where id = '"
                + buyer + "';";
        rs = stmt.executeQuery(sql);
        
        if(!rs.first()) {
        	return null;
        }
        
        order.setBuyer(rs.getString("nome"));
        
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
		
		
		
		return order;
	}
	
	public static boolean newOrder(int buyer,int seller, String oggetto, int prezzo) {
		Statement stmt = null;
        Connection conn = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.mariadb.jdbc.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            //!!!RICORDA ID AUTOINCREMENT!!!
            PreparedStatement pst = conn.prepareStatement("INSERT into orders(buyer,seller,data,oggetto,prezzo) values(?,?,?,?,?)");
            
         
            
            pst.setInt(1, buyer);
            pst.setInt(2, seller);
            pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            pst.setString(4, oggetto);
            pst.setInt(5, prezzo);
            
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
