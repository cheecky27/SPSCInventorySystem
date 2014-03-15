package app.dao.newentry.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import app.dao.connector.DatabaseManager;
import app.dao.newentry.WriteNewItemDao;
import app.db.MySQLDatabaseManager;
import app.model.Item;

public class WriteNewItemDaoImpl implements WriteNewItemDao{

	boolean isAccepted ;


	public boolean writeIntoInventory(Item items) {

		DatabaseManager dm = new MySQLDatabaseManager();

		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO inventory(itemDescription,datecreated, location, quantity,unit, remarks, instock,eitemid ) VALUES(?,?,?,?,?,?,?,?);");

		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{

			pr = conn.prepareStatement(sqlUpdate);
			
			pr.setString(1, items.getItemDes());
			pr.setString(2,items.getDateDelivered());
			pr.setString(3, items.getLocation());
			pr.setInt(4, items.getQuantity());
			pr.setString(5, items.getUnitOfMeasurement());
			pr.setString(6, items.getRemarks());
			pr.setInt(7, items.getQuantity());
			pr.setString(8, items.getItemID());

			pr.executeUpdate();

			isAccepted = true;
		} catch(SQLException e) {
			e.printStackTrace();
			isAccepted = false;
			JOptionPane.showMessageDialog(null, "Sorry, unable to add new item!");
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

		return isAccepted;

	}

}


