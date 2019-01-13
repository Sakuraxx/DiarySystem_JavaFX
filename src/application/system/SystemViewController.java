package application.system;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**   
* @title: SystemDialogController.java 
* @package application.system 
* @description: (日记主界面控制器) 
* @author 夏靖雯  
* @date 2018年12月18日 下午4:24:42 
* @version V1.0   
*/
public class SystemViewController {
	private MainApp mainApp;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private void initialize() {
	
	}
	
	/**
	 *	退出登录
	 */
	@FXML
	private void handleLogoutLabelClickedAction() {
		mainApp.showLoginView();
		mainApp.setUser(null);
	}

	/**
	 *	跳转查找日记视图
	 */
	@FXML
	private void handleFindBtnAction() {
		mainApp.showFindView();
	}
	
	/**
	 *	跳转写日记视图
	 */
	@FXML
	private void handleWriteBtnAction() {
		mainApp.showWriteView();
	}
	
	/**
	 *	得到主控制器的引用
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		nameLabel.setText(mainApp.getUser().getUserName());
	}
	
}
