package app.dao.unitofmeasurement.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.connector.DatabaseManager;
import app.dao.unitofmeasurement.ReadUnitOfMeasurementDao;
import app.db.MySQLDatabaseManager;
import app.model.Group;
import app.model.Item;

public class ReadUnitOfMeasurementDaoImpl implements ReadUnitOfMeasurementDao {

	@Override
	public List<Item> getFromDbUnitsOfMeasurement() {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Item> unitsList = new ArrayList<Item>();
		StringBuilder sql = new StringBuilder(300);

		sql.append("SELECT *FROM `unitsofmeasurement` ");



		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			

			while(rs.next()){
				Item myItem = new Item();
				myItem.setUnitOfMeasurement(rs.getString("units"));
				unitsList.add(myItem);
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


		return unitsList;
	}
}
