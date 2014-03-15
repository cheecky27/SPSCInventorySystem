package app.dao.allitems.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.allitems.UpdateInventoryItemsDao;
import app.dao.connector.DatabaseManager;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class UpdateInventoryItemsDaoImpl implements UpdateInventoryItemsDao {


	
	public void updateInventory(Item items) {
		
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("UPDATE `inventory system`.`inventory` SET `instock`=?, `outstock`=?, `borrower` =? WHERE `eitemid`='"+items.getItemID()+"' ");
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			
		
				pr.setInt(1, items.getInstock());
				pr.setInt(2, items.getOutstock());
				pr.setString(3, items.getBorrower());
				
				
			pr.executeUpdate();					
		
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
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