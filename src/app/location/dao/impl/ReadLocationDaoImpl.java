package app.location.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.connector.DatabaseManager;
import app.dao.location.ReadLocationDao;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class ReadLocationDaoImpl implements ReadLocationDao {

	@Override
	public List<Item> getFromDbLocation() {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Item> locationList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);

		sql.append("SELECT *FROM `location` ");



		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			

			while(rs.next()){
				Item myItem = new Item();
				myItem.setLocation(rs.getString("location"));
				locationList.add(myItem);
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


		return locationList;
	}
}

	