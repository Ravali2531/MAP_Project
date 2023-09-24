//  	
// Project - Computer Store
// Written by: Ravali Gottam (2393061)
//  	

package ComputerStore;

import java.util.*;

public class ComputerStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int maxComputers = 0;
		int choice1 = 0;
		final String PASSWORD = "password";
		final int PASSWORDMAXCOUNT = 3;
		String pswd;
		Scanner sc = new Scanner(System.in);
		System.out.println("==============Welcome to the Computer Store==============\n");
		do {
			try {
				System.out.println("How many computers do you want?");
				maxComputers = sc.nextInt();
				if (maxComputers <= 0) {
					System.out.println("Please enter positive integer");
				}
			}
			catch (Exception e) {
				System.out.println("Exception " + e + " Occured. Enter a positive integer");
				sc.nextLine();
			}

		} while (maxComputers <= 0);

		Computer[] inventory = new Computer[maxComputers];
		// inventory[0] = new Computer();

		boolean check = true;
		do {
			try{
				System.out.print("\nWhat do you want to do?\n" + "1.Enter new computers (password required)\n"
						+ "2.Change information of a computer (password required)\n"
						+ "3.Display all computers by a specific brand\n"
						+ "4.Display all computers under a certain a price.\n" + "5.Quit\n" + "Please enter your choice ");
				choice1 = sc.nextInt();

				// int createdComputers = inventory[0].findNumberOfCreatedComputers();
				int createdComputers = Computer.findNumberOfCreatedComputers();

				switch (choice1) {
				case 1:
					System.out.print("\nPlease enter your password ");
					pswd = sc.next();
					if (!passwordChecker(pswd))
						break;

					int addComputersize = 0;
					int temp = 1;
					do {
							do {
							System.out.print("\nEnter the number of computers you want to add ");
							try{
								addComputersize = sc.nextInt();
								if(addComputersize <= 0) {
									System.out.print("Enter a positive integer ");
								}
							}
							catch (Exception e) {
								System.out.println("Exception " + e + " Occured. Enter a positive integer");
								sc.nextLine();
							}
							}while(addComputersize <= 0);
							if ((maxComputers - createdComputers) >= addComputersize) {
							for (int i = createdComputers; i < (createdComputers + addComputersize); i++) {

								System.out.println("Enter the details for computer " + temp + " below");
								double pr = 0;
								System.out.print("Enter the brand name of the computer " + temp + ": ");
								String br = sc.next();
								System.out.print("Enter the model of the computer " + temp + ": ");
								String model = sc.next();
								do {
									try{
										System.out.print("Enter the price of the computer " + temp + ": ");
										pr = sc.nextDouble();
										if (pr <= 0) {
											System.out.println("Please enter positive number");
										}
									}
									catch (Exception e) {
										System.out.println("Exception " + e + " Occured. Enter a positive integer");
										sc.nextLine();
									}
								} while (pr <= 0);
								inventory[i] = new Computer(br, model, pr);
								temp++;
								System.out.println();

							}
							System.out.println("\nComputer details you have entered are:");
							for (int i = createdComputers; i < (createdComputers + addComputersize); i++) {
								inventory[i].displayComputer(inventory[i]);
								System.out.println();
							}
						} else {
							if(maxComputers - createdComputers == 0) {
								System.out.println("Your inventory is full. You cannot add new computers!");
								break;
							}
							else {
							System.out.println("You cannot add " + addComputersize + " computers instead you can add up to "
									+ (maxComputers - createdComputers) + " computers.");
							}
						}
					} while ((maxComputers - createdComputers) < addComputersize);
					System.out.println();
					break;

				case 2:
					System.out.println("Please enter your password");
					pswd = sc.next();
					int index = 0;
					if (!passwordChecker(pswd))
						break;
					char check2 = 'Y';
					char check4 = 'Y';
					do {
						do {
							try{
								System.out.print("Please enter the number of computer you want to modify: ");
								index = sc.nextInt();
								if(index <= 0) {
									System.out.println("Please enter a positive number");
								}
							}
							catch (Exception e) {
							System.out.println("Exception " + e + " Occured. Enter a positive integer");
							sc.nextLine();
						}
						}while(index <= 0);
						if (index - 1 >= createdComputers) {
							System.out.println(
									"There is no computer in this location. Please do the following: \n1. Enter Y if you wish to enter another computer number\n2. Enter Q to quit.");
							check4 = sc.next().charAt(0);
							if(check4 == 'Y') {
								check2 = 'Y';
							}
							else {
								check2 = 'N';
							}
							
						} else {
							check2 = 'N';
						}
					} while (check2 == 'Y');
					
					if (check4 != 'Y' ) {
						System.out.println("You have exited from option 2\n");
						break;
					}
					System.out.println("\nThe details of the computer with the specified index are:\n");
					inventory[index - 1].displayComputer(inventory[index - 1]);
					System.out.println();

					boolean check3 = true;
					int choice2;
					do {
						try{
							System.out.print("What information would you like to change?\n" + "1.brand\n" + "2.model\n"
									+ "3.SN\n" + "4.price\n" + "5.Quit\n" + "Enter your choice:" + "");
							choice2 = sc.nextInt();

							switch (choice2) {
							case 1:
								System.out.print("Enter the brand name you want to update: ");
								inventory[index - 1].setBrand(sc.next());
								System.out.println("\nUpdated details of the computer are:");
								inventory[index - 1].displayComputer(inventory[index - 1]);
								break;
							case 2:
								System.out.print("Enter the model name you want to update: ");
								inventory[index - 1].setModel(sc.next());
								System.out.println("\nUpdated details of the computer are:");
								inventory[index - 1].displayComputer(inventory[index - 1]);
								break;
							case 3:
								long l = 0;
								do {
									try{
										System.out.print("Enter the SN you want to update: ");
										l = sc.nextLong();
										if(l <= 0) {
											System.out.println("Please enter postive number");
										}
									}
									catch (Exception e) {
										System.out.println("Exception " + e + " Occured. Enter a positive integer");
										sc.nextLine();
									}
								} while (l <= 0);
								inventory[index - 1].setSerialNumber(l);
								System.out.println("\nUpdated details of the computer are:");
								inventory[index - 1].displayComputer(inventory[index - 1]);
								break;
							case 4:
								double d = 0;
								do {
									try{
										System.out.print("Enter the price you want to update: ");
										d = sc.nextDouble();
										if (d <= 0) {
											System.out.println("Please enter a positive number");
										}
									}
									catch (Exception e) {
										System.out.println("Exception " + e + " Occured. Enter a positive integer");
										sc.nextLine();
									}
								} while (d <= 0);
								inventory[index - 1].setPrice(d);
								System.out.println("\nUpdated details of the computer are:");
								inventory[index - 1].displayComputer(inventory[index - 1]);
								break;
							case 5:
								check3 = false;
								break;
							default:
								System.out.println("Please enter a number between 1 and 5");
								break;
							}
						}
						catch (Exception e) {
							System.out.println("Exception " + e + " Occured. Enter a positive integer");
							sc.nextLine();
						}
						System.out.println();
					} while (check3);

					System.out.println();
					break;

				case 3:
					System.out.println("Enter a brand name, to find the computers with the brand name");
					String brandName = sc.next();
					findComputersByBrand(inventory, brandName);
					break;

				case 4:
					double value = 0;
					do {
						try{
							System.out.println("Enter the price, to find the computers less than the given price");
							value = sc.nextDouble();
							if (value <= 0) {
								System.out.println("Please enter a positive value:");
							}
						}
						catch (Exception e) {
							System.out.println("Exception " + e + " Occured. Enter a positive integer");
							sc.nextLine();
						}
					} while (value <= 0);
					findCheaperThanPrice(inventory, value);
					break;

				case 5:
					check = false;
					System.out.println("==============Thank you for visiting our store. Have a good day!=================");
					break;

				default:
					System.out.println("Please enter a number between 1 and 5");
					break;
				}
			}
		
			catch (Exception e) {
				System.out.println("Exception " + e + " Occured. Enter a positive integer");
				sc.nextLine();
			}

		} while (check);

		// Close the scanner
		sc.close();

	}

	// Function to verify the password
	public static boolean passwordChecker(String pswd) {
		Scanner sc = new Scanner(System.in);
		final String PASSWORD = "password";
		final int PASSWORDMAXCOUNT = 3;
		int pswdCount = 1;
		while (!pswd.equals(PASSWORD) && pswdCount != PASSWORDMAXCOUNT) {
			// if (!pswd.equals(PASSWORD) && pswdCount != PASSWORDMAXCOUNT) {
			System.out.println(
					"Your password is Invalid, you've left with " + (PASSWORDMAXCOUNT - pswdCount) + " more tries\n");
			// }
			System.out.print("Please enter your password ");
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

	// Function to retrieve the computer based on the brandname
	public static void findComputersByBrand(Computer[] inventory, String brandName) {
		int createdComputers = Computer.findNumberOfCreatedComputers();
		int count = 0;
		for (int i = 0; i < createdComputers; i++) {
			if (inventory[i].getBrand().equals(brandName)) {
				System.out.println("Computer: " + (i + 1));
				inventory[i].displayComputer(inventory[i]);
				count++;
				System.out.println();
			}
		}
		if (count == 0) {
			System.out.println("There are no computers with specified brand name\n");
		}

	}

	// Function to retrieve the computers data whose price is less than or equal to
	// the
	// mentioned price
	public static void findCheaperThanPrice(Computer[] inventory, double price) {
		int createdComputers = Computer.findNumberOfCreatedComputers();
		int count = 0;
		for (int i = 0; i < createdComputers; i++) {
			if (inventory[i].getPrice() <= price) {
				System.out.println("Computer: " + (i + 1));
				inventory[i].displayComputer(inventory[i]);
				count++;
				System.out.println();
			}
		}
		if (count == 0) {
			System.out.println("There are no computers under the specified price\n");
		}
	}

}
