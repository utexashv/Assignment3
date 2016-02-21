package Assignment3;

import java.io.*;
import java.util.*;

public class ShoppingCartDriver 
{
	protected static SortedMap<String, Item> ShoppingCart;

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

	/**
	 * 
	 * @param line line to read
	 * @return returns which function to do
	 * @throws Exception Not a valid function
	 */
	public static String processCommand(String line) throws Exception
	{
		String[] command = line.toLowerCase().split(" ");
		if (command.length > 8) 
		{
			throw new Exception("Wrong number of arguments");
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

	/**
	 * 
	 * @param command command of the file
	 * @return inserts an item into the cart and prints out statement
	 * @throws Exception
	 */
	public static String parseInsert(String[] command) throws Exception
	{
		Item item = NewItem.createItem(command);
		ShoppingCart.put(item.name, item);
		return String.format("Inserted %s into ShoppingCart", item.name);
	}

	public static String parseDelete(String[] command) throws Exception
	{
		if (command.length != 2) 
		{
			throw new Exception("Wrong number of arguments");
		}
		String name = command[1];
		if (ShoppingCart.containsKey(name)) 
		{
			ShoppingCart.remove(name);
			return String.format("Removed %s from ShoppingCart", name);
		} 
		else 
		{
			return "Item not found in ShoppingCart";
		}
	}

	public static String parseSearch(String[] command) throws Exception
	{
		if (command.length != 2) {
			throw new Exception("Wrong number of arguments");
		}
		String name = command[1];
		if (ShoppingCart.containsKey(name)) {
			return ShoppingCart.get(name).printItemAttributes();
		} else {
			return "Item not found in ShoppingCart";
		}
	}

	public static String parseUpdate(String[] command) throws Exception
	{
		if (command.length != 3) {
			throw new Exception("Wrong number of arguments");
		}
		String name = command[1];
		int quantity = Integer.parseInt(command[2]);
		if (ShoppingCart.containsKey(name)) {
			ShoppingCart.get(name).update(quantity);
			return String.format("Updated quantity of %s to %d", name, quantity);
		} else {
			return "Item not found in ShoppingCart";
		}
	}

	public static String parsePrint(String[] command) throws Exception
	{
		if (command.length != 1) {
			throw new Exception("Wrong number of arguments");
		}
		StringBuilder output = new StringBuilder();
		for (Item item : ShoppingCart.values()) {
			output.append(item.printItemAttributes());
		}
		return output.toString();
	}

}