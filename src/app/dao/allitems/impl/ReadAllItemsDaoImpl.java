package app.dao.allitems.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.allitems.ReadAllItemsDao;
import app.dao.connector.DatabaseManager;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class ReadAllItemsDaoImpl implements ReadAllItemsDao{

	@Override
	public ArrayList<Item> showEverything() {
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		ArrayList<Item> itemList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT  *FROM inventory");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			
			while(rs.next()){
				Item myItem = new Item();
				myItem.setItemID(rs.getString("eitemid"));
				myItem.setItemDes(rs.getString("itemDescription"));
				myItem.setLocation(rs.getString("location"));
				myItem.setQuantity(rs.getInt("quantity"));
				myItem.setUnitOfMeasurement(rs.getString("unit"));
				myItem.setDateDelivered(rs.getString("datecreated"));
				myItem.setRemarks(rs.getString("remarks"));
				myItem.setInstock(rs.getInt("instock"));
				myItem.setOutstock(rs.getInt("outstock"));
				myItem.setBorrower(rs.getString("borrower"));
				myItem.setDateReturn(rs.getString("lastBorrowed"));
				myItem.setGrpId(rs.getInt("idgroup"));


				itemList.add(myItem);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pr!=null){
				try {
					pr.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return itemList;
	}

	@Override
	public int countRecords() {
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		int countRecords =0;
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT  *FROM inventory");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			
			while(rs.next()){
				countRecords++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pr!=null){
				try {
					pr.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return countRecords;
	}
}