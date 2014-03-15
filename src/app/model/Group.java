package app.model;

public class Group {
	private int groupID= 0;
	private String professor = "";
	private String groupName= "";
	private String groupLeader = "";
	private String groupMembers = "";
	private String studentId = "";
	private int itemsborrowed = 0;
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}
	public String getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(String groupMembers) {
		this.groupMembers = groupMembers;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getItemsborrowed() {
		return itemsborrowed;
	}
	public void setItemsborrowed(int itemsborrowed) {
		this.itemsborrowed = itemsborrowed;
	}
	
	
}
