package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import connectionManager.ConnectionManager;
import model.Product;
import model.Transaction;

public class ProductDAO {
	
	
	public void addProduct(product product) throws ClassNotFoundException, SQLException {
		int id=product.getPRODUCTID();
		String name=product.getPRODUCTNAME();
		int minsell=product.getMINSELL();
		int price=product.getPRICE();
		int quantity=product.getQUANTITY();
		
		connectionmanager conn = new connectionmanager();
		Connection con = conn.establishConnection();
		
		String query ="insert into PRODUCT(PRODUCTID,PRODUCTNAME,MINSELL,PRICE,QUANTITY)values(?,?,?,?,?)";
		
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, minsell);
		ps.setInt(4, price);
		ps.setInt(5, quantity);
		
		ps.executeUpdate();
		conn.closeConnection();
	}
	
	public void display() throws ClassNotFoundException, SQLException {
		connectionmanager conn=new connectionmanager();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT");
		
		while(rs.next()) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println(rs.getInt("PRODUCTID")+" | "+rs.getString("PRODUCTNAME")+" | "+rs.getInt("MINSELL")+" | "+rs.getInt("PRICE")+" | "+rs.getInt("QUANTITY"));
			System.out.println("-----------------------------------------------------------------");
		}
	}
	public TreeMap<String, Integer> productDetails(product product) throws ClassNotFoundException, SQLException {
		
		int pID=product.getPRODUCTID();
		TreeMap<String,Integer> map=new TreeMap<String,Integer>();
		
		connectionmanager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		
		String query="SELECT PRODUCTNAME,PRICE FROM PRODUCT WHERE PRODUCTID = '"+pID+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next()) {
			
			String name=rs.getString("PRODUCTNAME");
			int cost=rs.getInt("PRICE");
			map.put(name,cost);
			
			System.out.println("-----------------------------------------------------------------");
			System.out.println(" | "+rs.getString("PRODUCTNAME")+" | "+rs.getInt("PRICE")+" | ");
			System.out.println("-----------------------------------------------------------------");
			
		}
		return map;
	}
	public boolean validate(product product) throws ClassNotFoundException, SQLException {
		
		int quantity=product.getMINSELL();
		int pID=product.getPRODUCTID();
		
		connectionmanager conn=new connectionmanager();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT MINSELL FROM PRODUCT WHERE PRODUCTID = '"+pID+"'");
		
		while(rs.next()){
			if(quantity<=rs.getInt("MINSELL")) {
				
				conn.closeConnection();
				return true;
			}
		}
		conn.closeConnection();
		return false;
	}

	public int calculateTotalCost(product product) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int pID=product.getPRODUCTID();
		int quantity=product.getMINSELL();
		int total=0;
		
		ConnectionManager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT PRICE FROM PRODUCT WHERE PRODUCTID = '"+pID+"'");
		
		while(rs.next()) {
			total=rs.getInt("PRICE")*quantity;
		}
		
		
		return total;
	}

	public void updateTransaction(Transaction transaction) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int id=transaction.getPRODUCTID();
		String type=transaction.getTRANSACTIONTYPE();
		int quantity=transaction.getQUANTITY();
		int oldQuantity = 0, newQuantity=0;
		
		connectionmanager conn=new connectionmanager();
		Connection con=conn.establishConnection();
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT QUANTITY FROM PRODUCT WHERE PRODUCTID = '"+id+"'");
		while(rs.next()) {
			oldQuantity=rs.getInt("QUANTITY");
		}
		
		if(type.equals("Sell")) {
			newQuantity=oldQuantity-quantity;
		}
		if(type.equals("Buy")) {
			newQuantity=quantity+oldQuantity;
			
		}
		String query="UPDATE PRODUCT SET QUANTITY='"+newQuantity+"' WHERE PRODUCTID='"+id+"'";
		
		st.executeUpdate(query);
		conn.closeConnection();
		
		
	}
}
