	/*Calculates boat sales with markup using loops, ifs, and validation
	 * Tyler McFarland 12/10/18
	 */


	import java.util.*;
	import java.text.*;

	
public class Boat_Sales_Revised {

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
		static char MoreRecs = 'y';
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
		static boolean valid = true;
		static Scanner myScanner; //input device
		static String colHdgFormat = "%-12s %-12s \n";
		static NumberFormat nf;
		
		
		public static void main(String[] args) {
		
			init();
			
				while (MoreRecs == 'y') {
			
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
			
			//set number formatter
			nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
		}
		
		public static void input() {
			//Gets the boat code and sets markup and name also validates with loop
			do {
				try {
					System.out.print("Enter a Boat Type of B(Bass), P(Pontton), S(Ski), or C(Canoe): ");
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
								default:  System.out.println("Invalid data, re-enter");
										  iBoatCode = "invalid";
							}
				}
				catch (Exception e) {
					System.out.print("Invalid data, re-enter");
						iBoatCode = "invalid";
				}	
			
			}while(iBoatCode == "invalid");
			
			//Gets accessory code and sets name and cost validates with loop
			do{
				try {
					System.out.print("Enter an Accessory type of 1(Electronics), 2(Ski), or 3(Fishing): ");
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
							   default: System.out.println("Invalid Data, re-enter");
											iAccessCode = 30;
										
							}
				}
				
				catch (Exception e){
					System.out.println("Invalid accessory Type, re-enter");
						iAccessCode = 30;
				}					
			
			}while(iAccessCode == 30);
			
			//gets quantity and validates range
			do {
				try {
					System.out.print("Enter the Quantity of boats, limit 25: ");
					iQty = Integer.parseInt(myScanner.next());
						if (iQty < 1 || iQty > 25) {
							System.out.println("Quantity must be between 1 and 25, re-enter");
						}
				}	
				catch (Exception e) {
					System.out.println("Invalid data, re-enter");
						iQty = -1;
				}	
			
			}while (iQty < 1 || iQty > 25);		
					
			//gets boat cost and validates the range with if else statement
			//validates with a loop for correct answer
			do {
				try {
					System.out.print("Enter the cost of the boat(s) between 2,500 and 150,000: ");
						iBoatCost = Double.parseDouble(myScanner.next());
							if (iBoatCost < 2500 || iBoatCost > 150000) {
									System.out.println("Invalid cost, re-enter");
										iBoatCost = 2000;
							}
				}  
			
				catch (Exception e){
					System.out.println("Invalid Number, re-enter");
						iBoatCost = 2000;
				}
			
			}while(iBoatCost == 2000);
			
			//validates the same as the boat cost
			//added loop to get correct answer
			do {
				try {
					System.out.print("Enter prep cost between $100.00 and $9,999.99: ");
						iPrepCost = Double.parseDouble(myScanner.next());
							if (iPrepCost < 100 || iPrepCost > 9999.99) {
									System.out.println("Invalid data, re-enter");
										iPrepCost = 1;
							}
				}	
			
				catch (Exception e) {
					System.out.println("Invalid Number, re-enter");
						iPrepCost = 1;
				}
			
			}while(iPrepCost == 1);
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
			//outputting formatted data making all fields the same size to line up decimal
			System.out.format("%-7s \n", oBoatName);                              
			
			System.out.format("%-15s \n", oAccessName);
			
			System.out.format("%-15s %20s \n", "Quantity:", iQty);
			
			oBoatCost = nf.format(iBoatCost);
				System.out.format("%-15s %20s \n", "Boat Cost:", oBoatCost);
			
			oAccessCost = nf.format(iAccessCost);
				System.out.format("%-15s %20s \n", "Accessory Cost:", oAccessCost);
			
			oPrepCost = nf.format(iPrepCost);
				System.out.format("%-15s %20s \n", "Prep Cost:", oPrepCost);
			
			oMrkAmt = nf.format(cMrkAmt);
				System.out.format("%-15s %20s \n", "Markup Amount:", oMrkAmt);
			
			oSub = nf.format(cSub);
				System.out.format("%-15s %20s \n", "Sub:", oSub);
			
			oTax = nf.format(cTax);
				System.out.format("%-15s %20s \n", "Tax:", oTax);
			
			oTotal = nf.format(cTotal);
				System.out.format("%-15s %20s \n", "Total:", oTotal);
			
			//asking for another reciept, validating response with a loop
			do {
				try {
				System.out.println("Would you like to enter another order? y to continue or n to terminate");
					MoreRecs = myScanner.next().toLowerCase().charAt(0);
						switch (MoreRecs) {
							case 'y':  MoreRecs = 'y';
									   		break;
							case 'n':  MoreRecs = 'n';
									   		System.out.println("Program Terminated, Have a good day!");
									   			break;
							 default:   MoreRecs = 'k';
							   		   		System.out.println("Invalid response, re-enter");
							   		   			break;
						}		
				}
		
				catch(Exception e){
					System.out.println("Invalid response, Program Terminated");
						MoreRecs = 'k';
				}
			
			}while(MoreRecs == 'k');
		}
		
		public static void totals(){
			System.out.format("%-15s %17s \n", "Total Sales: ", cSales);
			
			oGtTotal = nf.format(cGtTotal);
				System.out.format("%-15s %20s \n", "Grand Total: ", oGtTotal);
		}
			
	}

