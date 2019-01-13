package application;
	
import java.io.IOException;

import application.find.FindViewController;
import application.forget.ForgetViewController;
import application.forget.ResetPasswdViewController;
import application.forget.SecretQuesViewController;
import application.login.LoginViewController;
import application.model.User;
import application.regist.RegistViewController;
import application.system.SystemViewController;
import application.write.WriteViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {
	private Stage stage;
	private Scene scene;
	//标识当前用户
	private User user;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage  = primaryStage;
			showLoginView();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	显示登录界面
	 */
	public void showLoginView() {
		try {
			stage.setTitle("Login");
			stage.getIcons().add(new Image("file:images/login.png"));
			LoginViewController lgController = (LoginViewController)replaceSceneContent("login/LoginView.fxml");
			lgController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	显示忘记密码界面
	 */
	public void showForgetView() {
		try {
			stage.setTitle("Forget");
			ForgetViewController fgController = (ForgetViewController)replaceSceneContent("forget/ForgetView.fxml");
			fgController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *	显示密保问题验证界面
	 */
	public void showSecretQuesView() {
		try {
			stage.setTitle("SecretQuestion");
			SecretQuesViewController sqController = (SecretQuesViewController)replaceSceneContent("forget/SecretQuesView.fxml");
			sqController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	显示重置密码界面
	 */
	public void showResetPasswdView() {
		try {
			stage.setTitle("ResetPasswdView");
			ResetPasswdViewController rpController = (ResetPasswdViewController)replaceSceneContent("forget/ResetPasswdView.fxml");
			rpController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *	显示注册界面
	 */
	public void showRegistView() {
		try {
			
			stage.setTitle("Regist");
			stage.getIcons().add(new Image("file:images/regist.png"));
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("regist/RegistView.fxml"));
			BorderPane bp = (BorderPane)loader.load();
			scene = new Scene(bp);
			stage.setScene(scene);
			stage.setResizable(false);
			
			RegistViewController regController = (RegistViewController)loader.getController();
			System.out.println(regController);
			regController.setMainApp(this);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *	显示写日记视图
	 */
	public void showWriteView() {
		try {
			stage.setTitle("Write");
			stage.getIcons().add(new Image("file:images/write.png"));
			
			WriteViewController wController = (WriteViewController)replaceSceneContent("write/WriteView.fxml");
			wController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *	显示查找日记视图
	 */
	public void showFindView() {
		try {
			stage.setTitle("Find");
			stage.getIcons().add(new Image("file:images/find.png"));
			
			FindViewController fController = (FindViewController)replaceSceneContent("find/FindView.fxml");
			fController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *	显示日记系统主界面
	 */
	public void showSystemView() {
		try {
			stage.setTitle("SDSystem");
			stage.getIcons().add(new Image("file:images/home.png"));
			
			SystemViewController sdController = (SystemViewController)replaceSceneContent("system/SystemView.fxml");
			sdController.setMainApp(this);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	显示指定的视图
	 */
	private Object replaceSceneContent(String fxmlFile) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource(fxmlFile));
		
		AnchorPane ap = null;
		try {
			ap = (AnchorPane)loader.load();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		scene = new Scene(ap);
		stage.setScene(scene);
		stage.setResizable(false);
		
		return loader.getController();
		
	}
	
	/**
	 *	获取scene
	 */
	public Scene getScene() {
		return scene;
	}
	
	/**
	 *	获取Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
