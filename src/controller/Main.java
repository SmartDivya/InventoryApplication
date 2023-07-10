package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.TreeMap;

import dao.LoginDAO;
import dao.ProductDAO;
import dao.TransactionDAO;
import model.Login;
import model.Transaction;
import model.product;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		Login login=new Login();
		product product=new product();
		Transaction transaction=new Transaction();
		LoginDAO logindao=new LoginDAO();
		ProductDAO productdao=new ProductDAO();
		TransactionDAO transactiondao=new TransactionDAO();
		
		int option,option1,option2;
		do {
			System.out.println("1.Admin");
			System.out.println("2.Agent");
			System.out.println("3.Exit");
			System.out.println("-----------------------------------------------------------------");
			option = Integer.parseInt(br.readLine());
			
			switch(option) {
			case 1:
			
				System.out.println("-----------------------------------------------------------------");
				System.out.println("Enter username");
				String username=br.readLine();
				System.out.println("Enter password");
				String password=br.readLine();
				login.setUSERNAME(username);
				login.setPASSWORD(password);
				boolean result = logindao.validate(login);
				if(result==true) {
					System.out.println("Login Successful!!!");
					System.out.println("-----------------------------------------------------------------");
					do {
					System.out.println("1.Add Product");
					System.out.println("2.Display Inventory Details");
					System.out.println("3.Logout");
					System.out.println("-----------------------------------------------------------------");
					option1 = Integer.parseInt(br.readLine());
					switch(option1) {
					case 1:
						System.out.println("Enter product name");
						String productName=br.readLine();
						System.out.println("Enter product id");
						int productId=Integer.parseInt(br.readLine());
						System.out.println("Enter minimum selling quantity");
						int minsell=Integer.parseInt(br.readLine());
						System.out.println("Enter price of the product");
						int price=Integer.parseInt(br.readLine());
						System.out.println("Enter the quantity");
						int quantity=Integer.parseInt(br.readLine());
					
						product.setPRODUCTNAME(productName);
						product.setPRODUCTID(productId);
						product.setMINSELL(minsell);
						product.setPRICE(price);
						product.setQUANTITY(quantity);
					
						productdao.addProduct(product);
						break;
					case 2:
						productdao.display();
						break;
					case 3:
						break;
				}
				}
				while(option1!=3);
					
				}
				else {
					System.out.println("username & password is not correct");
				}
				break;
			
			case 2:
			
				System.out.println("-----------------------------------------------------------------");
				System.out.println("Enter username");
				String agentusername=br.readLine();
				System.out.println("Enter password");
				String agentpassword=br.readLine();
				login.setUSERNAME(agentusername);
				login.setPASSWORD(agentpassword);
				result = logindao.validate(login);
				
				if(result==true) {
					System.out.println("Login Successful!!!");
					System.out.println("-----------------------------------------------------------------");
					do {
					System.out.println("1.Buy/Sell");
					System.out.println("2.Show History");
					System.out.println("3.Logout");
					System.out.println("-----------------------------------------------------------------");
					option1 = Integer.parseInt(br.readLine());
					
					switch(option1) {
					case 1:
					
						System.out.println("Enter product id");
						int productId=Integer.parseInt(br.readLine());
						
						product.setPRODUCTID(productId);
						transaction.setPRODUCTID(productId);
						
						TreeMap<String,Integer> map=productdao.productDetails(product);
						transaction.setPRODUCTNAME(map.firstKey());
						transaction.setCOST(map.get(map.firstKey()));
						
						System.out.println("-----------------------------------------------------------------");
						System.out.println("1.Buy");
						System.out.println("2.Sell");
						System.out.println("-----------------------------------------------------------------");
						option2 = Integer.parseInt(br.readLine());
						
						System.out.println("Enter the quantity");
						int quantity=Integer.parseInt(br.readLine());
						product.setMINSELL(quantity);
						transaction.setQUANTITY(quantity);
						
						if(option2==2) {
							transaction.setTRANSACTIONTYPE("Sell");
							boolean res=productdao.validate(product);
							
							if(res==true) {
								System.out.println("-----------------------------------------------------------------");
								System.out.println("Product available");
								int total= productdao.calculateTotalCost(product);
								transaction.setTOTALCOST(total);
								System.out.println("Total cost : "+total);
								System.out.println("-----------------------------------------------------------------");
								
								System.out.println("Confirm Booking:\n1.confirm\n2.cancel");
								System.out.println("-----------------------------------------------------------------");
								int op=Integer.parseInt(br.readLine());
								
								if(op==1) {
									System.out.println("-----------------------------------------------------------------");
									System.out.println("Transaction successfull!!!");
									System.out.println("-----------------------------------------------------------------");
									transactiondao.updateTransaction(transaction);
									productdao.updateTransaction(transaction);
									break;
								}
								else {
									System.out.println("Transaction cancelled...");
									System.out.println("-----------------------------------------------------------------");
									break;
								}
							}
							else {
								System.out.println("-----------------------------------------------------------------");
								System.out.println("Product NOT available!!");
								System.out.println("-----------------------------------------------------------------");
								break;
							}
						}
						else if(option2==1) {
							transaction.setTRANSACTIONTYPE("Buy");
							
							System.out.println("-----------------------------------------------------------------");
							
							int total= productdao.calculateTotalCost(product);
							transaction.setTOTALCOST(total);
							
							System.out.println("Total cost : "+total);
							
							System.out.println("-----------------------------------------------------------------");
							
							System.out.println("Confirm Booking:\n1.confirm\n2.cancel");
							System.out.println("-----------------------------------------------------------------");
							int op=Integer.parseInt(br.readLine());
							
							if(op==1) {
								System.out.println("-----------------------------------------------------------------");
								System.out.println("Transaction successfull!!!");
								System.out.println("-----------------------------------------------------------------");
								transactiondao.updateTransaction(transaction);
								productdao.updateTransaction(transaction);
								break;
							}
							else {
								System.out.println("Transaction cancelled...");
								System.out.println("-----------------------------------------------------------------");
								break;
							}
							
						}
					
					case 2:
						transactiondao.showHistory();
						break;
					case 3:
						break;
						
					}
					}while(option1!=3);
				}
				else {
					System.out.println("Invalid login!!!");
				}
				break;
			
			case 3:
				break;
			}
			
		}while(option!=3);
	}

}
