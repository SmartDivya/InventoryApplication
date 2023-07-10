package connectionmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionManager {
	
Connection con=null;

public Connection establishConnection() throws ClassNotFoundException, SQLException
{
   Class.forName("com.mysql.cj.jdbc.Driver");
   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/INVENTORY","root","priyadivya2126");
   return con;
}
public void closeConnection() throws SQLException 
{
	con.close();
}
}
