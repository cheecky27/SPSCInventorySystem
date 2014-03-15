package app.dao.archive.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.dao.archive.WriteIntoArchiveDao;
import app.dao.connector.DatabaseManager;
import app.dao.newentry.impl.ReadNewItemDaoImpl;
import app.db.MySQLDatabaseManager;
import app.model.Group;
import app.model.Item;

public class WriteIntoArchiveDaoImpl implements WriteIntoArchiveDao{
	Item items;
	boolean wasWritten= false;

	@Override
	public boolean doWriteInArchive(Item items) {
		this.items = items;
		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO archive( `itemno`, `itemdes`, `quantity`, `unit`, `location`, datedeleted) VALUES (?,?,?,?,?,?)  ;");

		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{

			pr = conn.prepareStatement(sqlUpdate);
			pr.setString(1, items.getItemID());
			pr.setString(2, items.getItemDes());
			pr.setInt(3, items.getQuantity());
			pr.setString(4, items.getUnitOfMeasurement());
			pr.setString(5, items.getLocation());
			pr.setString(6,items.getDateDeleted());

			int isWritten = pr.executeUpdate();
			if(isWritten == 1){
				deleteItemInInventory();
				wasWritten = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
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
		return wasWritten;


	}


	public void deleteItemInInventory(){
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();

		StringBuilder sql = new StringBuilder(300);

		System.out.println("was here");
		sql.append("DELETE FROM inventory WHERE eitemid LIKE ? ");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			pr.setString(1, items.getItemID());
			pr.execute();

		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Hindi na deleete!");
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


