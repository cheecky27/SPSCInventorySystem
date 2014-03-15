package app.dao.group;

import java.util.List;

import app.model.Group;



public interface ReadGroupsDao {
	List<Group> showGroups();
	List<Group> deleteGroups(Group grpID);
}
