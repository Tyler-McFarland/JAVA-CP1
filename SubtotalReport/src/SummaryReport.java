//Program demonstrates major control breaks	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SummaryReport {


		static LocalDate today = LocalDate.now();
		static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		static String iID;
		static char iGender;
		static String iMajorCode;
		static double iDonation;
		static String MoreRecs = "yes";
		static PrintWriter pw;
		static DecimalFormat df;
		static Scanner FundScanner;
		
		//men/women
		static Integer cMen = 0;
		static double cMenDon = 0;
		static double cMenAvg = 0;
		static Integer cWomen = 0;
		static double cWomDon = 0;
		static double cWomAvg = 0;
		
		//Information Tech
		static Integer cInfo = 0;
		static double cInfoDon = 0;
		static double cInfoAvg = 0;
		
		//Manufacture tech
		static Integer cManufacture = 0;
		static double cManufactureDon = 0;
		static double cManufactureAvg = 0;
		
		//Transportation tech
		static Integer cTrans = 0;
		static double cTransDon = 0;
		static double cTransAvg = 0;
		
		//Information Tech men/women
		static Integer cInfoMen = 0;
		static double cInfoMenDon = 0;
		static double cInfoMenAvg = 0;
		static Integer cInfoWom = 0;
		static double cInfoWomDon = 0;
		static double cInfoWomAvg = 0;
		
		//Manufacture men/women
		static Integer cManufactureMen = 0;
		static double cManufactureMenDon = 0;
		static double cManufactureMenAvg = 0;
		static Integer cManufactureWom = 0;
		static double cManufactureWomDon = 0;
		static double cManufactureWomAvg = 0;
		
		//Transport men/women
		static Integer cTransMen = 0;
		static double cTransMenDon = 0;
		static double cTransMenAvg = 0;
		static Integer cTransWom = 0;
		static double cTransWomDon = 0;
		static double cTransWomAvg = 0;
		
		//overall
		static Integer cCtr = 0;
		static double cTotal = 0;
		static double cAverage;
		
		//format Donation fields
		static String oMenDon;
		static String oWomDon;
		static String oInfoDon;
		static String oInfoMenDon;
		static String oInfoWomDon;
		static String oManufactureDon;
		static String oManufactureMenDon;
		static String oManufactureWomDon;
		static String oTransDon;
		static String oTransMenDon;
		static String oTransWomDon;
		static String oTotalDon;
		
		//format average fields
		static String oMenAvg;
		static String oWomAvg;
		static String oInfoAvg;
		static String oInfoMenAvg;
		static String oInfoWomAvg;
		static String oManufactureAvg;
		static String oManufactureMenAvg;
		static String oManufactureWomAvg;
		static String oTransAvg;
		static String oTransMenAvg;
		static String oTransWomAvg;
		static String oTotalAvg;


		public static void main(String[] args) {
			init();
			
			input();
				
			while (MoreRecs == "yes") {
				calcs();
				
				input();
			}
			
			totals();
			
			output();
			
			pw.close();
		}
		
	
	
		public static void init() {
			df = new DecimalFormat("$##,###.00");
			
			try {
				FundScanner = new Scanner(new File("IHCCFUND.DAT"));
				FundScanner.useDelimiter(System.getProperty("line.separator"));
			}
			catch (FileNotFoundException e1) {
				System.out.println("File Error");
				System.exit(1);
			}
			
			try {
				pw = new PrintWriter(new File ("summary.prt"));
			} catch (FileNotFoundException e) {
				System.out.println("Output file error");
			}
		}
		
		
		public static void input() {
			String record;
			String iString;
			
			if (FundScanner.hasNext()) {
				record = FundScanner.next();
				
				iID = record.substring(0, 7);
				
				iString = record.substring(7,8);
				iGender = iString.charAt(0);
				
				iMajorCode = record.substring(8,10);
				
				iString = record.substring(10,17);
				iDonation = Double.parseDouble(iString);
			}
			else {
				MoreRecs = "no";
			}
		}
		public static void calcs() {
			cTotal += iDonation;
			
			cCtr++;
			
			switch(iGender) {
				case 'M':	cMen++;
							cMenDon += iDonation;
							switch (iMajorCode) {
							
							//information counters
							case "01":	cInfo++;
										cInfoDon+=iDonation;
										cInfoMen++;
										cInfoMenDon+=iDonation;
										break;
							
							case "06":	cInfo++;
					        			cInfoDon+=iDonation;
					        			cInfoMen++;
					        			cInfoMenDon+=iDonation;
					        			break;
							
							case "08":	cInfo++;
					        			cInfoDon+=iDonation;
					        			cInfoMen++;
					        			cInfoMenDon+=iDonation;
					        			break;
							
							case "09":	cInfo++;
					        			cInfoDon+=iDonation;
					        			cInfoMen++;
					        			cInfoMenDon+=iDonation;
					        			break;
						
							case "10":	cInfo++;
					        			cInfoDon+=iDonation;
					        			cInfoMen++;
					        			cInfoMenDon+=iDonation;
					        			break;
						
							//Manufacture Counters
							case "04":	cManufacture++;
										cManufactureDon+=iDonation;
										cManufactureMenDon+=iDonation;
										cManufactureMen++;
										break;
						
							case "05":	cManufacture++;
										cManufactureDon+=iDonation;
										cManufactureMenDon+=iDonation;
										cManufactureMen++;
										break;
						
							case "07":	cManufacture++;
										cManufactureDon+=iDonation;
										cManufactureMenDon+=iDonation;
										cManufactureMen++;
										break;
							
							case "11":	cManufacture++;
										cManufactureDon+=iDonation;
										cManufactureMenDon+=iDonation;
										cManufactureMen++;
										break;
							
							//Transport counters			
							case "02":	cTrans++;
										cTransDon+=iDonation;
										cTransMen++;
										cTransMenDon+=iDonation;
										break;
										
							case "03":	cTrans++;
										cTransDon+=iDonation;
										cTransMen++;
										cTransMenDon+=iDonation;
										break;
										
							case "12":	cTrans++;
										cTransDon+=iDonation;
										cTransMen++;
										cTransMenDon+=iDonation;
										break;
										
							case "13":	cTrans++;
										cTransDon+=iDonation;
										cTransMen++;
										cTransMenDon+=iDonation;
										break;
							}
				break;
							
				case 'F':	cWomen++;
							cWomDon+=iDonation;
							switch (iMajorCode) {
							
								//information counters
								case "01":	cInfo++;
											cInfoDon+=iDonation;
											cInfoWom++;
											cInfoWomDon+=iDonation;
											break;
								
								case "06":	cInfo++;
						        			cInfoDon+=iDonation;
						        			cInfoWom++;
						        			cInfoWomDon+=iDonation;
						        			break;
								
								case "08":	cInfo++;
						        			cInfoDon+=iDonation;
						        			cInfoWom++;
						        			cInfoWomDon+=iDonation;
						        			break;
								
								case "09":	cInfo++;
						        			cInfoDon+=iDonation;
						        			cInfoWom++;
						        			cInfoWomDon+=iDonation;
						        			break;
							
								case "10":	cInfo++;
						        			cInfoDon+=iDonation;
						        			cInfoWom++;
						        			cInfoWomDon+=iDonation;
						        			break;
							
								//Manufacture Counters
								case "04":	cManufacture++;
											cManufactureDon+=iDonation;
											cManufactureWomDon+=iDonation;
											cManufactureWom++;
											break;
							
								case "05":	cManufacture++;
											cManufactureDon+=iDonation;
											cManufactureWomDon+=iDonation;
											cManufactureWom++;
											break;
							
								case "07":	cManufacture++;
											cManufactureDon+=iDonation;
											cManufactureWomDon+=iDonation;
											cManufactureWom++;
											break;
								
								case "11":	cManufacture++;
											cManufactureDon+=iDonation;
											cManufactureWomDon+=iDonation;
											cManufactureWom++;
											break;
								
								//Transport counters			
								case "02":	cTrans++;
											cTransDon+=iDonation;
											cTransWom++;
											cTransWomDon+=iDonation;
											break;
											
								case "03":	cTrans++;
											cTransDon+=iDonation;
											cTransWom++;
											cTransWomDon+=iDonation;
											break;
								
								case "12":	cTrans++;
											cTransDon+=iDonation;
											cTransWom++;
											cTransWomDon+=iDonation;
											break;
											
								case "13":	cTrans++;
											cTransDon+=iDonation;
											cTransWom++;
											cTransWomDon+=iDonation;
							break;	
							}
			}
		}
		
		
		public static void totals() {
			if (cCtr != 0) {
				cAverage = cTotal / cCtr;
			}
			if (cMen != 0) {
				cMenAvg = cMenDon / cMen;
			}
			if (cWomen != 0) {
				cWomAvg = cWomDon / cWomen;
			}
			if (cInfo != 0) {
				cInfoAvg = cInfoDon / cInfo;
			}
			if (cInfoMen != 0) {
				cInfoMenAvg = cInfoMenDon / cInfoMen;
			}
			if (cInfoWom != 0) {
				cInfoWomAvg = cInfoWomDon / cInfoWom;
			}
			if (cManufacture != 0) {
				cManufactureAvg = cManufactureDon / cManufacture;
			}
			if (cManufactureMen != 0) {
				cManufactureMenAvg = cManufactureMenDon / cManufactureMen;
			}
			if (cManufactureWom != 0) {
				cManufactureWomAvg = cManufactureWomDon / cManufactureWom;
			}
			if (cTrans != 0) {
				cTransAvg = cTransDon / cTrans;
			}
			if (cTransMen != 0) {
				cTransMenAvg = cTransMenDon / cTransMen;
			}
			if (cTransWom != 0) {
				cTransWomAvg = cTransWomDon / cTransWom;
			}
		}
		
		public static void output() {
			oMenDon = df.format(cMenDon);
			oWomDon = df.format(cWomDon);
			oInfoDon = df.format(cInfoDon);
			oInfoMenDon = df.format(cInfoMenDon);
			oInfoWomDon = df.format(cInfoWomDon);
			oManufactureDon = df.format(cManufactureDon);
			oManufactureMenDon = df.format(cManufactureMenDon);
			oManufactureWomDon = df.format(cManufactureWomDon);
			oTransDon = df.format(cTransDon);
			oTransMenDon = df.format(cTransMenDon);
			oTransWomDon = df.format(cTransWomDon);
			oTotalDon = df.format(cTotal);
			
			//formatting averages
			oMenAvg = df.format(cMenAvg);
			oWomAvg = df.format(cWomAvg);
			oInfoAvg = df.format(cInfoAvg);
			oInfoMenAvg = df.format(cInfoMenAvg);
			oInfoWomAvg = df.format(cInfoWomAvg);
			oManufactureAvg = df.format(cManufactureAvg);
			oManufactureMenAvg = df.format(cManufactureMenAvg);
			oManufactureWomAvg = df.format(cManufactureWomAvg);
			oTransAvg = df.format(cTransAvg);
			oTransMenAvg = df.format(cTransMenAvg);
			oTransWomAvg = df.format(cTransWomAvg);
			oTotalAvg = df.format(cAverage);
			
			//company title line
			pw.format("-%10s%-25s%-14s%n", today.format(dtf), " ", "IHCC Fundraiser");
			
			//report title line
			pw.format("%-33s%-20s%n%n", "", "Donation Report");
			
			//headings
			pw.format("%-20s%-23s%-25s%-10s%n%n", "Category", "Donation Total", "Donation Average", "Count");
		
			pw.format("%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s"
					+ "%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n"
					+ "%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%-23s%-26s%-21s%-15s%n%n", 
							"Men", oMenDon, oMenAvg, cMen,
							"Women", oWomDon, oWomAvg, cWomen, 
							"Information Tech",oInfoDon, oInfoAvg, cInfo,
							"I.T Men",oInfoMenDon, oInfoMenAvg, cInfoMen, 
							"I.T Women",   oInfoWomDon, oInfoWomAvg, cInfoWom,
							"Manufacturing Tech",  oManufactureDon, oManufactureAvg, cManufacture,
							"M.T Men",  oManufactureMenDon, oManufactureMenAvg, cManufactureMen,
							"M.T Women",  oManufactureWomDon, oManufactureWomAvg, cManufactureWom,
							"Transportation Tech", oTransDon, oTransAvg, cTrans,
							"T.T Men", oTransMenDon, oTransMenAvg, cTransMen,
							"T.T Women",  oTransWomDon, oTransWomAvg, cTransWom,
							"Overall", oTotalDon, oTotalAvg, cCtr);
		}
}