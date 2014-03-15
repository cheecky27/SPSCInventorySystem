package app.dao.newentry.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;
import app.dao.newentry.UpdateNewItemDao;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class UpdateNewItemDaoImpl implements UpdateNewItemDao {

	@Override
	public void updateIntoDB(Item items) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("UPDATE `inventory system`.`inventory` SET `itemDescription`=?, `location`=?, `quantity`=? , `remarks`=? WHERE `eitemid`='"+items.getItemID()+"' ");
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			
		
				pr.setString(1, items.getItemDes());
				pr.setString(2, items.getLocation());
				pr.setInt(3, items.getQuantity());
				pr.setString(4,items.getRemarks());
				
				
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
