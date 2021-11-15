package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
This is the scene controller for the main GUI window. 
@author Stephen Juan, David Halim
*/
public class MainSceneController 
{
	@FXML
	private Pane mainPane, titlePane;
	@FXML
	private TextField phoneNumberBox; 
	@FXML
    private ImageView delPizza, hawPizza, pepPizza;
	@FXML
    private ImageView currentOrder, storeOrder;

	private StoreOrders allStoreOrder = new StoreOrders(); 
	private Order allCurrentOrder = new Order(""); 
	private boolean isOngoingOrder = true;
	
	/**
	This method checks if the phone number is valid, 10 digits and unused. (25 lines)
	@return true if its valid, false if otherwise
	*/
	private boolean isValidPhone()
	{
		// check if the text box is empty
		if (phoneNumberBox.getText().equals(""))
			return false; 
		String phoneNumber = phoneNumberBox.getText();
		// check if the phone number is 10 digits
		if (phoneNumber.length() != 10)
			return false;
		
		// CHECK IF THE PHONE NUMBER IS ALREADY IN StoreOrder
		String[] allNumbers = allStoreOrder.getArrOfPhone();
		for (int i = 0; i < allNumbers.length; i++)
		{
			if (phoneNumberBox.getText().equals(allNumbers[i]))
				return false;
		}
		
		// loop through the phone number to check if its all digit
		for (int i = 0; i < phoneNumber.length(); i++)
		{
			if (!Character.isDigit(phoneNumber.charAt(i)))
				return false; 
		}
		return true; 
	}
    
    /**
    This method gets the current order. (4 lines)
    @return the current order
    */
    public Order getCurrentOrder()
    {
    	return allCurrentOrder;
    }
    
    /**
    This method returns the current store order. (4 lines)
    @return the current store order
    */
    public StoreOrders getStoreOrder()
    {
    	return allStoreOrder; 
    }
    
    /**
    This method sets the current order. (4 lines)
    @param order - order to be replaced as
    */
    public void setCurrentOrder(Order order)
    {
    	allCurrentOrder = order; 
    }
    
    /**
    This method sets the current store order. (4 lines)
    @param order - store order to be replaced as
    */
    public void setStoreOrder(StoreOrders order)
    {
    	allStoreOrder = order; 
    }
	
    /**
    This method sets that the current order is not ongoing.  (4 lines)
    */
    public void setIsOngoingOrder()
    {
    	isOngoingOrder = true; 
    }
    
	/**
	This method shows the alert window when the user clicks on any pizza. (7 lines)
	*/
	private void orderingAlertWindow()
	{
    	Alert a = new Alert(AlertType.CONFIRMATION,"Pizza Order",ButtonType.OK);
    	a.setHeaderText("Ordering Pizzas");
    	a.setContentText("Starting a new order!");
    	a.showAndWait();
	}
	
	/**
	This method shows the alert window when someone tries to put in an invalid phone number. (7 lines)
	*/
	private void invalidPhone()
	{
        Alert a = new Alert(AlertType.WARNING,"WARNING",ButtonType.OK);
        a.setHeaderText("Warning");
        a.setContentText("Invalid phone number!");
        a.showAndWait();
	}
	
	/**
	This method opens the new window to order a deluxe pizza. (39 lines)
	@param event mouse click
	*/
    @FXML
    void order_d_pizza(MouseEvent event) 
    {
    	if (!isValidPhone())
    		invalidPhone();
    	else
    	{
        	orderingAlertWindow();
			try 
			{
				if (isOngoingOrder) 
				{
					allCurrentOrder = new Order(phoneNumberBox.getText());
					isOngoingOrder = false; 
				}
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaView.fxml"));
				
	    		Parent root = (Parent) loader.load();
	    		PizzaSceneController pizza = loader.getController(); 
	    		
	    		pizza.setImage("deluxe");
	    		pizza.setDefaultToppings("deluxe");
	    		pizza.setPhoneNumber(phoneNumberBox.getText());
	    		pizza.setMainController(this);
	
				Stage stage = new Stage();
				Scene scene = new Scene(root); 
				stage.setScene(scene);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setTitle("Deluxe Pizza Order"); 
				stage.setScene(scene);
				stage.show();
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
    	}
    }
    
	/**
	This method opens the new window to order a hawaiian pizza. (39 lines)
	@param event mouse click
	*/
    @FXML 
    void order_h_pizza(MouseEvent event) 
    {
    	if (!isValidPhone())
    		invalidPhone();
    	else
    	{
        	orderingAlertWindow();
			try 
			{
				if (isOngoingOrder) 
				{
					allCurrentOrder = new Order(phoneNumberBox.getText());
					isOngoingOrder = false; 
				}
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaView.fxml"));
				
	    		Parent root = (Parent) loader.load();
	    		PizzaSceneController pizza = loader.getController(); 
	    		
	    		pizza.setImage("hawaiian");
	    		pizza.setDefaultToppings("hawaiian");
	    		pizza.setPhoneNumber(phoneNumberBox.getText());
	    		pizza.setMainController(this);
	
				Stage stage = new Stage();
				Scene scene = new Scene(root); 
				stage.setScene(scene);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setTitle("Deluxe Pizza Order"); 
				stage.setScene(scene);
				stage.show();
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
    	}
    }
    
	/**
	This method opens the new window to order a pepperoni pizza. (39 lines)
	@param event mouse click
	*/
    @FXML
    void order_p_pizza(MouseEvent event) 
    {
    	if (!isValidPhone())
    		invalidPhone();
    	else
    	{
        	orderingAlertWindow();
			try 
			{
				if (isOngoingOrder) 
				{
					allCurrentOrder = new Order(phoneNumberBox.getText());
					isOngoingOrder = false; 
				}
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaView.fxml"));
				
	    		Parent root = (Parent) loader.load();
	    		PizzaSceneController pizza = loader.getController(); 
	    		
	    		pizza.setImage("pepperoni");
	    		pizza.setDefaultToppings("pepperoni");
	    		pizza.setPhoneNumber(phoneNumberBox.getText());
	    		pizza.setMainController(this);
	
				Stage stage = new Stage();
				Scene scene = new Scene(root); 
				stage.setScene(scene);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setTitle("Deluxe Pizza Order"); 
				stage.setScene(scene);
				stage.show();
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
    	}
    }
    
	/**
	This method opens the window to display all pizzas in the order. (33 lines)
	@param event mouse click
	*/
    @FXML
    void display_current_orders(MouseEvent event) 
    {
    	if (!isValidPhone())
    		invalidPhone();
    	else
    	{
			try
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderView.fxml"));
				
	    		Parent root = (Parent) loader.load();
	    		
	    		CurrentOrderSceneController currentOrder = loader.getController(); 
	    		
	    		currentOrder.setPhoneNumber(phoneNumberBox.getText());
	    		currentOrder.setMainController(this);
	    		currentOrder.displayPizzaOrders();
	    		currentOrder.setCosts();
	
				Stage stage = new Stage();
				Scene scene = new Scene(root); 
				stage.setScene(scene);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setTitle("Current Order"); 
				stage.setScene(scene);
				stage.show();
			} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
    	}
    }
    
	/**
	This method opens the window to display all orders in the store. (26 lines)
	@param event mouse click
	*/
    @FXML
    void display_store_orders(MouseEvent event) 
    {
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrderView.fxml"));
			
    		Parent root = (Parent) loader.load();
    		
    		StoreOrderSceneController storeOrder = loader.getController(); 
    		
    		storeOrder.setMainController(this);
    		storeOrder.setPhoneDropdownMenu();

			Stage stage = new Stage();
			Scene scene = new Scene(root); 
			stage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Current Order"); 
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
    }
}
