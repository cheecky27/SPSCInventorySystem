package app.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;
import app.dao.user.UpdateUserUserNameDao;
import app.db.MySQLDatabaseManager;
import app.model.User;

public class UpdateUserUserNameDaoImpl implements UpdateUserUserNameDao{

	@Override
	public User getUserUserName(String una) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		User myUser = new User();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("UPDATE `inventory system`.`user` SET `userName`=? WHERE `iduser`='1' ");
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			
				
			//	pr.setString(1,myUser.getUserName());
				pr.setString(1,una);
				System.out.println("nagbago ba? "+una);
			//	pr.setString(3,myUser.getAnswer());
				//pr.setString(4,myUser.getSecQues());
				
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


		return myUser;
	}

}

