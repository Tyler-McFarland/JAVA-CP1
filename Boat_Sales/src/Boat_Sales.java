/*Calculates boat sales with markup using loops, ifs, and validation
 * Tyler McFarland 12/5/18
 */

import java.util.*;
import java.text.*;

public class Boat_Sales {
	static String iBoatCode;
	static String oBoatName;
	static String oAccessName;
	static String oBoatCost;
	static String oAccessCost;
	static String oPrepCost;
	static String oMrkAmt;
	static String oSub;
	static String oTax;
	static String oTotal;
	static String oGtTotal;
	static String MoreRecs = "yes";
	static Integer iAccessCode;
	static Integer iQty;
	static Integer cSales = 0;
	static double iBoatCost;
	static double iPrepCost;
	static double iMarkup;
	static double cMrkAmt;
	static double iAccessCost;
	static double cSub;
	static double cTax;
	static double cTotal;
	static double cGtTotal = 0;
	static Scanner myScanner; //input device
	static NumberFormat nf;
	public static void main(String[] args) {
		init();
		
	while (MoreRecs != "n") {
		input();
		
		calcs();
		
		output();
		}
		
		totals();

	}
	
	public static void init() {
		//set scanner to console
		myScanner = new Scanner(System.in);
		//change delimiter from blank space to enter
		//to allow spaces in strings
		myScanner.useDelimiter(System.getProperty("line.separator"));
		
		//set formatter to use U.S. currency
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	}
	
	public static void input() {
		//Gets the boat code and sets markup and name also validates with the defaul
		System.out.print("Enter a Boat Type of B(Bass), P(Pontton), S(Ski), or C(Canoe).  Default is B: ");
		iBoatCode = myScanner.next().toUpperCase();		
			switch (iBoatCode) {
				case "B": oBoatName = "Bass";
						  iMarkup = .33;
						  break;
			
				case "P": oBoatName = "Pontoon";
				      	  iMarkup = .25;
				      	  break;
			
				case "S": oBoatName = "Ski";
					  	  iMarkup = .425;
					  	  break;
			
				case "C": oBoatName = "Canoe";
					  	  iMarkup = .20;
					  	  break;
				default:  System.out.println("Invalid Boat Type, defaulted to S");
				  	  	  oBoatName = "Ski";
				  	  	  iMarkup = .425;
				  	  	  break;
			}
		
		//Gets accessory code and sets name and cost validates with the default
		System.out.print("Enter an Accessory type of 1(Electronics), 2(Ski), or 3(Fishing). Default is 1: ");
		iAccessCode = Integer.parseInt(myScanner.next());
			switch (iAccessCode) {
				case 1: oAccessName = "Electronics";
						iAccessCost = 5415.3;
						break;
				case 2: oAccessName = "Ski Package";
						iAccessCost = 3980;
						break;
				case 3: oAccessName = "Fishing Package";
						iAccessCost = 345.45;
						break;			
				default:System.out.println("Invalid Data, defaulted to 1");
						oAccessName = "Electronics";
						iAccessCost = 5415.3;
						break;
			}
			
		//gets quantity and validates range
		try {
			System.out.print("Enter the Quantity of boats, limit 25.  Default is 1: ");
			iQty = Integer.parseInt(myScanner.next());
			if (iQty > 0 && iQty <26) {
				
			}
			else {
				System.out.println("Invalid value, defaulted to 1");
				iQty = 1;
			}
		}
		
		catch (Exception e){
			System.out.println("invalid value, defaulted to 1");
			iQty = 1;
		}
		
		
		//gets boat cost and validates the range with if else statement
		//also sets error messages with exceptions
		try {
		System.out.print("Enter the cost of the boat(s) between 2,500 and 100,000.  Default is 25,000: ");
			iBoatCost = Double.parseDouble(myScanner.next());
			if (iBoatCost > 2499 && iBoatCost < 100001) {
			}
			else {
				System.out.println("Invalid Number, defaulted to $250,000");
					iBoatCost = 250000;
				}
			}  
		
		catch (Exception e){
		System.out.println("Invalid Number, defaulted to $250,000");
			iBoatCost = 250000;
		}
		
		
		//validates the same as the boat cost
		try {
		System.out.print("Enter prep cost between $100.00 and $9,999.99, default is $5,000: ");
			iPrepCost = Double.parseDouble(myScanner.next());
			if (iPrepCost > 99.99 && iPrepCost < 1000.00) {
				}
				else {
					System.out.println("Invalid Number, defaulted to $5,000");
						iPrepCost = 5000;
				}
			}	
		
		catch (Exception e) {
		System.out.println("Invalid Number, Price defaulted to $5,000");
			iPrepCost = 5000;
		}
	}
	
	public static void calcs() {
		//Markup calc with rounded total
		cMrkAmt = iMarkup * iBoatCost;
			cMrkAmt = cMrkAmt * 100;
			cMrkAmt = Math.round(cMrkAmt);
			cMrkAmt = cMrkAmt / 100;
		
		//sub calc
		cSub = (iBoatCost + iAccessCost + iPrepCost + cMrkAmt) * iQty;
		
		//tax calc with rounded total
		cTax = cSub * .06;
			cTax = cTax * 100;
			cTax = Math.round(cTax);
			cTax = cTax / 100;
		
		//total calc
		cTotal = cSub + cTax;
		
		//Total accumulator
		cGtTotal += cTotal;
		
		//sales counter
		cSales ++ ;
	}
	
	public static void output() {
		//outputting formatted data
		System.out.println("Boat Type: " + oBoatName);
		
		System.out.println("Accessory Type: " + oAccessName);
		
		System.out.println("Quantity: " + iQty);
		
		oBoatCost = nf.format(iBoatCost);
		System.out.println("Boat Cost: " + oBoatCost);
		
		oAccessCost = nf.format(iAccessCost);
		System.out.println("Accessory Cost: " + oAccessCost);
		
		oPrepCost = nf.format(iPrepCost);
		System.out.println("Prep Cost: " + oPrepCost);
		
		oMrkAmt = nf.format(cMrkAmt);
		System.out.println("Markup Amount: " + oMrkAmt);
		
		oSub = nf.format(cSub);
		System.out.println("Subtotal: " + oSub);
		
		oTax = nf.format(cTax);
		System.out.println("Tax: " + oTax);
		
		oTotal = nf.format(cTotal);
		System.out.println("Total: " + oTotal);
		
		//asking for another reciept, validating response
		System.out.println("Would you like to enter another order? y to continue or n to terminate");
		MoreRecs = myScanner.next().toLowerCase();
			switch (MoreRecs) {
				case "y":  MoreRecs = "y";
						   break;
				case "n":  MoreRecs = "n";
						   System.out.println("Program Terminated, Have a good day!");
						   break;
				default:   MoreRecs = "n";
						   System.out.println("Invalid response, Program Terminated");
						   break;
			}
	}
	
	public static void totals(){
		System.out.println("Total Sales: " + cSales);
		
		oGtTotal = nf.format(cGtTotal);
		System.out.println("Grand Total: " + oGtTotal);
	}
		
}


