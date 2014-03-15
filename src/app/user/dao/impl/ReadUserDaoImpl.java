package app.user.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;
import app.dao.user.ReadUserDao;
import app.db.MySQLDatabaseManager;

import app.model.User;

public class ReadUserDaoImpl implements ReadUserDao{

	@Override
	public User getUserInfo() {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		User myUser = new User();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("SELECT *FROM user");
		
		
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			
		
			while(rs.next()){
				
				myUser.setUserName(rs.getString("userName"));
				myUser.setPassWord(rs.getString("passWord"));
				myUser.setSecQues(rs.getString("secQues"));
				myUser.setAnswer(rs.getString("answer"));
				
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


		return myUser;
	}
}
