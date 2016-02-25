/* Vo, Henry & Spearing, Michael
 * HV3364, MSS3627
 * EE422C - Assignment 3
 * February 2016
 */

package Assignment3;

public class Clothing extends Item 
{
	/********************************************************************************
	* NAME:				Clothing(String name, double price, int quantity, double weight)
	* DESCRIPTION:		Clothing Extends Item - Clothing is a type of Item
	* 					Relies on the superclass Item
	* INPUTS:			name
	* 					price
	* 					quantity
	* 					weight
	* OUTPUTS			None
	* PROCESS :			
	**********************************************************************************/
	public Clothing(String name, double price, int quantity, double weight)
	{
		super(name, price, quantity, weight);
	}
}
