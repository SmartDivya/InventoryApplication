package model;

public class Transaction {

	private int PRODUCTID;
	private String PRODUCTNAME;
	private String TRANSACTIONTYPE;
	private int QUANTITY;
	private int COST;
	private int TOTALCOST;
	public int getPRODUCTID() {
		return PRODUCTID;
	}
	public void setPRODUCTID(int pRODUCTID) {
		PRODUCTID = pRODUCTID;
	}
	public String getPRODUCTNAME() {
		return PRODUCTNAME;
	}
	public void setPRODUCTNAME(String pRODUCTNAME) {
		PRODUCTNAME = pRODUCTNAME;
	}
	public String getTRANSACTIONTYPE() {
		return TRANSACTIONTYPE;
	}
	public void setTRANSACTIONTYPE(String tRANSACTIONTYPE) {
		TRANSACTIONTYPE = tRANSACTIONTYPE;
	}
	public int getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(int qUANTITY) {
		QUANTITY = qUANTITY;
	}
	public int getCOST() {
		return COST;
	}
	public void setCOST(int cOST) {
		COST = cOST;
	}
	public int getTOTALCOST() {
		return TOTALCOST;
	}
	public void setTOTALCOST(int tOTALCOST) {
		TOTALCOST = tOTALCOST;
	}
}