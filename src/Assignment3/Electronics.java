/* Vo, Henry & Spearing, Michael
 * HV3364, MSS3627
 * EE422C - Assignment 3
 * February 2016
 */

package Assignment3;

import java.util.Set;
import java.util.Arrays;
import java.util.Locale;
import java.util.HashSet;
import java.text.NumberFormat;

public class Electronics extends Item 
{
	protected static final String[] STATE_FREE_TAX = new String[] {"tx", "nm", "va", "az", "ak"};
	protected static final String[] ALL_STATES = new String[] {"al", "ak", "as", "az", "ar", "ca", "co", "ct", "de", "dc", "fm", "fl", "ga", "gu", "hi", "id", "il", "in", "ia", "ks", "ky", "la", "me", "mh", "md", "ma", "mi", "mn", "ms", "mo", "mt", "ne", "nv", "nh", "nj", "nm", "ny", "nc", "nd", "mp", "oh", "ok", "or", "pw", "pa", "pr", "ri", "sc", "sd", "tn", "tx", "ut", "vt", "vi", "va", "wa", "wv", "wi", "wy"};
	protected static final Set<String> STATE_SET_ALL = new HashSet<String>(Arrays.asList(ALL_STATES));
	protected static final Set<String> STATE_SET_FREE = new HashSet<String>(Arrays.asList(STATE_FREE_TAX));
	
	protected String state;
	protected boolean fragile;
	
	/***********************************************************************************************************************
	* NAME:				Electronics(String name, double price, int quantity, double weight, boolean fragile, String state)
	* DESCRIPTION:		Electronics Extends Item - Electronics is a type of Item
	* 					Relies on the superclass Item
	* INPUTS:			name
	* 					price
	* 					quantity
	* 					weight
	* 					fragile
	* 					state
	* THROWS:			Exception This is not a State
	* OUTPUTS			None
	* PROCESS :			[1] amends the fragility of the item
	* 					[2] Processes State
	************************************************************************************************************************/
	public Electronics(String name, double price, int quantity, double weight, boolean fragile, String state) throws Exception
	{
		super(name, price, quantity, weight);
		this.fragile = fragile;
		if (!STATE_SET_ALL.contains(state)) 
		{
			throw new Exception("This is not a state.");
		}
		this.state = state;
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
	public double calculatePrice () 
	{
		double shipping = 20.0 * quantity * weight;
		if (fragile) 
		{
			shipping = shipping * 1.20;
		}
		double tax = getStateTax();
		double final_price = (shipping + quantity * price) * (tax + 1);
		return final_price;
	}

	/*
	 * Gets the state tax if there is one
	 */
	protected double getStateTax()
	{
		return (STATE_SET_FREE.contains(state)) ? 0.0 : 0.10;
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
		return String.format("Item %s:\n\tPrice:      %s\n\tQuantity:   %d\n\tWeight:     %d\n\tFragile:    %b\n\tState:      %s\n\tTotal cost: %s\n",
			name,
			nf.format(price),
			quantity,
			(int)weight,
			fragile,
			state,
			nf.format(calculatePrice()));
	}
}