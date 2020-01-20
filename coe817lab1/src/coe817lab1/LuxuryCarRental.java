package coe817lab1;
import java.util.Scanner; 

public class LuxuryCarRental extends CarRental {
	
	private double chaufferCharge = 200.0;
	private double dailyLuxuryCarFee = 79.99;

	public LuxuryCarRental(String name, int zipCode, int rentalLength) {
		super(name, zipCode, "Luxury", rentalLength);
		System.out.println("Would you like to include a chauffuer at a rate of $200/day Y/N?");
		Scanner scan = new Scanner(System.in);
		String response = scan.next().toLowerCase();
		scan.close();
		if(response.equals("y")){
			System.out.println("Your chauffer's name is Hugo!");
		}else {
			this.chaufferCharge = 0.0;
		}
		super.setDailyRentalFee(dailyLuxuryCarFee);
		super.setTotalRentalFee((this.chaufferCharge + this.dailyLuxuryCarFee) * rentalLength);
	}
	

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		System.out.println("Daily chauffer charge: $" + this.getChaufferCharge());
		System.out.println("Total chauffer charge: $" + this.getChaufferCharge() * this.getRentalLength());
	}
	
	
	// getters and setters
	public double getChaufferCharge() {
		return this.chaufferCharge;
	}

	public void setChaufferCharge(double chauffer_charge) {
		this.chaufferCharge = chauffer_charge;
	}
}
