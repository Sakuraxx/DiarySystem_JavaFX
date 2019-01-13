package application.model;
/**   
* @title: Dairy.java 
* @package application.model 
* @description: (日记模型类) 
* @author 夏靖雯  
* @date 2018年12月30日 下午9:30:42 
* @version V1.0   
*/

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** 
*  	model class for a dariy.
*/
public class Dairy {
	private StringProperty title;
	private StringProperty feeling;
	private IntegerProperty weatherIndex;
	private ObjectProperty<LocalDate> date;
	private StringProperty content;
	
	public Dairy(String title, String feeling, int weatherIndex, LocalDate date, String content) {
		this.title = new SimpleStringProperty(title);
		this.feeling = new SimpleStringProperty(feeling);
		this.weatherIndex = new SimpleIntegerProperty(weatherIndex);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.content = new SimpleStringProperty(content);
	}
	
	public StringProperty getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public StringProperty getFeeling() {
		return feeling;
	}
	
	public void setFeeling(String feeling) {
		this.feeling.set(feeling);
	}

	public IntegerProperty getWeather() {
		return weatherIndex;
	}
	
	public void setWeather(int weather) {
		this.weatherIndex.set(weather);
	}

	public ObjectProperty<LocalDate> getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date.set(date);
	}
	
	public StringProperty getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content.set(content);
	}
}
