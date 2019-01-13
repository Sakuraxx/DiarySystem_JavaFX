package application.model;
/**   
* @title: User.java 
* @package application.model 
* @description: (用户模型类) 
* @author 夏靖雯  
* @date 2018年12月31日 下午3:55:17 
* @version V1.0   
*/

public class User {
	private String userName;
	private String password;
	private int secQuesIndex;
	private String secAnswer;
	
	public User(String userName, String password, int secQuesIndex, String secAnswer) {
		this.userName = userName;
		this.password = password;
		this.secQuesIndex = secQuesIndex;
		this.secAnswer = secAnswer;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSecQuesIndex() {
		return secQuesIndex;
	}

	public void setSecQuesIndex(int secQuesIndex) {
		this.secQuesIndex = secQuesIndex;
	}

	public String getSecAnswer() {
		return secAnswer;
	}

	public void setSecAnswer(String secAnswer) {
		this.secAnswer = secAnswer;
	}
}
