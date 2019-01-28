//Program demonstrates major control breaks	
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.*;
import java.util.*;
public class SubtotalReport {
	
	static String iID;
	static String MoreRecs = "yes";
	static char iGender;
	static String oGender;
	static String iMajor;
	static String hMajor;
	static String oMajor;
	static double iDonation;
	static String oDonation;
	static int cMjStuCtr = 0;
	static double cMjDonCtr = 0;
	static String oMjDonCtr;
	static int cGtStuCtr = 0;
	static double cGtDonCtr = 0;
	static String oGtDonCtr;
	static LocalDate today = LocalDate.now();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	static NumberFormat nf;
	static DecimalFormat df = new DecimalFormat("$##,###,###.00");
	static PrintWriter pw;
	static Scanner FundScanner;
	
	public static void main(String[] args) {
		init();
		
		while (MoreRecs == "yes") {
			System.out.println(iMajor);
			System.out.println(hMajor);
			if (iMajor.equals(hMajor)) {
			}
			else {
				subtotals();
			}
		calcs();
		
		output();
		
		input();
		}
		
		subtotals();
		
		GrandTotals();
		
		pw.close();

	}
	
	public static void init() {
		try {
			FundScanner = new Scanner(new File("IHCCFUND.DAT"));
			FundScanner.useDelimiter(System.getProperty("line.separator"));
		}
		catch (FileNotFoundException e) {
			System.out.println("Input File Error");
		}
		
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
		
		try {
		pw = new PrintWriter(new File("subtotal.prt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Output file error");
		}
		input();
		
		heading();
		
		hMajor = iMajor;
	}	
	public static void input() {
		String record;
		String iString;
		
		if (FundScanner.hasNext()) {
			record = FundScanner.next();
			
			iID = record.substring(0, 7);
			
			iString = record.substring(7,8);
			iGender = iString.charAt(0);
			
			iMajor = record.substring(8,10);
			
			iString = record.substring(10,17);
			iDonation = Double.parseDouble(iString);
		}
		else {
			MoreRecs = "no";
		}
	}
	
	public static void calcs() {
		cMjStuCtr++;
		
		cMjDonCtr += iDonation;
	}
	
	public static void heading() {
		pw.format("%-10s%-55s%-30s%n", "", today.format(dtf), "IHCC");
		
		pw.format("%-10s%-48s%-37s%n%n", "", "", "Fundraiser Report");
		
		pw.format("%-10s%-35s%-30s%-54s%-35s%n%n", "", "Student ID", "Gender", "Major", "Donation");
	}
	
	public static void output() {
		switch (iMajor) {
			case "01":	oMajor = "Computer Software Development";
			break;
			
			case "02":  oMajor = "Diesel Power Systems Technology";
			break;
			
			case "03":  oMajor = "Automotive Technology";
			break;
			
			case "04":	oMajor = "Laser/Electro-Optics Technology";
			break;
			
			case "05":	oMajor = "Robotics/Automation Technology";
			break;
			
			case "06":	oMajor = "Digital Forensics";
			break;
			
			case "07":	oMajor = "Machine Technology";
			break;
			
			case "08":	oMajor = "Geospatial Technology";
			break;
			
			case "09":	oMajor = "Administrative Assistant";
			break;
			
			case "10":	oMajor = "Accounting Assistant";
			break;
			
			case "11":	oMajor = "Welding Technology";
			break;
			
			case "12":	oMajor = "Automotive Collision Technology";
			break;
			
			case "13":	oMajor = "Aviation Pilot Training";
			break;
		}
		
		switch (iGender) {
			case 'M':	oGender = "Male";
			break;
		
			case 'F':	oGender = "Female";
			break;
		}
		
		oDonation = nf.format(iDonation);
		
		pw.format("%-10s%-35s%-30s%-54s%-35s%n", "", iID, oGender, oMajor, oDonation);
	}
	
	static public void subtotals() {
		cGtStuCtr += cMjStuCtr;
		
		cGtDonCtr += cMjDonCtr;
		
		oMjDonCtr = nf.format(cMjDonCtr);
		
		pw.format("%n%-10s%-7s%-58s%17s%-21s%-16s%-20s%n%n", "", "Major:", oMajor, "Student Counter: ", cMjStuCtr, "Total Donation:", oMjDonCtr);
		
		cMjStuCtr = 0;
		
		cMjDonCtr = 0;
		
		hMajor = iMajor;
	}
	
	static public void GrandTotals() {
		oGtDonCtr = df.format(cGtDonCtr);
		
		pw.format("%n%-10s%-65s%-7s%-11s%-17s%-14s", "", "Grand Totals:","Student Counter:", cGtStuCtr, "Total Donations:", cGtDonCtr);
		
	}
		

}
