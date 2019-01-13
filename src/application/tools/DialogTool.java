package application.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

/**   
* @title: DialogTool.java 
* @package application.tools 
* @description: (各种提示对话框) 
* @author 夏靖雯  
* @date 2018年12月24日 下午11:29:50 
* @version V1.0   
*/

public class DialogTool {
	
	/**
	 *	确认对话框
	 */
	public static boolean  confirmDialog(String header, String content) {
		//https://blog.csdn.net/Howinfun/article/details/82722519
		////使用数组来解决lambda表达式无法修改局部变量的问题
		boolean[] isClickedOk = new boolean[] {false};
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setGraphic(new ImageView(new Image("file:images/confirm.png")));
		alert.setTitle("确认");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait().filter(response->response==ButtonType.OK)
		.ifPresent(response->isClickedOk[0] = true);
		
		return isClickedOk[0];
	}
	
	/**
	 *	消息提示对话框
	 */
	public static void informationDialog(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setGraphic(new ImageView(new Image("file:images/message.png")));
		alert.setTitle("消息");
		alert.setHeaderText(header);
		alert.setContentText(content);
		//显示弹窗，同时后续代码等挂起
		alert.showAndWait();
	}
	
	/**
	 *	警告消息对话框
	 */
	public static void warningDialog(String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setGraphic(new ImageView(new Image("file:images/warn.png")));
		alert.setTitle("警告");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	/**
	 *	错误消息对话框
	 */
	public static void errorDialog(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setGraphic(new ImageView(new Image("file:images/error.png")));
		alert.setTitle("错误");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}	
}


