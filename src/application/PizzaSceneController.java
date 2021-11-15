package application;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
Controller class for the pizza window. 
@author Stephen Juan, David Halim
*/
public class PizzaSceneController 
{
	@FXML 
	private ImageView pizzaPicture;
	@FXML
	private Label sizeLabel;
	@FXML
	private ListView<String> selectedBox;
	@FXML
	private ListView<String> availableBox; 
	@FXML
	private MenuItem smallPizza, mediumPizza, largePizza; 
	@FXML
	private Button addButton, removeButton;
	@FXML
	private TextArea priceBar;
	@FXML
	private Button addOrder; 
	
	private String type, phoneNumber; 
	private String[] selectedToppings = {}; 
	private String[] availableToppings;
	private int size = -1;
	
	private Pizza currentPizza;
	
	private MainSceneController mainController; 
	
	/**
	Sets the main controller as the origin of this window. (4 lines)
	@param controller - the main controller of the window
	*/
	public void setMainController(MainSceneController controller)
	{
		mainController = controller; 
	}
	
	/**
	This method sets the image of the pizza. (23 lines)
	@param type type of pizza
	@throws Exception - if create pizza is not on the list of flavors
	*/
	public void setImage(String type) throws Exception
	{
		this.type = type; 
		currentPizza = PizzaMaker.createPizza(type);
    	if (type.equals("deluxe")) 
    	{ 
    		File file = new File("d_pizza.PNG");
            Image image = new Image(file.toURI().toString());
            pizzaPicture.setImage(image);
    		
    	}
    	if (type.equals("hawaiian")) 
    	{ 
    		File file = new File("h_pizza.PNG");
            Image image = new Image(file.toURI().toString());
            pizzaPicture.setImage(image);
    	}
    	if (type.equals("pepperoni")) 
    	{ 
    		File file = new File("p_pizza.PNG");
            Image image = new Image(file.toURI().toString());
            pizzaPicture.setImage(image);
    	}
	}
	
	/**
	This method sets the default values for toppings for each type of pizza. (31 lines)
	@param type - type of pizza
	*/
	public void setDefaultToppings(String type)
	{
		if (type.equals("deluxe")) 
    	{
            String[] dAvailable = {"Chicken", "Beef", "Ham", "Cheese", "Pepper", "Pineapple"};
            String[] dSelected = currentPizza.getArrToppings();
            availableToppings = dAvailable; 
            selectedToppings = dSelected;
    		
    	}
    	if (type.equals("hawaiian")) 
    	{ 
            String[] hAvailable = {"Chicken", "Beef", "Cheese", "Pepper", "Pepperoni", "Sausage", "Onion", "Mushroom", "Olive"};
            String[] hSelected = currentPizza.getArrToppings();
            availableToppings = hAvailable; 
            selectedToppings = hSelected;
    	}
    	if (type.equals("pepperoni")) 
    	{ 
            String[] pAvailable = {"Chicken", "Beef", "Cheese", "Pepper", "Pepperoni", "Sausage", "Onion", "Mushroom", "Olive", "Pineapple", "Ham"};
            String[] pSelected = currentPizza.getArrToppings();
            availableToppings = pAvailable; 
            selectedToppings = pSelected;
    	}
		availableBox.getItems().addAll(availableToppings); 
		selectedBox.getItems().addAll(selectedToppings); 
		availableBox.getSelectionModel().select(0); // automatically selects 1st item to avoid null selection
		selectedBox.getSelectionModel().select(0); // automatically selects 1st item to avoid null selection
	}
	
	/**
	This method sets the phone number for the pizza. (4 lines)
	@param phoneNumber - phone number for the order that the pizza will go to
	*/
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber; 
	}
	
	/**
	This method sets the text bar with the total cost the pizza. (6 lines)
	*/
	private void setTextBar()
	{
    	DecimalFormat df = new DecimalFormat("0.00");
    	String cost = df.format(currentPizza.price()); 
    	priceBar.setText(cost);
	}
	
	/**
	This method changes a string into an enum topping class (24 lines)
	@param topping - topping in string format
	@return enum class topping
	*/
	private Topping getToppingString(String topping)
	{
		if (topping.equalsIgnoreCase("Chicken"))
			return Topping.Chicken;
		else if (topping.equalsIgnoreCase("Beef"))
			return Topping.Beef;
		else if (topping.equalsIgnoreCase("Pepperoni"))
			return Topping.Pepperoni;
		else if (topping.equalsIgnoreCase("Ham"))
			return Topping.Ham;
		else if (topping.equalsIgnoreCase("Sausage"))
			return Topping.Sausage;
		else if (topping.equalsIgnoreCase("Cheese"))
			return Topping.Cheese;
		else if (topping.equalsIgnoreCase("Pepper"))
			return Topping.Pepper;
		else if (topping.equalsIgnoreCase("Onion"))
			return Topping.Onion;
		else if (topping.equalsIgnoreCase("Mushroom"))
			return Topping.Mushroom;
		else if (topping.equalsIgnoreCase("Olive"))
			return Topping.Olive;
		else
			return Topping.Pineapple;
	}
	
	/**
	This displays the warning message for not selecting a pizza size. (7 lines)
	*/
	private void noSizeSelected()
	{
        Alert a = new Alert(AlertType.WARNING,"WARNING",ButtonType.OK);
        a.setHeaderText("Warning");
        a.setContentText("No size chosen!");
        a.showAndWait();
	}

	/**
	This method displays a warning message if user tries to add more than 7 toppings. (7 lines)
	*/
	private void maxToppingReached()
	{
    	Alert a = new Alert(AlertType.WARNING,"WARNING",ButtonType.OK);
    	a.setHeaderText("Warning");
    	a.setContentText("Only 7 toppings maximum!");
    	a.showAndWait();
	}
	
	/**
	This method displays the warning message if there are no more toppings to remove. (7 lines)
	*/
	private void noMoreToppings()
	{
    	Alert a = new Alert(AlertType.WARNING,"WARNING",ButtonType.OK);
    	a.setHeaderText("Warning");
    	a.setContentText("No toppings available to remove!");
    	a.showAndWait();
	}
	
	/**
	The method displays a confirmation that a pizza is added to an order. (7 lines)
	*/
	private void addedToOrder()
	{
        Alert a = new Alert(AlertType.CONFIRMATION,"CONFIRMATION",ButtonType.OK);
        a.setHeaderText("Pizza Added!");
        a.setContentText("Thank you!");
        a.showAndWait();
	}
	
	/**
	This adds a topping to the array of toppings, increasing the size by one. (9 lines)
	@param array array being increased
	@param topping topping to be added to the array
	@return the array with the new topping in it
	*/
	private String[] addOneTopping(String[] array, String topping)
	{
    	String[] newToppings = new String[array.length + 1]; 
    	for (int i = 0; i < array.length; i++)
    	{
    		newToppings[i] = array[i]; 
    	}
    	newToppings[array.length] = topping;
    	return newToppings; 
	}
	
	/**
	This removes a topping to the array of toppings, decreasing the size by one. (13 lines)
	@param array array being decreased
	@param topping topping to be reomved from the array
	@return the array with the new topping missing
	 */
	private String[] removeOneTopping(String[] array, String topping)
	{
    	String[] oldToppings = new String[array.length - 1]; 
    	int j = 0;
    	for (int i = 0; i < array.length; i++)
    	{
    		if (!array[i].equals(topping))
    		{
    			oldToppings[j] = array[i];
    			j++;
    		}
    	}
    	return oldToppings; 
	}
	
	/**
    Removes the selected topping from the available box and puts it in the selected box. (36 lines)
	@param event mouse click
	@throws Exception if the topping cannot be added
	*/
    @FXML
    void add_topping(ActionEvent event) throws Exception // currently set up for deluxe
    {
    	if (size == -1)
    		noSizeSelected();
    	else
    	{
	    	if (currentPizza.getNumberOfToppings() < Pizza.MAX_TOPPINGS)
	    	{
		    	
		    	// clear previous items
		    	selectedBox.getItems().clear();
		    	
		    	// gets the topping name
		    	String topping =  availableBox.getSelectionModel().getSelectedItem();
		    	
		    	// add topping to current pizza
		    	currentPizza.addTopping(getToppingString(topping));
		    	selectedToppings = addOneTopping(selectedToppings, topping); 
		    	availableToppings = removeOneTopping(availableToppings, topping);
		    	
		    	// display the toppings
		    	selectedBox.getItems().addAll(selectedToppings);
		    	
		    	// remove the selected topping in availableBox
		    	int index = availableBox.getSelectionModel().getSelectedIndex();
		    	availableBox.getItems().remove(index);
		    	
		    	selectedBox.getSelectionModel().select(0);
		    	setTextBar();
	    	}
	    	else
	    		maxToppingReached();
    	}
    }
    
    /**
    Removes the selected topping from the selected box and puts it in the available box. (38 lines)
	@param event mouse click
 	*/
    @FXML
    void remove_topping(ActionEvent event)
    {
    	if (size == -1)
    		noSizeSelected();
    	else
    	{
	    	if (currentPizza.getNumberOfToppings() > 0)
	    	{
		    	// clear previous items
			    availableBox.getItems().clear();
			    
			    // gets the topping name
			    String topping =  selectedBox.getSelectionModel().getSelectedItem();
			    
		    	// remove topping to current pizza
		    	currentPizza.removeTopping(getToppingString(topping));
			    
			    availableToppings = addOneTopping(availableToppings, topping); 
		    	
			    selectedToppings = removeOneTopping(selectedToppings, topping);
			    	
			    // display the toppings
			    availableBox.getItems().addAll(availableToppings);
			    	
			    // remove the selected topping in availableBox
			    int index = selectedBox.getSelectionModel().getSelectedIndex();
			    selectedBox.getItems().remove(index);
			    
			    availableBox.getSelectionModel().select(0);
			    
			    setTextBar();
		    }
	    	else
	    		noMoreToppings();
    	}
    }
    
    /**
    Sets text bar and pizza size if the user selects a small pizza. (7 lines)
    @param event mouse click
    */
    @FXML
    void small_selected(ActionEvent event)
    {
    	size = 0;
    	currentPizza.setSize(Size.Small);
    	sizeLabel.setText("S");
    	setTextBar();
    }
    
    /**
    Sets text bar and pizza size if the user selects a medium pizza. (7 lines)
    @param event mouse click
    */
    @FXML
    void medium_selected(ActionEvent event)
    {
    	size = 1; 
    	currentPizza.setSize(Size.Medium);
    	sizeLabel.setText("M");
    	setTextBar();
    }
    
    /**
    Sets text bar and pizza size if the user selects a large pizza. (7 lines)
    @param event mouse click
    */
    @FXML
    void large_selected(ActionEvent event)
    {
    	size = 2; 
    	currentPizza.setSize(Size.Large);
    	sizeLabel.setText("L");
    	setTextBar();
    }
    
    /**
    Event handler to adds the current pizza to the order. (23 lines)
    @param event mouse click
    @throws Exception if pizza is not able to be made
    */
	@FXML
    void add_to_order(ActionEvent event) throws Exception
    {
		// EXCEPTION HANDLING: CHECK THAT priceBar IS NOT 0.00 AKA there is a size selected
    	if (size == -1)
    		noSizeSelected();
    	else
    	{
    		// gets the order from the main method
    		Order order = mainController.getCurrentOrder();
    		
    		// create a Pizza object (depending on type) with these attributes and add them to the phone number's Order
    		order.addPizza(currentPizza);
    		
    		// sets the order from the main method
    		mainController.setCurrentOrder(order);
    		
    		// alert window that says order successfully added
    		addedToOrder();
    		
    		// make a fresh pizza
    		currentPizza = PizzaMaker.createPizza(type); 
    	}
    }
}
