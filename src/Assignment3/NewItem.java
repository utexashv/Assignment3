/* Vo, Henry & Spearing, Michael
 * HV3364, MSS3627
 * EE422C - Assignment 3
 * February 2016
 */

package Assignment3;

public class NewItem
{
	
	/********************************************************************************
	* NAME:				Item createItem(String[] command)
	* DESCRIPTION:		Creates a new Item and applies necesary restrictions
	* INPUTS:			String[] command		
	* OUTPUTS			Item
	* THROWS:			Exception Wrong Number of Arguments
	* 					Exception Fragility Unknown
	* 					Exception Perishability Unknown
	* 					Exception Not a valid category
	* PROCESS :			
	* 					[1] Break the String[] down into the necessary information
	* 					[2] Make sure there are enough arguments for the desired item type
	* 					[3] Create an Item of the proper type
	* 					[4] Assign given values to the item
	* 					[5] Return the Item
	**********************************************************************************/
	public static Item createItem(String[] command) throws Exception
	{
		if (command.length < 6) 
		{
			throw new Exception("Wrong number of arguments. You need at least 6.");
		}
		String name = command[2].toLowerCase();
		double price = Double.parseDouble(command[3]);
		int quantity = Integer.parseInt(command[4]);
		double weight = Double.parseDouble(command[5]);
		/* 
		 * Checks for errors within clothing
		 */
		if (command[1].equals("clothing")) 
		
		{
			if (command.length != 6) 
			{
				throw new Exception("Wrong number of arguments for clothing.");
			}
			return new Clothing(name, price, quantity, weight);
		} 
		/* 
		 * Checks for errors within electronics
		 */
		else if (command[1].equals("electronics")) 
		{
			if (command.length != 8)
			{
				throw new Exception("Wrong number of arguments for electronics.");
			}
			String state = command[7];
			boolean fragile;
			if (command[6].equals("f")) 
			{
				fragile = true;
			}
			else if (command[6].equals("nf")) 
			{
				fragile = false;
			} 
			else 
			{
				throw new Exception("Fragility Unkown");
			}
			return new Electronics(name, price, quantity, weight, fragile, state);
		} 
		/* 
		 * Checks for errors within groceries
		 */
		else if (command[1].equals("groceries")) 
		{
			if (command.length != 7) 
			{
				throw new Exception("Wrong number of arguments for groceries.");
			}
			boolean perishable;
			if (command[6].equals("p")) 
			{
				perishable = true;
			} 
			else if (command[6].equals("np")) 
			{
				perishable = false;
			} 
			else 
			{
				throw new Exception("Perishability unknown");
			}
			return new Grocery(name, price, quantity, weight, perishable);
		} 
		/* 
		 * Checks for errors if it not a valid product catergory
		 */
		else 
		{
			throw new Exception("Not a valid category");
		}
	}

}