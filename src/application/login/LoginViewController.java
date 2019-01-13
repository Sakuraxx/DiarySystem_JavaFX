package application.login;

import application.MainApp;
import application.model.User;
import application.tools.CheckValidTool;
import application.tools.DialogTool;
import application.tools.JDBCTool;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/**   
* @title: LoginDialogController.java 
* @package application.login 
* @description: (登录的控制器) 
* @author 夏靖雯  
* @date 2018年12月18日 下午4:17:14 
* @version V1.0   
*/

public class LoginViewController {
	private MainApp mainApp;
	
	@FXML
	private Label userNameLabel;
	
	@FXML
	private Label passwordLabel;
	
	@FXML
	private Button LoginButton;
	
	@FXML
	private Button registButton;
	
	@FXML
	private ImageView leftImageView;
	
	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField passwordField;
	
	@FXML
	private Label errorInfoLabel;
	
	/**
	 * 获取主控制器的引用
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	//在fxml文件被加载后自动调用
	@FXML
	private void initialize() {
		
		userNameLabel.setGraphic(new ImageView(new Image("file:images/user.png")));
		passwordLabel.setGraphic(new ImageView(new Image("file:images/password.png")));
		leftImageView.setImage(new Image("file:images/leftimage.png"));
		
		leftImageView.setPreserveRatio(true);
		leftImageView.setSmooth(true);
	}
	
	/**
	 * 处理登录 到主界面的事件
	 */
	@FXML
	private void handleLoginButtonAction() {
		
		String userName = userNameField.getText();
		String password = passwordField.getText();
		
		if(CheckValidTool.isValidUserName(userName) && CheckValidTool.isValidPassword(password)) {
			String sql = "select * from user where name=? and password=?";
			User user= JDBCTool.getUser(sql, userName, password);
			if(user != null) {
				//设置当前用户
				mainApp.setUser(user);
				DialogTool.informationDialog("登录成功", "即将进入主界面");
				//显示主界面
				mainApp.showSystemView();
			}else {
				errorInfoLabel.setText("用户名或密码不正确");
				userNameField.clear();
				passwordField.clear();
				//登录失败做出渐变渐显的效果
				FadeTransition ft = new FadeTransition();
				ft.setDuration(Duration.seconds(0.1));
				ft.setNode(mainApp.getScene().getRoot());
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.play();
			}
		}else {
			errorInfoLabel.setText("用户名或密码不合法");
			userNameField.clear();
			passwordField.clear();
			FadeTransition ft = new FadeTransition();
			ft.setDuration(Duration.seconds(0.1));
			ft.setNode(mainApp.getScene().getRoot());
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.play();
		}
	
	}
	
	/**
	 * 处理注册按钮事件
	 */
	@FXML
	private void handleRegistButtonAction() {
		mainApp.showRegistView();
	}
	
	/**
	 * 处理忘记密码事件
	 */
	@FXML
	private void handleForgetPasswordAction() {
		mainApp.showForgetView();
	}
}