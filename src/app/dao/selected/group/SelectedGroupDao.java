package app.dao.selected.group;

import java.util.List;

import app.model.Group;

public interface SelectedGroupDao {

	List<Group> getSelectedGroupInfo(String selectedGroup);
}
