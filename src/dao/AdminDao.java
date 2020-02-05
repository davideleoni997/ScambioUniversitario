package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class AdminDao {

	private static final String CONNECTOR = "org.mariadb.jdbc.Driver";
	private static final String PASS = "root";
    private static final String USER = "root";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/scambio";

	private AdminDao() {
		//Not necessary, static methods
	}
	
	public static boolean adminLogin(String user, String pass) throws ClassNotFoundException, SQLException {
		//Method to verify login of a user of the kind admin
        ResultSet rs = null;
       
       
            //Dynamic loading mysql driver
            Class.forName(CONNECTOR);

            // Open connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Prepare a query
            PreparedStatement pst =conn.prepareStatement("SELECT user,pass from admins where user = ? and pass = ?");
            
            //Set query parameters
            pst.setString(1, user);
            pst.setString(2, pass);
            //Execute
            rs = pst.executeQuery();

            if (!rs.first()) // rs not empty
                return false;

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; //Just to verify the consistency of the user table, no two same users
            
            // Close connection
            rs.close();
            pst.close();
            conn.close();

            return true;
		}
	
	
}
