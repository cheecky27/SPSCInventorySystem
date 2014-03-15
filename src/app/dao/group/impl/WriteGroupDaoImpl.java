package app.dao.group.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import app.dao.connector.DatabaseManager;
import app.dao.group.WriteGroupDao;
import app.db.MySQLDatabaseManager;
import app.model.Group;

public class WriteGroupDaoImpl implements WriteGroupDao{

	@Override
	public void writeIntoGrpDB(Group groups, Group memId) {
		
		DatabaseManager dm = new MySQLDatabaseManager();
		
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO `inventory system`.`groups`(`prof`, `grpName`,`grpLeader`, `grpMembers`, `studentNo`) VALUES(?,?,?,?,?);");
	
		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{
			
			pr = conn.prepareStatement(sqlUpdate);
			
			pr.setString(1,groups.getProfessor());
			pr.setString(2,groups.getGroupName());
			pr.setString(3, groups.getGroupLeader());
			pr.setString(4,memId.getGroupMembers());
			pr.setString(5, memId.getStudentId());
			
			pr.executeUpdate();
			JOptionPane.showMessageDialog(null, "Succesfully new added group!");
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Sorry, unable to add new group!");
		} finally {
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
/*	public void writeIntoGroup(int foreign1) {
		DatabaseManager dm = new MySQLDatabaseManager();
	
		Connection conn = dm.getConnection();
		StringBuilder sql = new StringBuilder(300);

		sql.append("INSERT INTO groups(eitemid) VALUES(?);");

		String sqlUpdate = sql.toString();
		PreparedStatement pr = null;
		try{
			
			
			pr = conn.prepareStatement(sqlUpdate);
			
			
			pr.setInt(1, foreign1);
			
			
			pr.executeUpdate();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
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

//, instock, outstock,lastReturned, lastBorrowed, remarks, location

	}*/

}
