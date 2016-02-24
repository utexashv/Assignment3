/* Vo, Henry & Spearing, Michael
 * HV3364, MSS3627
 * EE422C - Assignment 3
 * February 2016
 */

package Assignment3;

import java.io.*;
import java.util.*;

public class A3Driver 
{
	// Shopping cart housing all of the items that have been added
	protected static SortedMap<String, Item> ShoppingCart;

	/*******************************************************************
	* NAME :            void main(String[] args)
	*
	* DESCRIPTION :     Main function
	*
	* INPUTS :
	*       			File Name from command line
	* OUTPUTS :
	*    				NONE
	* PROCESS :
	*                   [1]  Creates a shopping cart
	*                  	[2]  Handles the input
	*                   [3]  Calls ProcessCommand
	*******************************************************************/
	public static void main(String[] args) 
	{
		if (args.length > 1) 
		{
			return;
		}
		// Reads the input file
		Scanner in;
		if (args.length == 1) 
		{
			String filename = args[0];
			File inputFile = new File(filename);
			try 
			{
				
				in = new Scanner(inputFile);
			} catch (Exception e) 
			{
				System.err.println("Error: " + e.getMessage());
				return;
			}
		} 
		else 
		{
			in = new Scanner(System.in);
		}

		ShoppingCart = new TreeMap<String, Item>();
		while (in.hasNextLine())
		{
			String line = in.nextLine();
			try 
			{
				String output = processCommand(line);
				System.out.println(output.trim());
				
			} 
			catch (Exception e) 
			{
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
	/*******************************************************************
	* NAME:				String processCommand(String line)
	* DESCRIPTION:		Processes the given string and redirects to the
	* 					proper routine
	* PARAMATERS:		String line - line to read
	* RETURN:			String - Which function to do
	* THROWS:			Exception - Not a valid function
	* PROCESS:
	*                   [1]  Splits up the string into a workable array
	*                  	[2]  Returns parse<Function>
	*******************************************************************/
	public static String processCommand(String line) throws Exception
	{
		String[] command = line.toLowerCase().split(" ");  // splits each line at the spaces
		if (command.length > 8) 
		{
			throw new Exception("Wrong number of arguments. Does not require over 8 arguments.");
		} 
		else if (command[0].equals("insert")) 
		{
			return parseInsert(command);
		} 
		else if (command[0].equals("delete")) 
		{
			return parseDelete(command);
		} 
		else if (command[0].equals("search")) 
		{
			return parseSearch(command);
		} 
		else if (command[0].equals("update")) 
		{
			return parseUpdate(command);
		} 
		else if (command[0].equals("print"))
		{
			return parsePrint(command);
		} 
		else 
		{
			throw new Exception("Not a valid command");
		}
	}

	
	/*******************************************************************
	* NAME:				String parseInsert(String command)
	* DESCRIPTION:		Adds a new item to the cart
	* PARAMATERS:		String command - directive of what to do
	* RETURN:			String - statement of what happened
	* PROCESS:
	*                   [1]  Create a new Item
	*                  	[2]  Add the Item to the Cart
	*                   [3]  Return confirmation message
	*******************************************************************/
	public static String parseInsert(String[] command) throws Exception
	{
		Item item = NewItem.createItem(command);
		ShoppingCart.put(item.name, item);
		return String.format("Inserted %s into ShoppingCart", item.name);
	}
	
	/*******************************************************************
	* NAME:				String parseDelete(String command)
	* DESCRIPTION:		Deletes and Item from the Cart
	* PARAMATERS:		String command - directive of what to do
	* RETURN:			String - statement of what happened
	* THROWS:			Exception Wrong Number of Arguments
	* PROCESS:
	*                   [1]  Search for Item in the Cart
	*                   [2]  Return Error if not found
	*                  	[3]  Delete the Item
	*                   [4]  Return confirmation message
	*******************************************************************/
	public static String parseDelete(String[] command) throws Exception
	{
		if (command.length != 2) 
		{
			throw new Exception("Wrong number of arguments. Needs to have 2.");
		}
		String name = command[1];
		if (ShoppingCart.containsKey(name)) 
		{
			ShoppingCart.remove(name);
			return String.format("Removed %s from ShoppingCart", name);
		} 
		else 
		{
			return "Cannot delete item because it is not in the shopping cart.";
		}
	}
	
	/*******************************************************************
	* NAME:				String parseSearch(String command)
	* DESCRIPTION:		Looks for an item in the Cart
	* PARAMATERS:		String command - directive of what to do
	* RETURN:			String - statement of what happened
	* THROWS:			Exception Wrong Number of Arguments
	* PROCESS:
	*                   [1]  Search for Item in Cart
	*                   [2]  Return confirmation message of Found or not
	*******************************************************************/
	public static String parseSearch(String[] command) throws Exception
	{
		if (command.length != 2)
		{
			throw new Exception("Wrong number of arguments. Needs to have 2.");
		}
		String name = command[1];
		if (ShoppingCart.containsKey(name))
		{
			return ShoppingCart.get(name).printItemAttributes();
		} 
		else
		{
			return "That item is not in the shopping cart";
		}
	}
	
	/*******************************************************************
	* NAME:				String parseUpdate(String command)
	* DESCRIPTION:		Updates the quantity of an item in the cart
	* PARAMATERS:		String command - directive of what to do
	* RETURN:			String - statement of what happened
	* THROWS:			Exception Item not in cart
	* PROCESS:
	*                   [1]  Search for Item in Cart
	*                   [2]  Updates quantity of Item
	*                   [3]  Returns Confirmation/Failure Message 
	*******************************************************************/

	public static String parseUpdate(String[] command) throws Exception
	{
		if (command.length != 3) 
		{
			throw new Exception("Wrong number of arguments. Needs to have 3.");
		}
		String name = command[1];
		int quantity = Integer.parseInt(command[2]);
		if (ShoppingCart.containsKey(name)) 
		{
			ShoppingCart.get(name).update(quantity);
			return String.format("Updated quantity of %s to %d", name, quantity);
		} 
		else 
		{
			return "Cannot update because item is not in the shopping cart.";
		}
	}

	/*******************************************************************
	* NAME:				String parsePrint(String command)
	* DESCRIPTION:		Updates the quantity of an item in the cart
	* PARAMATERS:		String command - directive of what to do
	* RETURN:			String - statement of what happened
	* THROWS:			Exception Wrong Number of Arguments
	* PROCESS:
	*                   [1]  Build String of cart
	*                   [2]  Return String
	*******************************************************************/
	public static String parsePrint(String[] command) throws Exception
	{
		if (command.length != 1) 
		{
			throw new Exception("Wrong number of arguments. Needs to have 1.");
		}
		StringBuilder output = new StringBuilder();
		for (Item item : ShoppingCart.values()) 
		{
			output.append(item.printItemAttributes());
		}
		return output.toString();
	}

}