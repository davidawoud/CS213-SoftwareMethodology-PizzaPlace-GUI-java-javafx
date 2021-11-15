package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
This method stores an list of all orders in a store. It has an option for saving it to a text file. 
@author Stephen Juan, David Halim
*/
public class StoreOrders 
{
	private ArrayList<Order> orderList = new ArrayList<Order>();
	
	/**
	This methods adds an order to the list of orders. (4 lines)
	@param order - order to be added to the list of orders
	*/
	public void addOrder(Order order)
	{
		orderList.add(order); 
	}
	
	/**
	This method removes an order identified by the phone number. (9 lines)
	@param phoneNumber - phone number of the order to be removed. 
	*/
	public void removeOrder(String phoneNumber)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if (phoneNumber.equals(orderList.get(i).getPhoneNumber()))
			{
				orderList.remove(i); 
			}
		}
	}
	
	/**
	This method returns the order in the order list. (10 lines)
	@param phoneNumber - phone number of the order
	@return order in the order list, null if it doesnt exist
	*/
	public Order findOrder(String phoneNumber)
	{
		for (int i = 0; i < orderList.size(); i++)
		{
			if (orderList.get(i).getPhoneNumber().equals(phoneNumber))
			{
				return orderList.get(i);
			}
		}
		return null; 
	}
	
	/**
	This method returns an array of strings of phone numbers of all the orders in the list. (11 lines)
	@return an array of of strings phone numbers of all the orders in the list
	*/
	public String[] getArrOfPhone()
	{
		String[] phoneNumbers = new String[orderList.size()];
		
		for (int i = 0; i < phoneNumbers.length; i++)
		{
			String number = (orderList.get(i)).getPhoneNumber();
			phoneNumbers[i] = number;
		}
		
		return phoneNumbers; 
	}
	
	/**
	This method exports the store order to a text file named Store Order.txt. (??? lines)
	@throws IOException - if the file is not able to be opened
	*/
	public void export() throws IOException // currently does not work
	{
		PrintWriter outFile = new PrintWriter("Store_Orders.txt");
		
		for (int i = 0; i < orderList.size(); i++)
		{
			outFile.println("\n\n-------New Order-------");
			outFile.println("Phone Number: " + orderList.get(i).getPhoneNumber());
			String[] pizzas = orderList.get(i).getArrOfPizza();
			for (int j = 0; j < pizzas.length; j++)
			{
				outFile.print(pizzas[j] + "\r\n");
			}
		}
		
		outFile.close();
	}
}
