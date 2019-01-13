package application.forget;

import application.MainApp;
import application.model.User;
import application.tools.DialogTool;
import application.tools.JDBCTool;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**   
* @title: ForgetViewController.java 
* @package application.forget 
* @description: (忘记密码填写用户名视图的控制器) 
* @author 夏靖雯  
* @date 2018年12月24日 下午2:06:43 
* @version V1.0   
*/

public class ForgetViewController {
	
	private MainApp mainApp;
	
	@FXML
	private ImageView backToLoginImg;
	
	@FXML
	private ImageView goToSecretQuesImg;
	
	@FXML
	private TextField userNameField;
	
	@FXML
	private void initialize() {
		backToLoginImg.setImage(new Image("file:images/back.png"));
		goToSecretQuesImg.setImage(new Image("file:images/go.png"));
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	/**
	 *	返回登录视图
	 */
	@FXML
	private void handleBackToLoginImgAction() {
		mainApp.showLoginView();
	}
	
	/**
	 *	前往验证密保视图
	 */
	@FXML
	private void handleGoToSecretQuesImgView() {
		String userName = userNameField.getText();
		if(userName == null) {
			DialogTool.warningDialog("没有输入用户名", "请输入忘记密码的用户名！");
		}else {
			
			String sql = "select * from user where name = ?";
			User user = JDBCTool.getUser(sql, userName);
			if(user == null) {
				DialogTool.warningDialog("用户不存在", "输入的用户不存在，请重新输入");
			}else {
				mainApp.setUser(user);
				mainApp.showSecretQuesView();
			}			
		}
	}
	
}
