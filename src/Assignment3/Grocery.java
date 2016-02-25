/* Vo, Henry & Spearing, Michael
 * HV3364, MSS3627
 * EE422C - Assignment 3
 * February 2016
 */

package Assignment3;

import java.util.Locale;
import java.text.NumberFormat;

public class Grocery extends Item
{
	protected boolean perishable;
	
	/********************************************************************************************************
	* NAME:				Grocery(String name, double price, int quantity, double weight, boolean perishable)
	* DESCRIPTION:		Grocery Extends Item - Grocery is a type of Item
	* 					Relies on the superclass Item
	* INPUTS:			name
	* 					price
	* 					quantity
	* 					weight
	* 					perishable
	* OUTPUTS			None
	* PROCESS :			[1] amends the perishable of the item
	***********************************************************************************************************/
	/**
	 * 
	 * @param name Name of the grocery
	 * @param price Price of the grocery
	 * @param quantity Quantity of the grocery
	 * @param weight grocery weight
	 * @param perishable Tells if grocery is perishable or not
	 */
	public Grocery(String name, double price, int quantity, double weight, boolean perishable)
	{
		super(name, price, quantity, weight);
		this.perishable = perishable;
	}

	/********************************************************************************
	* NAME:				double calculatePrice()
	* DESCRIPTION:		Calculates the final price after shipping
	* INPUTS:			None		
	* OUTPUTS			double
	* PROCESS :			
	* 					[1] Calculate the shipping
	* 					[2] Add shipping to original price
	* 					[3] Return Final price 
	**********************************************************************************/
	public double calculatePrice () 
	{
		double shipping = quantity * weight * 20.0;
		if (perishable) 
		{
			shipping = shipping * 1.20;
		}
		double final_price = shipping + quantity * price;
		return final_price;
	}

	/********************************************************************************
	* NAME:				String printItemAttributes()
	* DESCRIPTION:		Compiles and returns all the pieces of the item in a formatted
	* 					string that can be printed
	* INPUTS:			None
	* OUTPUTS			String
	* PROCESS :			
	* 					[1] Format the String with all the data
	* 					[2] Return the string
	**********************************************************************************/
	public String printItemAttributes () 
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return String.format("Item %s:\n\tPrice:      %s\n\tQuantity:   %d\n\tWeight:     %d\n\tPerishable: %b\n\tTotal cost: %s\n",
			name,
			nf.format(price),
			quantity,
			(int)weight,
			perishable,
			nf.format(calculatePrice()));
	}
}