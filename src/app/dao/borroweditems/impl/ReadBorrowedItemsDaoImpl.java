package app.dao.borroweditems.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.borroweditems.ReadBorrowedItemDao;
import app.dao.connector.DatabaseManager;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class ReadBorrowedItemsDaoImpl implements ReadBorrowedItemDao {

	public List<Item> showItemsToBeReturned(int itemid) {
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		List<Item> borrowList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT  itemDescription FROM borrow WHERE eitemid = "+itemid+" ");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			
			while(rs.next()){
				Item myItem = new Item();
				myItem.setItemID(rs.getString("itemno"));
				myItem.setItemDes(rs.getString("itemdes"));
				myItem.setQuantity(rs.getInt("quantity"));



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


		return borrowList;
	}

	public List<Item> showQuantityToBeReturned(){
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		List<Item> borrowList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT  * FROM borrow");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			
			while(rs.next()){

				Item myItem = new Item();
				myItem.setBorrower(rs.getString("groupid"));
				myItem.setItemID(rs.getString("itemno"));
				myItem.setQuantity(rs.getInt("borrowQuantity"));


				borrowList.add(myItem);
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


		return borrowList;
	}


}