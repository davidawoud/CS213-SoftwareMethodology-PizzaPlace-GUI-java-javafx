package application;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
/**
This is the controller class for the Current Order window. 
@author Stephen Juan, David Halim
*/
public class CurrentOrderSceneController 
{
    @FXML
    private TextArea phoneNumberBox;
    @FXML
    private ListView<String> orderList;
    @FXML
    private TextArea subtotalBar, taxBar, totalBar;
    @FXML
    private Button removeButton, orderButton;
    
    private String phoneNumber;
    
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
    This methods sets the phone number of the current order. (5 lines)
    @param phoneNumber - customer's phone number
    */
    public void setPhoneNumber(String phoneNumber)
    {
    	this.phoneNumber = phoneNumber; 
    	phoneNumberBox.setText(phoneNumber);
    }
    
    
    /**
    This method displays a list of the current pizzas in the order in the ListView. (12 lines)
    */
    public void displayPizzaOrders()
    {
    	// if the phone number in order doesnt exist, alert and close window
    	
    	orderList.getSelectionModel().select(0); // automatically selects the first item to avoid exceptions
    	
    	// calls the method that returns an array of pizzas in orderList
		Order order = mainController.getCurrentOrder();
    	
    	orderList.getItems().addAll(order.getArrOfPizza());
    	
    	orderList.getSelectionModel().select(0);
    }
    
    /**
    This method sets the subtotal, tax and the grand total of the order. (19 lines)
    */
    public void setCosts()
    {
    	float subtotalCost = 0; 
    	float taxRate = (float) 1.06625; 
    	
    	Order order = mainController.getCurrentOrder();
    	
    	ArrayList<Pizza> pizzasInOrder = order.getPizzaOrder();
    	
    	for (int i = 0; i < pizzasInOrder.size(); i++)
    	{
    		subtotalCost += pizzasInOrder.get(i).price(); 
    	}
    	
	    DecimalFormat df = new DecimalFormat("0.00");
	    
	    subtotalBar.setText(df.format(subtotalCost)); 
	    taxBar.setText(df.format(subtotalCost * (taxRate - 1)));
	    totalBar.setText(df.format(subtotalCost * taxRate));
    }
    
    /**
    This method gets the type of a pizza in a toString format. (12 lines)
    @param selectedPizza - string format of a pizza
    @return type of pizza
    */
    private String getType(String selectedPizza)
    {
    	String type = ""; 
    	for (int i = 0; i < selectedPizza.length(); i++)
    	{
    		if (selectedPizza.charAt(i) == ':')
    			break;
    		type += selectedPizza.charAt(i); 
    	}
    	String arr[] = type.split(" ", 2);
    	type = arr[0];
    	return type;
    }
    
    /**
    This method gets the size of a pizza in a toString format. (25 lines)
    @param selectedPizza - string format of a pizza
    @return size of pizza
    */
    private Size getSize(String selectedPizza)
    {
    	String size = ""; 
    	Size pizzaSize;
    	int semicolonCount = 0; 
    	for (int i = 0; i < selectedPizza.length(); i++)
    	{
    		if (selectedPizza.charAt(i) == ':')
    		{
    			semicolonCount++;
    			continue;
    		}
    		if (selectedPizza.charAt(i) == ':' && semicolonCount == 2)
    			break; 
    		if (semicolonCount == 1)
    			size += selectedPizza.charAt(i); 
    	}
    	if (size.equals("small"))
    		pizzaSize = Size.Small;
    	else if (size.equals("medium"))
    		pizzaSize = Size.Medium;
    	else
    		pizzaSize = Size.Large;
    	
    	return pizzaSize;
    }
    
    /**
    This method adds a topping in string format to an arraylist of Topping. (24 lines)
    @param currentTopping - topping to be added
    @param toppings - arraylist of Topping
    */
    private void addToppings(String currentTopping, ArrayList<Topping> toppings)
    {
		if (currentTopping.equals("chicken"))
			toppings.add(Topping.Chicken);
		else if (currentTopping.equals("beef"))
			toppings.add(Topping.Beef);
		else if (currentTopping.equals("pepperoni"))
			toppings.add(Topping.Pepperoni);
		else if (currentTopping.equals("ham"))
			toppings.add(Topping.Ham);
		else if (currentTopping.equals("sausage"))
			toppings.add(Topping.Sausage);
		else if (currentTopping.equals("cheese"))
			toppings.add(Topping.Cheese);
		else if (currentTopping.equals("pepper"))
			toppings.add(Topping.Pepper);
		else if (currentTopping.equals("onion"))
			toppings.add(Topping.Onion);
		else if (currentTopping.equals("mushroom"))
			toppings.add(Topping.Mushroom);
		else if (currentTopping.equals("olive"))
			toppings.add(Topping.Olive);
		else
			toppings.add(Topping.Pineapple);
    }
    
    /**
    This method gets the toppings of a pizza in a toString format. (24 lines)
    @param selectedPizza - string format of a pizza
    @return toppings of a pizza
    */
    private ArrayList<Topping> getToppings(String selectedPizza)
    {
    	ArrayList<Topping> toppings = new ArrayList<>(); 
    	String currentTopping = ""; 
    	int semicolonCount = 0; 
    	for (int i = 0; i < selectedPizza.length(); i++)
    	{
    		if (selectedPizza.charAt(i) == ':')
    		{
    			semicolonCount++;
    			continue; 
    		}
    		if (semicolonCount == 2)
    		{
    			if (selectedPizza.charAt(i) == ' ')
    			{
    				addToppings(currentTopping, toppings);
    				currentTopping = "";
    				continue; 
    			}
    			currentTopping += selectedPizza.charAt(i);
    		}
    	}
    	return toppings;
    }
    
    /**
    This method shows a confirmation that an order has been placed. (7 lines)
    */
    private void placedOrder()
    {
        Alert a = new Alert(AlertType.CONFIRMATION,"CONFIRMATION",ButtonType.OK);
        a.setHeaderText("Order Placed!");
        a.setContentText("Thank you!");
        a.showAndWait();
    }
    
    /**
    This method shows a warning that there is nothing in the order. (7 lines)
    */
    private void nothingInOrder()
    {
        Alert a = new Alert(AlertType.WARNING,"WARNING",ButtonType.OK);
        a.setHeaderText("Nothing in the order!");
        a.setContentText("Please order some pizzas!");
        a.showAndWait();
    }
    
	/**
	This button places the order all the pizzas, identified with the phone number. (32 lines)
	@param event mouse click
	*/
    @FXML
    void place_order(ActionEvent event) 
    {	
    	Order order = mainController.getCurrentOrder();
    	if (order.getPizzaOrder().isEmpty() || order.equals(null) || totalBar.getText().equals(""))
    		nothingInOrder();
    	else
    	{
	    	StoreOrders storeOrder = mainController.getStoreOrder();
	    	
	    	// set total cost for the order
	    	order.setOrderTotal(Float.parseFloat(totalBar.getText()));
	    	
	    	// add Order to the StoreOrder
	    	storeOrder.addOrder(order);
	    	
	    	// set new store order in the main controller
	    	mainController.setStoreOrder(storeOrder);
	    	
	    	// clears the current order in main controller
	    	mainController.setIsOngoingOrder();
	    	
	    	// clears all the text fields
	    	phoneNumberBox.clear();
	    	subtotalBar.clear();
	    	taxBar.clear(); 
	    	totalBar.clear();
	    	orderList.getItems().clear();
	    	
	    	// alert to say that order has been placed
	    	placedOrder();
    	}
    }
    
    /**
    Removes the selected pizza in the current order. (37 lines)
    @param event mouse click
    @throws Exception if the pizza is not able to be created
    */
    @FXML
    void remove_pizza(ActionEvent event) throws Exception 
    {
    	Order order = mainController.getCurrentOrder();
    	
    	if (order.getPizzaOrder().isEmpty() || order.equals(null) || totalBar.getText().equals(""))
    		nothingInOrder();
    	else
    	{
	    	// string analyzing
	    	String selectedPizza = orderList.getSelectionModel().getSelectedItem(); // the current selected pizza
	    	
	    	// get the type of the pizza
	    	String type = getType(selectedPizza);
	    	
	    	// get the size of pizza
	    	Size pizzaSize = getSize(selectedPizza); 
	    	
	    	// gets the toppings of pizza and put them into an arraylist
	    	ArrayList<Topping> toppings = getToppings(selectedPizza); 
	    	
	    	Pizza pizza = PizzaMaker.createPizza(type);
	    	pizza.setSize(pizzaSize);
	    	pizza.setToppings(toppings); 
	    	
	    	// remove the Pizza object from Order
	    	order.removePizza(pizza);
	    	
	    	// set orders in main controller
	    	mainController.setCurrentOrder(order);
	    	
	    	// remove selected item from visual
	    	orderList.getItems().remove(orderList.getSelectionModel().getSelectedIndex());
	    	
	    	// update setCosts
	    	setCosts(); 
    	}
    }
}
