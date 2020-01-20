package coe817lab1;

public class CarRental {
	
	private String name;
	private int zipCode;
	private String sizeOfCar;
	private double dailyRentalFee;
	private int rentalLength;
	private double totalRentalFee;
	
	
	
	public CarRental(String name, int zipCode, String sizeOfCar, int rentalLength) {
		super();
		this.name = name;
		this.zipCode = zipCode;
		this.sizeOfCar = sizeOfCar;
		this.rentalLength = rentalLength;
		
		switch(this.sizeOfCar) {
			case "economy":
				this.dailyRentalFee = 29.99;
				this.totalRentalFee = this.dailyRentalFee * this.rentalLength;
				break;
			case "midsize":
				this.dailyRentalFee = 38.99;
				this.totalRentalFee = this.dailyRentalFee * this.rentalLength;
				break;
			case "fullsize":
				this.dailyRentalFee = 43.50;
				this.totalRentalFee = this.dailyRentalFee * this.rentalLength;
				break;
			default:
				break;
		}
	}

	public void display() {
		System.out.println("Here is your reciept, thank you for booking and drive safe!");
		System.out.println("Name: " + this.getName());
		System.out.println("Zip Code: " + this.getZipCode());
		System.out.println("Size of vehicle: " + this.getSizeOfCar());
		System.out.println("Daily rental fee: $" + this.getDailyRentalFee());
		System.out.println("Duration of rental: " + this.getRentalLength() + " day(s)");
		System.out.println("Total fee incurred: $" + this.getTotalRentalFee());
	}
	

	//getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getSizeOfCar() {
		return sizeOfCar;
	}

	public void setSizeOfCar(String sizeOfCar) {
		this.sizeOfCar = sizeOfCar;
	}

	public double getDailyRentalFee() {
		return dailyRentalFee;
	}

	public void setDailyRentalFee(double dailyRentalFee) {
		this.dailyRentalFee = dailyRentalFee;
	}

	public int getRentalLength() {
		return rentalLength;
	}

	public void setRentalLength(int rentalLength) {
		this.rentalLength = rentalLength;
	}

	public double getTotalRentalFee() {
		return this.totalRentalFee;
	}

	public void setTotalRentalFee(double totalRentalFee) {
		this.totalRentalFee = totalRentalFee;
	}
}
