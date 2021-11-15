package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
Main method for the pizza store program. 
@author Stephen Juan, David Halim
*/
public class Main extends Application 
{
	// KNOWN ERRORS
	// exporting stuff in a text file doest not work
	
	/**
	Starts the main GUI. (16 lines)
	@param primaryStage the primary stage
	*/
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));  
			Scene scene = new Scene(root); 
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Pizza Shop"); 
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	Runs the program. (4 lines)
	@param args console input
	*/
	public static void main(String[] args) 
	{
		launch(args);
	}
}
