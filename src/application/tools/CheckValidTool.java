package application.tools;

import java.util.regex.Pattern;

/**   
* @title: CheckValidTool.java 
* @package application.tools 
* @description: (检查用户输入的合法性) 
* @author 夏靖雯  
* @date 2018年12月19日 下午4:05:45 
* @version V1.0   
*/

public class CheckValidTool {
	/**
	 *  检查用户输入用户名的合法性
	 *  
	 *  @param userName 用户名
	 *  @return boolean
	 */
	public  static boolean isValidUserName(String userName) {
		String patternName = "^[a-zA-Z]\\w{5,19}$";
		boolean matchName = Pattern.matches(patternName, userName);
		if(matchName) {
			return true;
		}
		return false;
	}
	
	/**
	 *	检查用户输入的密码是否合法
	 *
	 *	@param password 密码
	 *	@return boolean
	 */
	public static boolean isValidPassword(String password) {
		String patternPasswd = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{8,30}$";		//密码包含字母、数字和特殊字符
		boolean matchPasswd = Pattern.matches(patternPasswd, password);
		if(matchPasswd) {
			return true;
		}
		return false;
	}
	
	/**
	 *	检查用户输入的邮箱是否合法
	 *
	 *	@param mail 邮箱
	 *	@return boolean
	 */
	public static boolean isValidMail(String mail) {
		String patternMail = "^\\w[\\w_-]+@[\\w_-]+(\\.[\\w]+)+$";
		boolean  matchMail = Pattern.matches(patternMail, mail);
		if(matchMail) {
			return true;
		}
		return false;
	}
	
	
	
}
