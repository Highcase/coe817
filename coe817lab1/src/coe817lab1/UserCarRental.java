package coe817lab1;

import java.util.Scanner;

public class UserCarRental {
	
	
	public UserCarRental() {
		Scanner scan = new Scanner(System.in);
		String name = null, size = null; 
		int zip, duration;
		
		System.out.println("Hello and thank you for renting with us!");
		
		System.out.println("Please enter your full name: ");
		name = scan.nextLine();
		
		System.out.println("Please enter a 5 digit Zip Code: ");
		zip = scan.nextInt();
		
		System.out.println("Enter an amount in days you wish to rent a vehicle: ");
		duration = scan.nextInt();
		
		System.out.println("Will you be renting a luxury car today at a premium rate of 79.99?");
		System.out.println("Y/N");
		if(scan.next().toLowerCase().equals("y")) {
			new LuxuryCarRental(name, zip, duration).display();
		} else {
			System.out.println("Our vehicle size options are: economy, midsize and fullsize.");
			System.out.println("Please enter one of these options: ");
			size = scan.next();
			new CarRental(name, zip, size, duration).display();
		}
		
		scan.close();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		UserCarRental main = new UserCarRental();
	}
}
