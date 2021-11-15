package application;

import static org.junit.Assert.*;
import org.junit.Test;

/**
This method tests the price method in the Deluxe class. It calls it and checks the total price
to see if it matches up with the expected cost. 
@author David Halim, Stephen Juan.
*/
public class DeluxeTest
{
	/*
	tests the price of a small Deluxe pizza with no additional toppings.
	expects an output of 12.99.
	*/
	@Test
	public void test1()
	{
		Deluxe testPizza = new Deluxe();
		double testPrice = testPizza.price();
		double expectedOut = 12.99;
		assertTrue(testPrice == expectedOut);
	}
	
	/*
	tests the price of a Medium Deluxe pizza with no additional toppings.
	expects an output of 12.99 + 2 = 14.99.
	*/
	@Test
	public void test2()
	{
		Deluxe testPizza = new Deluxe(Size.Medium);
		double testPrice = testPizza.price();
		double expectedOut = 14.99;
		assertTrue(testPrice == expectedOut);
	}
	
	/*
	tests the price of a Large Deluxe pizza with no additional toppings.
	expects an output of 12.99 + 4 = 16.99.
	*/
	@Test
	public void test3()
	{
		Deluxe testPizza = new Deluxe(Size.Large);
		double testPrice = testPizza.price();
		double expectedOut = 16.99;
		assertTrue(testPrice == expectedOut);
	}
	
	/*
	tests the price of a small Deluxe pizza with 1 additional toppings.
	expects an output of 12.99 + 1.49 = 14.48.
	@throws Exception if the pizza reached MAX_TOPPINGS
	*/
	@Test
	public void test4() throws Exception
	{
		Deluxe testPizza = new Deluxe();
		testPizza.addTopping(Topping.Beef);
		double testPrice = testPizza.price();
		double expectedOut = 14.48;
		assertTrue(testPrice == expectedOut);
	}
	
	/*
	tests the price of a Medium Deluxe pizza with 2 additional toppings.
	expects an output of 12.99 + 2 + 1.49 + 1.49 = 17.97.
	@throws Exception if the pizza reached MAX_TOPPINGS
	*/
	@Test
	public void test5() throws Exception
	{
		Deluxe testPizza = new Deluxe(Size.Medium);
		testPizza.addTopping(Topping.Beef);
		testPizza.addTopping(Topping.Chicken);
		double testPrice = testPizza.price();
		double expectedOut = 17.97;
		assertTrue(testPrice == expectedOut);
	}
	
	/*
	tests the price of a Large Deluxe pizza with less than the standard 5 toppings.
	expects an output of 12.99 + 4 = 16.99 because the price does not change when
	the number of toppings is less than the original Deluxe toppings.
	*/
	@Test
	public void test6()
	{
		Deluxe testPizza = new Deluxe(Size.Large);
		testPizza.removeTopping(Topping.Olive);
		testPizza.removeTopping(Topping.Pepperoni);
		double testPrice = testPizza.price();
		double expectedOut = 16.99;
		assertTrue(testPrice == expectedOut);
	}

}