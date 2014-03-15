package app.dao.group.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.dao.connector.DatabaseManager;
import app.dao.group.ReadGroupsDao;
import app.db.MySQLDatabaseManager;
import app.model.Group;
import app.model.Item;


public class ReadGroupDaoImpl implements ReadGroupsDao{

	@Override
	public List<Group> showGroups() {
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Group> groupList = new ArrayList<Group>();
		StringBuilder sql = new StringBuilder(300);

		sql.append("SELECT *FROM groups");



		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			rs = pr.executeQuery();			

			while(rs.next()){
				Group myGroup = new Group();
				myGroup.setGroupID(rs.getInt("idgroup"));
				myGroup.setProfessor(rs.getString("prof"));
				myGroup.setGroupName(rs.getString("grpName"));
				myGroup.setGroupLeader(rs.getString("grpLeader"));
				myGroup.setGroupMembers(rs.getString("grpMembers"));
				myGroup.setStudentId(rs.getString("studentNo"));

				groupList.add(myGroup);
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


		return groupList;
	}

	public List<Group> deleteGroups(Group grpId){
		DatabaseManager dm = new MySQLDatabaseManager();
		Connection conn = dm.getConnection();
		List<Group> groupList = new ArrayList<Group>();
		StringBuilder sql = new StringBuilder(300);


		sql.append("DELETE FROM groups WHERE idgroup LIKE ? ");

		String sqlQuery = sql.toString();
		PreparedStatement pr = null;
		ResultSet rs = null;
		try{
			pr = conn.prepareStatement(sqlQuery);
			pr.setInt(1, grpId.getGroupID());
			pr.execute();

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


		return groupList;
	}
}

