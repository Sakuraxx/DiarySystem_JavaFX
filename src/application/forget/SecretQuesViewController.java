package application.forget;

import application.MainApp;
import application.tools.DialogTool;
import application.tools.SingleValueTool;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**   
* @title: SecretQuesViewController.java 
* @package application.forget 
* @description: (密保问题视图控制器) 
* @author 夏靖雯  
* @date 2018年12月24日 下午8:05:46 
* @version V1.0   
*/
public class SecretQuesViewController {
	private MainApp MainApp;
	
	@FXML
	private ImageView backToForgetImg;
	
	@FXML
	private ImageView goToResetPasswdImg;
	
	@FXML
	private Label secretQuestionLabel;
	
	@FXML
	private TextField secretQuesAnsField;

	@FXML
	private void initialize() {
		backToForgetImg.setImage(new Image("file:images/back.png"));
		goToResetPasswdImg.setImage(new Image("file:images/go.png"));
	}
	
	/**
	 *	返回忘记密码填写用户密码视图
	 */
	@FXML
	private void handleBackToForgetImgAction() {
		MainApp.showForgetView();
	}
	
	
	/**
	 *	前往重置面视图
	 */
	@FXML
	private void handleGoToResetPasswdImgAction() {
		String answer = this.MainApp.getUser().getSecAnswer();
		if(secretQuesAnsField.getText() == null) {
			DialogTool.warningDialog("密保答案为空", "请填写密保答案");
		}else {			
			if(answer.equals(secretQuesAnsField.getText())) {			
				MainApp.showResetPasswdView();
			}else {
				DialogTool.warningDialog("密保答案错误", "请重新输入密保答案");
				secretQuesAnsField.setText("");
			}
		}
	}
	
	/**
	 *	设置对主控制器的引用
	 */
	public void setMainApp(MainApp mainApp) {
		this.MainApp = mainApp;
		int index = this.MainApp.getUser().getSecQuesIndex();
		this.secretQuestionLabel.setText(SingleValueTool.getSecQues()[index]);
	}
	
}
