//demonstrates array processing with pop sales
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PopSalesArray {
		static LocalDate today = LocalDate.now();
		static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		static int i;							//used in the for loops 
		static int f;							//used in the error array
		static int s = -1;							//used to validate the pop array (used index2 in the deposit amount so I made a variable that doesnt conflict)
		static char MoreRecs = 'y';
		static String iLName;
		static String iFName;
		static String iAddress;
		static String iCity;
		static String iState;
		static int iZip;
		static int iPopType;
		static int iCases;
		static String oTotal;
		static String oDeposit;
		static String iTeam;
		static String oTeam;
		static Double cTotal;
		static Double cDeposit;
		static boolean errSw;					//Used in main to tell whether to do valid ouput or error
		static PrintWriter pw1;					
		static PrintWriter pw2;
		static Scanner SaleScanner;
		static String[] PopType = {"Coke", "Diet Coke", "Mello Yello", "Cherry Coke", "Diet Cherry Coke", "Sprite"};	//pop type array
		static int[] PopCtr = new int[6];		//pop counter array
		static String[] error = {"Invalid Last Name", "Invalid First Name", "Invalid Address", "Invalid City" ,"Invalid State", "Invalid Zip", "Invalid Pop Type", "Invalid Quantity", "Invalid Team"}; //error array
		static String[] Team = {"A","B","C","D","E"};		//team array			
		static double[] TeamCtr = new double[5];			//team counter array	(Parallel to team array)
		static String[] State = {"IA", "IL", "MI", "MO", "NE", "WI"};			//state array
		static double[] Deposit = {.05,0,.1,0,.05,.05};							//deposit array parallel to state array
		static int index = -1;								//Used to validate team array and add to counter
		static int index2 = -1;								//used to validate state and add to counter
		static NumberFormat nf;

		public static void main(String[] args) {
			init();
			
			validation();
			
			while (MoreRecs == 'y'){
				if (errSw == false) {
					calcs();
					
					output();
				}
				else {
					errOut();
				}
				validation();
			}
			totals();
			pw1.close();
			pw2.close();
			

		}
		
		public static void init() {
			nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
			
			//zeroes pop counter array
			for (i=0; i<PopCtr.length; i++) {
				PopCtr[i] = 0;
			}
			
			//zeroes team counter array
			for (i=0; i<TeamCtr.length; i++) {
				TeamCtr[i] = 0;
			}
			
			//valid output
			try {
				pw1 = new PrintWriter(new File("JAVAPOPSLB.PRT"));
			}
			catch (FileNotFoundException e) {
				System.out.print("Output File not Found");
			}
			
			//error output
			try {
				pw2 = new PrintWriter(new File("JAVAPOPERB.PRT"));
			}
			catch (FileNotFoundException e) {
				System.out.print("Error File Not Found");
			}
			
			try {
				SaleScanner = new Scanner(new File("POPSL.DAT"));
				SaleScanner.useDelimiter(System.getProperty("line.separator"));
			}
			catch (FileNotFoundException e){
				System.out.print("DAT File Not Found");
			}
			
			heading();
		}
		
		public static void validation() {
			//reset index to avoid the comparisons coming back wrong
			index = -1;
			index2 = -1;
			//variable f is used to get error message from array, check if inside array using index variables
			String string;
			String record;
			
			if (SaleScanner.hasNext()) {
				record = SaleScanner.next();
				
				try {
					iLName = record.substring(0, 15);
					if (iLName.trim().isEmpty()) {
						f = 0;
						errSw = true;
					}
				}
				catch(Exception e) {
					f = 0;
					errSw = true;
				}
				
				try {
					iFName = record.substring(15, 30);
					if (iFName.trim().isEmpty()) {
						f = 1;
						errSw = true;
					}
				}
				catch(Exception e){
					f = 1;
					errSw = true;
				}
				
				try {
					iAddress = record.substring(30, 45);
					if (iAddress.trim().isEmpty()) {
						f = 2;
						errSw = true;
					}
				}
				catch(Exception e) {
					f = 2;
					errSw = true;
				}
				
				try {
					iCity = record.substring(45, 55);
					if (iCity.trim().isEmpty()) {
						f = 3;
						errSw = true;
					}
				}
				catch (Exception e) {
					f = 3;
					errSw = true;
				}
				
				try {
					iState = record.substring(55,57);
					for (i=0; i<State.length; i++) {
						if (iState.equals(State[i])) {
							index2 = i;
						}
					}
						if (index2 < 0) {
							f = 4;
							errSw = true;
						}
				}
				
				catch (Exception e) {
					f = 4;
					errSw = true;
				}
				
				
					string = record.substring(57, 66);
					if (string.trim().isEmpty()) {
						f = 5;
						errSw = true;
						
					}
				try {
					iZip = Integer.parseInt(string);
				}
				catch (Exception e) {
					f = 5;
					errSw = true;
				}
				
				try {
					string = record.substring(66, 68);
					if (string.trim().isEmpty()) {
						f = 6;
						errSw = true;
					}
					iPopType = Integer.parseInt(string);
					for (i=0; i<PopType.length; i++) {
						if ((iPopType - 1) == i) {
							s = i;
						}
					}
					
					if (s < 0) {
							f = 6;
							errSw = true;
					}
				}catch (Exception e) {
					f = 6;
					errSw = true;
				}
				
				try {
					string = record.substring(68,70);
					iCases = Integer.parseInt(string);
					if (iCases < 1 || iCases > 99) {
						f = 7;
						errSw = true;
					}
				}
				catch (Exception e) {
					f = 7;
					errSw = true;
				}
				
				try { 
					iTeam = record.substring(70,71);
					for (i=0; i<Team.length; i++) {
						if (iTeam.equals(Team[i])) {
							index = i;
						}
					}
						
						if (index < 0) {
							f = 8;
							errSw = true;
						}
					
				}
				catch (Exception e) {
					f = 8;
					errSw = true;
				}
			}
			else {
				MoreRecs = 'n';
			}
		}
		
		public static void calcs() {
			PopCtr[iPopType - 1]++;
			
			cDeposit = Deposit[index2] * (iCases * 24);
			
			PopCtr[iPopType - 1] += iCases;
			
			cTotal = (18.71 * iCases) + cDeposit;
			
			TeamCtr[index] += cTotal;
		}
		
		public static void heading() {
			//headings for error report
			pw2.format("%6s%-50s%-30s%n", today.format(dtf), "", "ALBIA SOCCER CLUB FUNDRAISER");
			pw2.format("%-66s%-15s%n", "","MCFARLAND DIVISION");
			pw2.format("%-69s%-30s%n", "","ERROR REPORT");
			pw2.format("%-15s%-15s%-17s%-11s%-7s%-12s%-10s%-11s%-10s%-10s%n", "LAST NAME", "FIRST NAME", "ADDRESS", "CITY", "STATE", "ZIP", "POP TYPE", "QUANTITY", "TEAM", "ERROR");
		
			//headings for the correct output
			pw1.format("%6s%-50s%-30s%n", today.format(dtf), "", "ALBIA SOCCER CLUB FUNDRAISER");
			pw1.format("%-66s%-15s%n", "","MCFARLAND DIVISION");
			pw1.format("%-69s%-30s%n%n", "","SALE REPORT");
			pw1.format("%-17s%-17s%-17s%-11s%-9s%-10s%-13s%-11s%-6s%-15s%-9s%n", "LAST NAME", "FIRST NAME", "ADDRESS", "CITY", "STATE", "ZIP", "POP TYPE", "QUANTITY", "TEAM", "DEPOSIT", "TOTAL");
		}
		
		public static void errOut() {
			pw2.format("%-15s%-15s%-17s%-13s%-5s%-15s%-10s%-10s%-8s%-10s%n", iLName, iFName, iAddress, iCity, iState, iZip, iPopType, iCases, iTeam, error[f]);
			errSw = false;
			f = -1;
			s = -1;
		}
		
		public static void output() {
			oTotal = nf.format(cTotal);
			
			oDeposit = nf.format(cDeposit);
			
			pw1.format("%-17s%-17s%-17s%-13s%-5s%-12s%-17s%-3s%7s%10s%15s%n", iLName, iFName, iAddress, iCity, iState, iZip, PopType[iPopType - 1], iCases, iTeam, oDeposit, oTotal);
			
			s = -1;
		}
		
		public static void totals() {
			pw1.format("%n%n%n%-60s%-30s%n", "", "ALBIA SOCCER CLUB FUNDRAISER");
			pw1.format("%-66s%-15s%n", "","MCFARLAND DIVISION");
			pw1.format("%-69s%-30s%n", "","SALE REPORT");
			
				//grand total detail lines
			for (i = 0; i < 3; i++) {
				pw1.format("%-20s%-5d", PopType[i], PopCtr[i]);
			}
			pw1.format("%n%n");
			for (i = 3; i < 6; i++) {
				pw1.format("%-20s%-5d", PopType[i], PopCtr[i]);
			}
				pw1.format("%n%n%-10s%n", "TEAM SALES");
				//loops to print every team
				for (i = 0;i < 5; i++) {
					oTeam = nf.format(TeamCtr[i]);
					pw1.format("%n%-5s%-15s%n%n", Team[i], nf.format(TeamCtr[i]));
				}
			}
		}



