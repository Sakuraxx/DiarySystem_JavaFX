package application.forget;

import application.MainApp;
import application.tools.CheckValidTool;
import application.tools.DialogTool;
import application.tools.JDBCTool;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**   
* @title: ResetPasswdViewController.java 
* @package application.forget 
* @description: (重置密码视图控制器) 
* @author 夏靖雯  
* @date 2018年12月24日 下午8:06:59 
* @version V1.0   
*/

public class ResetPasswdViewController {
	private MainApp mainApp;
		
	@FXML
	private TextField resetPasswdField;
	
	@FXML
	private TextField confirmPasswdField;
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleConfirmButtonAction() {
		String resetPasswd = resetPasswdField.getText();
		String confirmPasswd = confirmPasswdField.getText();
		if(CheckValidTool.isValidPassword(resetPasswd)&&CheckValidTool.isValidPassword(confirmPasswd)) {
			if(resetPasswd.equals(confirmPasswd)) {
				String sql = "update user set password = ? where name = ?";
				boolean isUpdateOk = JDBCTool.executeInsertDeleteUpdate(sql, resetPasswd, mainApp.getUser().getUserName());
				if(isUpdateOk) {
					DialogTool.informationDialog("重置密码成功", "稍后将返回到登录界面");
					mainApp.getUser().setPassword(resetPasswd);
					mainApp.showLoginView();
				}else {
					DialogTool.informationDialog("重置密码失败", "数据库更新失败");
				}
			}else {
				DialogTool.informationDialog("重置密码失败", "两次密码不一致");
				resetPasswdField.setText("");
				confirmPasswdField.setText("");
			}
		}else {
			DialogTool.informationDialog("重置密码失败", "密码不合法 请重新输入");
			resetPasswdField.setText("");
			confirmPasswdField.setText("");
		}
	}
	
	/**
	 *	获取对主控器的引用
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
