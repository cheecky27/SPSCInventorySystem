package app.dao.archive.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.archive.ReadIntoArchiveDao;
import app.dao.connector.DatabaseManager;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class ReadIntoArchiveDaoImpl implements ReadIntoArchiveDao{
	public List<Item> showArchive() {
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		List<Item> archiveList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);
		sql.append("SELECT  *FROM archive");

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
				myItem.setUnitOfMeasurement(rs.getString("unit"));
				myItem.setLocation(rs.getString("location"));
				myItem.setDateDeleted(rs.getString("datedeleted"));

				archiveList.add(myItem);
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


		return archiveList;
	}
	public void deleteRecordInArchive(Item selectedNewItem) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Item> itemList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);


		sql.append("DELETE FROM archive WHERE itemno LIKE ? ");

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


		
	}
}
