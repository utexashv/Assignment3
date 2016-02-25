/* Vo, Henry & Spearing, Michael
 * HV3364, MSS3627
 * EE422C - Assignment 3
 * February 2016
 */

package Assignment3;

import java.util.Locale;
import java.text.NumberFormat;

public class Item 
{
	protected String name;
	protected double price;
	protected double weight;
	protected int quantity;

	/********************************************************************************
	* NAME :           Item(String name, double price, int quantity, double weight)
	*
	* DESCRIPTION :     ADT that houses all of the information about an item
	*
	* INPUTS :
	*       			name - Name of the product
	*       			price - Price of the item
	*       			quantity - quantity of the item
	*       			weight - weight of the item
	* OUTPUTS :
	*    				Item
	* PROCESS :			
	* 					[1] Creates and Item
	**********************************************************************************/

	public Item(String name, double price, int quantity, double weight)
	{
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	/********************************************************************************
	* NAME:				update(int quantity)
	* DESCRIPTION:		Updates the Quantity of the Item
	* INPUTS:			Quantity		
	* OUTPUTS			None
	* PROCESS :			
	* 					[1] Update the Quantity
	**********************************************************************************/
	public void update(int quantity)
	{
		this.quantity = quantity;
	}

	/********************************************************************************
	* NAME:				double calculatePrice()
	* DESCRIPTION:		Calculates the final price after tax and shipping
	* INPUTS:			None		
	* OUTPUTS			double
	* PROCESS :			
	* 					[1] Calculate the shipping
	* 					[2] Calculate the tax
	* 					[3] Add tax and shipping to original price
	* 					[4] Return Final price 
	**********************************************************************************/
	public double calculatePrice()
	{
		double shipping = quantity * weight * 20.0;
		double tax = 0.1;
		double final_price = (shipping + quantity * price) * (tax + 1);
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
	public String printItemAttributes() 
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return String.format("Item %s:\n\tPrice:      %s\n\tQuantity:   %d\n\tWeight:     %d\n\tTotal cost: %s\n",
			name,
			nf.format(price),
			quantity,
			(int)weight,
			nf.format(calculatePrice()));
	}
}