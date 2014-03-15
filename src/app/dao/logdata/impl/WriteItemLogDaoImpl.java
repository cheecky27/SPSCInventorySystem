package app.dao.logdata.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import app.dao.connector.DatabaseManager;
import app.dao.logdata.WriteItemLogDao;
import app.dao.newentry.impl.ReadNewItemDaoImpl;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class WriteItemLogDaoImpl implements WriteItemLogDao{

	@Override
	public void writeIntoLog(Item items) {
		DatabaseManager dm = new MySQLDatabaseManager();
		System.out.println("magsusulat ako wahaha");
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO log(eitemid, remarks, date, quantity, location, itemdes ) VALUES(?,?,?,?,?,?);");

		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{


			pr = conn.prepareStatement(sqlUpdate);
			pr.setString(1, items.getItemID());
			pr.setString(2, items.getRemarks());
			pr.setString(3, items.getDateDelivered());
			pr.setInt(4, items.getQuantity());
			pr.setString(5, items.getLocation());
			pr.setString(6, items.getItemDes());
			pr.executeUpdate();





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



	}

}
