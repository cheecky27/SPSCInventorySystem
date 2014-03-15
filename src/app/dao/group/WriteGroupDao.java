package app.dao.group;

import app.model.Group;



public interface WriteGroupDao {
	public void writeIntoGrpDB(Group groups, Group memId); 
	
}
