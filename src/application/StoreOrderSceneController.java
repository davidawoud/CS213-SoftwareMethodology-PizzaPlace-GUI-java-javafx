package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
This is the controller for the store orders window.
@author Stephen Juan, David Halim
*/
public class StoreOrderSceneController
{
    @FXML
    private ComboBox<String> phoneDropdown;
    @FXML
    private TextField orderTotal;
    @FXML
    private ListView<String> orderList;
    @FXML
    private Button cancelButton, exportButton;
    
	private MainSceneController mainController; 
    
	/**
	This sets the main controller as the origin for data sharing. (4 lines)
	@param controller - main controller
	*/
	public void setMainController(MainSceneController controller)
	{
		mainController = controller; 
	}
    
    /**
    This method sets all the phone numbers in the drop down menu. (5 lines)
    */
    public void setPhoneDropdownMenu()
    {
    	// add all phone numbers in StoreOrder to the dropdown menu
    	phoneDropdown.getItems().addAll(mainController.getStoreOrder().getArrOfPhone());
    }
    
	/**
	This method displays a warning message if there is no orders to be cancelled. (7 lines)
	*/
	private void nothingSelected()
	{
    	Alert a = new Alert(AlertType.WARNING,"Nothing Selected",ButtonType.OK);
    	a.setHeaderText("No Order Selected!");
    	a.setContentText("Select an order to cancel!");
    	a.showAndWait();
	}
    
	/**
	This method displays all the orders under a phone number. (18 lines)
	@param event mouse click
	*/
    @FXML
    void displayPhoneOrder(ActionEvent event)
    {
        StoreOrders storeOrders = mainController.getStoreOrder();
    	
    	String phoneNumber = phoneDropdown.getValue();
    	
    	// find selected order
    	Order order = storeOrders.findOrder(phoneNumber);
    	if (order != null)
    	{
    	   	// set total amount
        	orderTotal.setText("" + order.getOrderTotal());
        	
        	// display the pizza orders
        	String[] pizzas = order.getArrOfPizza();
        	orderList.getItems().setAll(pizzas);
    	}
    }
    
    /**
    This methods deletes an order from the store orders. (27 lines)
    @param event mouse click
    */
    @FXML
    void cancel_order(ActionEvent event)
    {
    	StoreOrders storeOrders = mainController.getStoreOrder();
    	
    	// exception handling: check to see if its selected
    	if (phoneDropdown.getSelectionModel().isEmpty())
    	{
    		nothingSelected();
    	}
    	else
    	{
        	String phoneNumber = phoneDropdown.getValue();
        	
        	// remove that phoneNumber from phoneDropDown
        	phoneDropdown.getItems().remove(phoneNumber);
        	
        	// clear the text box and list view
        	orderTotal.clear();
        	orderList.getItems().clear();
        	
        	// delete the Order with the phone number selected from StoreOrders
        	storeOrders.removeOrder(phoneNumber);
        	
        	// sets the new storeorder value in main
        	mainController.setStoreOrder(storeOrders);
    	}
    }
    
    /**
    This method exports the order to a text file. (17 lines)
    @param event mouse click
    @throws IOException if the file is not able to found
    */
    @FXML
    void export_order(ActionEvent event) throws IOException 
    {
    	// calls the export() method of StoreOrder
    	StoreOrders storeOrders = mainController.getStoreOrder();
    	
    	storeOrders.export();
    	
    	// clear everything
    	phoneDropdown.getItems().clear();
    	orderTotal.clear();
    	orderList.getItems().clear(); 
    	
    	Alert a = new Alert(AlertType.CONFIRMATION,"Exported",ButtonType.OK);
    	a.setHeaderText("File Exported");
    	a.setContentText("File exported to Store Orders.txt");
    	a.showAndWait();
    }
}
