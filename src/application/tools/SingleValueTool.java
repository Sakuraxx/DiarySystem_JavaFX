package application.tools;
/**   
* @title: SingleValueTool.java 
* @package application.tools 
* @description: (密保问题，天气情况单选值工具类) 
* @author 夏靖雯  
* @date 2019年1月2日 上午9:45:17 
* @version V1.0   
*/

public class SingleValueTool {
	/**
	 *	获取天气情况数组
	 */
	public static String[] getWeaStrs() {
		String[] weaStrs = new String[] {"天晴","下雨","多云","阴天"} ;
		return weaStrs;
	}
	
	/**
	 *	获取被选中的天气的字符串值
	 */
	public static String getWeaStrSelected(int index) {
		String[] weaStrs = new String[] {"天晴","下雨","多云","阴天"} ;
		return weaStrs[index];
	}
	
	/**
	 *	获取密保问题
	 */
	public static String[] getSecQues() {
		String[] ques = new String[]{"你最喜欢的动漫角色是谁？","你最喜欢的女明星是谁？","对你启发最大的一本书是什么？"};
		return ques;
	}
	
}
