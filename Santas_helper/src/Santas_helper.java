/*
 * This program calculates the total bill of 2 toys
 * Tyler McFarland 11/29/18
 */

import java.text.*;
import java.util.*;

public class Santas_helper {

	static String iFName;
	static String iLName;
	static String iToyName1;
	static String iToyName2;
	static String oSub;
	static String oTax;
	static String oTotal;
	static String oToyPrice1;
	static String oToyPrice2;
	static double cToyPrice1;
	static double cToyPrice2;
	static double cSub;
	static double cTax;
	static double cTotal;
	static Scanner myScanner;
	static NumberFormat nf;
	
	public static void main(String[] args) {
		
		init();
		
		input();
		
		calcs();
		
		output();

	}
	
	public static void init() {
		//set scanner to console, make default enter, format currency
		myScanner = new Scanner(System.in);
		
		myScanner.useDelimiter(System.getProperty("line.separator"));
		
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	}
	
	public static void input() {
		//get all inputs and parse prices to double
		System.out.print("Enter first name: ");
		iFName = myScanner.next();
		System.out.println("");
				
		System.out.print("Enter last name: ");
		iLName = myScanner.next();
		System.out.println("");

		System.out.print("Enter the name of the first toy: ");
		iToyName1 = myScanner.next();
		System.out.println("");
		
		try{
		System.out.print("Enter the price of the first toy: ");
		cToyPrice1 = Double.parseDouble(myScanner.next());
		System.out.println("");
		}
		
		catch (Exception e) {
		System.out.println("Enter a price in x.xx format, toy price has been set to 0");
		System.out.println("");
		cToyPrice1 = 0;
		}
		
		System.out.print("Enter the name of the second toy: ");
		iToyName2 = myScanner.next();
		System.out.println("");
		
		try {
		System.out.print("Enter the price of the second toy: ");
		cToyPrice2 = Double.parseDouble(myScanner.next());
		System.out.println("");
		}
		catch (Exception e){
		System.out.println("Enter a price in x.xx format, toy price has been set to 0");
		System.out.println("");
		cToyPrice2 = 0;
		}
	}

	public static void calcs() {
		cSub = cToyPrice1 + cToyPrice2;
		cTax = (cToyPrice1 * .07) + (cToyPrice2 * .07);
		cTotal = cSub + cTax;
	}
	
	public static void output() {
		//Format prices to currency
		oToyPrice1 = nf.format(cToyPrice1);
		oToyPrice2 = nf.format(cToyPrice2);
		oSub = nf.format(cSub);
		oTax = nf.format(cTax);
		oTotal = nf.format(cTotal);
		
		//print name
		System.out.println("Name: " + iFName + " " + iLName);
		System.out.println("");
		
		//print name and price of first toy
		System.out.println("First Toy: " + iToyName1 + "      " + "Price: " + oToyPrice1);

		//Print name and price of second toy
		System.out.println("Second Toy: " + iToyName2 + "      " + "Price: " + oToyPrice2);
		System.out.println("");
		
		//print totals
		System.out.println("Subtotal: " + oSub);
		System.out.println("Total Tax: " + oTax);
		System.out.println("Order Total: " + oTotal);
		System.out.println("");

		//final message
		System.out.println("Have a merry christmas, and keep those toys cheap!");
	}
}
