package ComputerStore;

import java.util.*;

public class ComputerStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int maxComputers;
		int choice1;
		final String PASSWORD = "password";
		final int PASSWORDMAXCOUNT = 3;
		String pswd;
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Computer Store");
		do {
			System.out.println("How many computers do you want?");
			maxComputers = sc.nextInt();
			if (maxComputers <= 0) {
				System.out.println("Please enter positive inventory count");
			}
		} while (maxComputers <= 0);

		Computer[] inventory = new Computer[maxComputers];
		//inventory[0] = new Computer();

		boolean check = true;
		do {

			System.out.println("What do you want to do?\n" + "1.Enter new computers (password required)\n"
					+ "2.Change information of a computer (password required)\n"
					+ "3.Display all computers by a specific brand\n"
					+ "4.Display all computers under a certain a price.\n" + "5.Quit\n" + "Please enter your choice");
			choice1 = sc.nextInt();

			//int createdComputers = inventory[0].findNumberOfCreatedComputers();
			int createdComputers = Computer.findNumberOfCreatedComputers();

			switch (choice1) {
			case 1:
				System.out.println("Please enter your password");
				pswd = sc.next();
				if (!passwordChecker(pswd))
					break;

				System.out.println("Enter the number of computers you want to add");
				int addComputersize = sc.nextInt();
				int temp = 1;
				if ((maxComputers - createdComputers) >= addComputersize) {
					for (int i = createdComputers; i < (createdComputers + addComputersize); i++) {

						System.out.println("Enter the details for computer " + temp + ":");

						System.out.print("Enter the brand name of the computer" + temp + ": ");
						String br = sc.next();
						System.out.print("Enter the model of the computer" + temp + ": ");
						String model = sc.next();
						System.out.print("Enter the price of the computer" + temp++ + ": ");
						double pr = sc.nextDouble();
						inventory[i] = new Computer(br, model, pr);

					}
					for (int i = createdComputers; i < (createdComputers + addComputersize); i++) {
						System.out.println("Computer details you have entered are:");
						inventory[i].displayComputer(inventory[i]);
					}
					System.out.println();
				} else {
					System.out.println("You cannot add " + addComputersize + " computers instead you can add up to "
							+ (maxComputers - createdComputers) + " computers.");
				}
				System.out.println();
				break;

			case 2:
				System.out.println("Please enter your password");
				pswd = sc.next();
				int index;
				if (!passwordChecker(pswd))
					break;
				char check2 = 'Y';
				do {
					System.out.print("Please enter the number of computer you want to modify: ");
					index = sc.nextInt();
					if (index-1 >= createdComputers) {
						System.out.println(
								"There is no computer in this location, please enter Y if you wish to enter another computer number, enter Q if you want to quit.");
						check2 = sc.next().charAt(0);
					} else {
						check2 = 'N';
					}
				} while (check2 == 'Y');
				if(check2 == 'Q') {
					System.out.println("You have exited from option 2\n");
					break;
				}
				System.out.println("The details of the computer with the specified index are:");
				inventory[index-1].displayComputer(inventory[index-1]);
				//test
				boolean check3 = true;
				int choice2;
				do {

					System.out.println("What information would you like to change?\n"
							+ "1.brand\n"
							+ "2.model\n"
							+ "3.SN\n"
							+ "4.price\n"
							+ "5.Quit\n"
							+ "Enter your choice:"
							+ "");
					choice2 = sc.nextInt();
					
					switch(choice2) {
					case 1: System.out.print("Enter the brand name you want to update: ");
						inventory[index-1].setBrand(sc.next());
						inventory[index-1].displayComputer(inventory[index-1]);
						break;
					case 2: System.out.print("Enter the model name you want to update: ");
						inventory[index-1].setModel(sc.next());
						inventory[index-1].displayComputer(inventory[index-1]);
						break;
					case 3: System.out.print("Enter the SN you want to update: ");
						inventory[index-1].setSerialNumber(sc.nextLong());
						inventory[index-1].displayComputer(inventory[index-1]);
						break;
					case 4: System.out.print("Enter the price you want to update: ");
						inventory[index-1].setPrice(sc.nextDouble());
						inventory[index-1].displayComputer(inventory[index-1]);
						break;
					case 5: check3 = false;
						break;
					default: System.out.println("Please enter a number between 1 and 5");
						break;
					}
				}while(check3);
				
				
				System.out.println();
				break;

			case 3:
				System.out.println("Enter a brand name, to find the computers with the brand name");
				String brandName = sc.next();
				findComputersByBrand(inventory, brandName);
				break;

			case 4:
				System.out.println("Enter the price, to find the computers less than the given price");
				double value = sc.nextDouble();
				findCheaperThanPrice(inventory, value);
				break;

			case 5:
				check = false;
				System.out.println("Thank you for visiting our store. Have a good day!");
				break;

			default:
				System.out.println("Please enter a number between 1 and 5");
				break;
			}

		} while (check);

	}

	public static boolean passwordChecker(String pswd) {
		Scanner sc = new Scanner(System.in);
		final String PASSWORD = "password";
		final int PASSWORDMAXCOUNT = 3;
		int pswdCount = 1;
		while (!pswd.equals(PASSWORD) && pswdCount != PASSWORDMAXCOUNT) {
			//if (!pswd.equals(PASSWORD) && pswdCount != PASSWORDMAXCOUNT) {
				System.out.println(
						"Your password is Invalid, you've left with " + (PASSWORDMAXCOUNT - pswdCount) + " more tries");
			//}
			System.out.println("Please enter your password");
			pswd = sc.next();
			pswdCount++;
		}
		if (!pswd.equals(PASSWORD) && pswdCount == PASSWORDMAXCOUNT) {
			System.out.println("Sorry, you cannot proceed further as you have entered wrong password for 3 times.\n");
			return false;
		} else {
			return true;
		}
	}

	public static void findComputersByBrand(Computer[] inventory, String brandName) {
		//int createdComputers = inventory[0].findNumberOfCreatedComputers();
		int createdComputers = Computer.findNumberOfCreatedComputers();
		int count = 0;
		for (int i = 0; i < createdComputers; i++) {
			if (inventory[i].getBrand().equals(brandName)) {
				inventory[i].displayComputer(inventory[i]);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("There are no computers with specified brand name\n");
		}

	}

	public static void findCheaperThanPrice(Computer[] inventory, double price) {
		//int createdComputers = inventory[0].findNumberOfCreatedComputers();
		int createdComputers = Computer.findNumberOfCreatedComputers();
		int count = 0;
		for (int i = 0; i < createdComputers; i++) {
			if (inventory[i].getPrice() <= price) {
				inventory[i].displayComputer(inventory[i]);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("There are no computers under the specified price\n");
		}
	}

}
