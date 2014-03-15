package app.dao.selected.group.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.dao.connector.DatabaseManager;
import app.dao.selected.group.UpdateSelectedGroupDao;
import app.db.MySQLDatabaseManager;
import app.model.Group;

public class UpdateSelectedGroupDaoImpl implements UpdateSelectedGroupDao {

	@Override
	public void UpdateGroup(Group group) {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);
		
		sql.append("UPDATE `inventory system`.`groups` SET `prof`=?, `grpName`=?, `grpMembers`=? WHERE `idgroup`='"+group.getGroupID()+"'");
		
		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			
			pr.setString(1, group.getProfessor());
			pr.setString(2, group.getGroupName());
			pr.setString(3, group.getGroupMembers());
			
			pr.executeUpdate();					
			System.out.println("ito ha:"+group.getGroupName()+group.getProfessor()+group.getGroupMembers());
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


	}


}
