package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionmanager.ConnectionManager;
import model.Transaction;

public class TransactionDAO {

	public void updateTransaction(Transaction transaction) throws ClassNotFoundException, SQLException {
		int id=transaction.getPRODUCTID();
		String name=transaction.getPRODUCTNAME();
		String type=transaction.getTRANSACTIONTYPE();
		int quantity=transaction.getQUANTITY();
		int cost=transaction.getCOST();
		int totalcost=transaction.getTOTALCOST();
		
		ConnectionManager conn = new ConnectionManager();
		Connection con = conn.establishConnection();
		
		String query ="insert into TRANSACTION(PRODUCTID,PRODUCTNAME,TRANSACTIONTYPE,QUANTITY,COST,TOTALCOST)values(?,?,?,?,?,?)";
		
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, type);
		ps.setInt(4, quantity);
		ps.setInt(5, cost);
		ps.setInt(6, totalcost);
		
		ps.executeUpdate();
		conn.closeConnection();
	}

	public void showHistory() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionManager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM TRANSACTION");
		
		while(rs.next()) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println(rs.getInt("PRODUCTID")+" | "+rs.getString("PRODUCTNAME")+" | "+rs.getString("TRANSACTIONTYPE")+" | "+rs.getInt("QUANTITY")+" | "+rs.getInt("COST")+"|"+rs.getInt("TOTALCOST")+" | ");
			System.out.println("-----------------------------------------------------------------");
		}
		
	}
}