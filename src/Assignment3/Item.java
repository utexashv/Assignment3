package Assignment3;

import java.util.Locale;
import java.text.NumberFormat;

public class Item 
{
	protected String name;
	protected double price;
	protected double weight;
	protected int quantity;

	/**
	 * 
	 * @param name name of the product
	 * @param price price of the item
	 * @param quantity quantity of the item
	 * @param weight item weight
	 */
	public Item(String name, double price, int quantity, double weight)
	{
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	/*
	 * Updates the quantity
	 */
	public void update(int quantity)
	{
		this.quantity = quantity;
	}

	/*
	 * Calculates the price of the item
	 */
	public double calculatePrice()
	{
		double shipping = quantity * weight * 20.0;
		double tax = 0.1;
		double final_price = (shipping + quantity * price) * (tax + 1);
		return final_price;
	}
	
	/*
	 * prints out the item information
	 */
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