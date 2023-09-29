package ComputerStore;

public class Computer {
	private String brand;
	private String model;
	private long serialNumber;
	private double price;
	
	private static long SNCounter = 100000;
	public static int computerCounter = 0;
	
	public Computer() {
		brand = "Apple";
		model = "MAC 2023";
		serialNumber = SNCounter++;
		price = 10000;
		computerCounter++;
	}
	public Computer(String br, String mod, double pr) {
		serialNumber = SNCounter++;
		brand = br;
		model = mod;
		price = pr;
		computerCounter++;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String br) {
		brand = br;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String mod) {
		model = mod;
	}
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long sn) {
		serialNumber = sn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double pr) {
		price = pr;
	}
	public static void displayComputer(Computer c) {
		System.out.println("Computer's brand is: "+c.getBrand());
		System.out.println("Computer's model is: "+c.getModel());
		System.out.println("Computer's serial Number is: "+c.getSerialNumber());
		System.out.println("Computer's price is: "+c.getPrice());
	}
	@Override
	public String toString() {
		return "Computer [brand=" + brand + ", model=" + model + ", serialNumber=" + serialNumber + ", price=" + price
				+ "]";
	}
	public static int findNumberOfCreatedComputers() {
		return computerCounter;
	}
	public boolean equals(Computer c) {
		if(brand == c.getBrand() && model == c.getModel() && price==c.getPrice()) {
			return true;
		}
		else
			return false;
	}
	
}
