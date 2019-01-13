package application.write;
/**   
* @title: WriteViewController.java 
* @package application.write 
* @description: (写日记视图控制器) 
* @author 夏靖雯  
* @date 2018年12月25日 下午1:12:00 
* @version V1.0   
*/

import java.time.LocalDate;

import application.MainApp;
import application.tools.DialogTool;
import application.tools.JDBCTool;
import application.tools.SingleValueTool;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class WriteViewController {
	@FXML
	private TextField titleField;
	
	@FXML
	private TextField feelingField;
	
	@FXML
	private ChoiceBox<String> weather;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private HTMLEditor htmlEditor;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		//设置天气
//		weather.setItems(FXCollections.observableArrayList(
//				"天晴","下雨","多云","阴天"
//				));
		weather.getItems().addAll(SingleValueTool.getWeaStrs());
		weather.getSelectionModel().selectFirst();
		//默认时间为当前时间
		datePicker.setValue(LocalDate.now());
	}
	
	/**
	 *	取消保存日记并返回到日记主界面
	 */
	@FXML
	private void handleCancelAndReturnAction() {
		boolean isReturn = DialogTool.confirmDialog("取消", "你真的要取消保存日记并返回到日记主界面吗？");
		if(isReturn) {
			//切换到日记主界面
			mainApp.showSystemView();
		}
	}
	
	/**
	 *	提交日记
	 */
	@FXML
	private void handleSaveAction() {
		boolean isCommit = DialogTool.confirmDialog("保存", "是否保存所写日记？");
		if(isCommit) {
			String title = titleField.getText();
			String feeling = feelingField.getText();
			int weaStrIndex = weather.getSelectionModel().getSelectedIndex();
			LocalDate date = datePicker.getValue();
			String content = htmlEditor.getHtmlText();
			
			if("".equals(title) || "".equals(feeling) || date==null || "".equals(content)) {
				DialogTool.confirmDialog("内容为空", "请填写完要求内容，不能为空");
			}else {
				String sql1 = "insert into dairy(title, feeling, weather, date, content) values(?,?,?,?,?) ";
				boolean isInsertOk1 = JDBCTool.executeInsertDeleteUpdate(sql1, title, feeling, weaStrIndex, date.toString(), content);
				String sql2 = "insert into ud(name,title) values(?, ?)";
				boolean isInsertOK2 = JDBCTool.executeInsertDeleteUpdate(sql2, mainApp.getUser().getUserName(), title);
				
				if(isInsertOk1 && isInsertOK2) {
					DialogTool.informationDialog("日记保存成功", "即将现在返回主界面");
					mainApp.showSystemView();
				}else {
					DialogTool.informationDialog("日记保存失败", "数据库插入失败");
				}
			}
		}	
	}
	
	/**
	 *	设置对主控制器的引用
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
