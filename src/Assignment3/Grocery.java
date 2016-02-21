package Assignment3;

import java.util.Locale;
import java.text.NumberFormat;

public class Grocery extends Item
{
	protected boolean perishable;
	
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

	/*
	 * (non-Javadoc)
	 * @see Assignment3.Item#calculatePrice()
	 */
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

	/*
	 * (non-Javadoc)
	 * @see Assignment3.Item#printItemAttributes()
	 */
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