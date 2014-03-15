package app.model;

public class User {

	private int userID=0;
	private String userName= "";
	private String passWord = "";
	private String secQues = "";
	private String answer= "";
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getSecQues() {
		return secQues;
	}
	public void setSecQues(String secQues) {
		this.secQues = secQues;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
