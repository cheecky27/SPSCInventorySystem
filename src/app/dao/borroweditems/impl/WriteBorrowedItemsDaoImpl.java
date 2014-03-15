package app.dao.borroweditems.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import app.dao.borroweditems.WriteBorrowedItemDao;
import app.dao.connector.DatabaseManager;
import app.db.MySQLDatabaseManager;
import app.model.Group;
import app.model.Item;

public class WriteBorrowedItemsDaoImpl implements WriteBorrowedItemDao {

	public void doWriteBorrowedItems(Group groupWhoBorrowed, Item borrowedItems ){
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO `inventory system`.`borrow`(`groupid`,`eitemid`,`borrowQuantity`) VALUES(?,?,?);");

		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{

			pr = conn.prepareStatement(sqlUpdate);
			pr.setInt(1, groupWhoBorrowed.getGroupID());
			pr.setString(2, borrowedItems.getItemID());
			pr.setInt(3, borrowedItems.getItemsToBeBorrowed());

			pr.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			if(pr!=null){
				try{
					pr.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			} try{
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
