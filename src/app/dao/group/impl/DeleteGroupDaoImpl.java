package app.dao.group.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;
import app.dao.group.DeleteGroupDao;
import app.db.MySQLDatabaseManager;
import app.model.Group;

public class DeleteGroupDaoImpl implements DeleteGroupDao {

	@Override
	public int deleteGroup(int myGroup) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("DELETE FROM `groups` WHERE `idgroup`='"+myGroup+"'");
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			
			pr.executeUpdate();					
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Hindi kaya!!!!");
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

		return myGroup;
	}


}

