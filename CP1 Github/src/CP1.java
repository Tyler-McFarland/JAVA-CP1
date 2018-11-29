/*
 * This program calculates a paycheck amount
 * Tyler McFarland 11/27/18
 */
import java.text.*;
import java.util.*;

public class CP1 {
	
	//declare variables
	static String iString; //generic input string
	static String iFirstName, iLastName; //first and last name
	static int cHours; //hours after conversion
	static double cRate; //rate after conversion
	static double cPay; //calculated pay
	static String oPay; //formatted pay
	static Scanner myScanner; //input device
	static NumberFormat nf; //used to format currency

	public static void main(String[] args) {
		
		//call init()
		init();
		
		//call input()
		input();
		
		//call calcs()
		calcs();
		
		//call output
		output();
		
		System.out.println("Program ending, have a good one!");
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
		//prompt for first name
		System.out.print("Enter first name: ");
		iFirstName = myScanner.next();
		
		//prompt for last name
		System.out.print("Enter last name: ");
		iLastName = myScanner.next();
		
		try {
		//prompt and convert hours
		System.out.print("Enter hours: ");
		cHours = Integer.parseInt(myScanner.next());
		}
		catch (Exception e) {
			System.out.println("Hours must be a whole number, defaulted to 0");
			cHours=0;
			}
		
		try {
		//prompt and convert rate
		System.out.println("Enter Rate: ");
	cRate = Double.parseDouble(myScanner.next());
		}
		catch (Exception e) {
			System.out.println("Rate must be in decimal number, defaulted to 0");
			cRate=0;
		}
	
	}
	
	public static void calcs() {
		//calculation
		cPay = cHours * cRate;
	}
	
	public static void output() {
		//display name
		System.out.println("Name: " + iLastName + "," + iFirstName);
		//format and output pay
		oPay = nf.format(cPay);
		System.out.println("Pay is: " + oPay);
	}
}