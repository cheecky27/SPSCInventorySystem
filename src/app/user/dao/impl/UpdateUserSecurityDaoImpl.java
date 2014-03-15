package app.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;
import app.dao.user.UpdateSecurityDao;
import app.db.MySQLDatabaseManager;
import app.model.User;

public class UpdateUserSecurityDaoImpl implements UpdateSecurityDao {

	@Override
	public void updateSecurity(User user) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("UPDATE `inventory system`.`user` SET `secQues`=?, `answer`=? WHERE `iduser`='1' ");
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			
				pr.setString(1, user.getSecQues());
				pr.setString(2, user.getAnswer());
	
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
