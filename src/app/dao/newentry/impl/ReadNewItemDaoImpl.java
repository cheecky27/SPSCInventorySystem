package app.dao.newentry.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.connector.DatabaseManager;
import app.dao.group.impl.WriteGroupDaoImpl;
import app.dao.newentry.ReadNewItemDao;
import app.db.MySQLDatabaseManager;
import app.model.Item;



public class ReadNewItemDaoImpl implements ReadNewItemDao{

	@Override
	public List<Item> showItems() {
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		List<Item> itemList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT *FROM inventory");

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



	

	public List<Item> deleteStudent(Item selectedNewItem) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Item> itemList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);


		sql.append("DELETE FROM additem WHERE itemID LIKE ? ");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			pr.setString(1, selectedNewItem.getItemID());
			pr.execute();

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


	public int getForeignKeyOfInventory() {

		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Item> itemList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		int foreignId = 0;

		sql.append("SELECT eitemid FROM inventory ORDER BY eitemid DESC LIMIT 1;");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();
			while(rs.next()){

				foreignId= rs.getInt("eitemid");

			}
			//	WriteGroupDaoImpl myAddGroupDaoImpl = new WriteGroupDaoImpl();
			//	myAddGroupDaoImpl.writeIntoGroup(foreignId);
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


		return foreignId;
	}
	public List<Item> showSearchedItems(String search) {
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		List<Item> itemList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT *FROM inventory WHERE itemDescription LIKE CONCAT('%','"+search+"','%')  ");

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
}





