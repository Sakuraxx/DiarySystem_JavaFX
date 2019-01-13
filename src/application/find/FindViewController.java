package application.find;

import java.time.LocalDate;
import java.util.ArrayList;

import application.MainApp;
import application.model.Dairy;
import application.tools.DialogTool;
import application.tools.JDBCTool;
import application.tools.SingleValueTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;

/**   
* @title: FindDialogController.java 
* @package application.find 
* @description: (找日记的控制器) 
* @author 夏靖雯  
* @date 2018年12月18日 下午9:24:16 
* @version V1.0   
*/
public class FindViewController {
	@FXML
	private TableView<Dairy> dairyTable;
	
	@FXML
	private TableColumn<Dairy, String> titleColumn;
	
	@FXML
	private TableColumn<Dairy, LocalDate> dateColumn;
	
	@FXML
	private TextField titleField;
	
	@FXML
	private TextField feelingField;
	
	@FXML
	private ChoiceBox<String> weatherCBox;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private HTMLEditor htmlEditor;
	
	@FXML
	private ImageView backImg;
	
	@FXML
	private ImageView searchImg;
	
	@FXML
	private TextField searchField;
	
	private MainApp mainApp;
	
	private ObservableList<Dairy> dairyData = FXCollections.observableArrayList();
	
	public ObservableList<Dairy> getDairyData(){
		return dairyData;
	}
	
	@FXML
	private void initialize() {
		//设置搜索图标
		searchImg.setImage(new Image("file:images/search.png"));
		
		//设置天气
		weatherCBox.getItems().addAll(SingleValueTool.getWeaStrs());
		weatherCBox.getSelectionModel().selectFirst();
		
		titleColumn.setCellValueFactory(cellData->cellData.getValue().getTitle());
		dateColumn.setCellValueFactory(cellData->cellData.getValue().getDate());
		
		//将列表中的数据添加到表中
		dairyTable.setItems(dairyData);
		
		//清除日记显示信息
		showDairyDetails(null);
		
		//监听列表选择并将选择的日记详细信息显示在右边 并且显示区域不可以被编辑
		dairyTable.getSelectionModel().selectedItemProperty().addListener(
					(Observable, oldValue, newValue)->{
						showDairyDetails(newValue);
						setEditable(false);});
		
		titleField.setEditable(false);
	}
	
	/**
	 *	显示日记的详细信息
	 */
	private void showDairyDetails(Dairy dairy) {
		if(dairy != null) {
			titleField.setText(dairy.getTitle().getValue());
			feelingField.setText(dairy.getFeeling().getValue());
			weatherCBox.getSelectionModel().select(dairy.getWeather().get());
			datePicker.setValue(dairy.getDate().getValue());
			htmlEditor.setHtmlText(dairy.getContent().getValue());
		}else {
			titleField.setText("");
			feelingField.setText("");
			weatherCBox.getSelectionModel().selectFirst();
			datePicker.setValue(LocalDate.now());
			htmlEditor.setHtmlText("");
		}
	}
	
	/**
	 *	当按钮编辑按钮时，使得显示区域可以被编辑 并拥有可以被更新的权限
	 */
	@FXML
	private void handleEditBtnAction() {
		setEditable(true);
	}
	
	/**
	 *	当显示区域处于可以被编辑的状态时 按下更新按钮弹出提示框 询问用户是否需要更新
	 */
	@FXML
	private void handleUpdateBtnAction() {
		if(feelingField.isEditable()) {
			boolean isUpdate = DialogTool.confirmDialog("更新", "你确定要更新当前的日记吗？");
			if(isUpdate) {
				String sql = "update dairy set feeling = ?, weather = ?, date = ?, content = ? where title = ?";
				boolean isUpdateOk = JDBCTool.executeInsertDeleteUpdate(sql, feelingField.getText(), 
						weatherCBox.getSelectionModel().getSelectedIndex(),
						datePicker.getValue().toString(), htmlEditor.getHtmlText(),
						titleField.getText());
				if(isUpdateOk) {
					DialogTool.informationDialog("更新成功", "日记已经更新成功！");
					
				}else{
					DialogTool.informationDialog("更新失败", "日记更新失败！");
				}
			}
		}else {
			DialogTool.warningDialog("不可编辑", "日记处于不可以编辑状态！");
		}
		setEditable(false);
	}
	
	/**
	 *	设置显示区域是否可以被编辑  
	 */
	private void setEditable(boolean isEditable) {
		feelingField.setEditable(isEditable);
	}
	
	/**
	 *	当点击搜索日记时的处理方法
	 */
	@FXML
	private void handleSearchImgClickedAction() {
		String sql = "select * from dairy where title = ?";
		ArrayList<Dairy> dList = JDBCTool.getDairyList(sql, searchField.getText());
		if(dList.isEmpty() == false) {
			dairyData.clear();
			dairyData.add(dList.get(0));
		}else {
			DialogTool.informationDialog("没有找到日记", "该日记可能不存在");
		}
	}
	
	/**
	 *	点击返回主界面按钮
	 */
	@FXML
	private void handleBackImgClickedAction() {
		mainApp.showSystemView();
	}
	
	/**
	 *	设置对主控制器的引用
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp  = mainApp;
		
		String sql = "select * from dairy where title in (select title from ud where name = ?)";
		ArrayList<Dairy> dairyList = JDBCTool.getDairyList(sql, mainApp.getUser().getUserName());
		dairyData.addAll(dairyList);
	}
}
