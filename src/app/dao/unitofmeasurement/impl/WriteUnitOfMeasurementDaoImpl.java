package app.dao.unitofmeasurement.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import app.dao.connector.DatabaseManager;
import app.dao.newentry.impl.ReadNewItemDaoImpl;
import app.dao.unitofmeasurement.WriteUnitOfMeasurementDao;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class WriteUnitOfMeasurementDaoImpl implements WriteUnitOfMeasurementDao {


	boolean isDuplicate = false;
	public boolean writeIntoDbUnits(Item units) {
		DatabaseManager dm = new MySQLDatabaseManager();
		int beses = 0;
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO unitsofmeasurement(units ) VALUES(?);");

		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{
			beses++;
			System.out.println("beses: "+ beses);
			pr = conn.prepareStatement(sqlUpdate);

			pr.setString(1, units.getUnitOfMeasurement());

			pr.executeUpdate();





		} catch(SQLException e) {
			//	e.printStackTrace();
			isDuplicate = true;
			JOptionPane.showMessageDialog(null, "Sorry, unable to add new unit!!");

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
		return isDuplicate;
	}


}

