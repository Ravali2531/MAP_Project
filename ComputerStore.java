//  	
// Project - Computer Store
// Written by: Ravali Gottam (2393061)
//Program Description: The below program allows user to specify maximum number of computers that a Computer Store can include and then the user is allowed to do below 5 operations.
//1. Add new computers to the store (Always the space in the array which is used to store the computers is checked)
//2. Edit the information of each computer such as brand, model, serial number, price.
//3. Display all the computers with a specified brand name
//4. Display all the computers which are less than specified price
//5. Quit the operation.
//  	

package ComputerStore;

import java.util.*;

public class ComputerStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//maxComputers is used to store maximum number of computers the store can include
		int maxComputers = 0;
		int choice1 = 0;
		String pswd;
		Scanner sc = new Scanner(System.in);
		System.out.println("==============Welcome to the Computer Store==============\n");
		
		//below loop is used to check if maxComputers is <= 0 and runs until a positive value >0 is inputed
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

		//inventory array is a computer object which is used to store all the computers data 
		Computer[] inventory = new Computer[maxComputers];

		boolean check = true;
		
		//below loop is run until option 5 i.e., quit is selected
		do {
			try{
				System.out.print("\nWhat do you want to do?\n" + "1.Enter new computers (password required)\n"
						+ "2.Change information of a computer (password required)\n"
						+ "3.Display all computers by a specific brand\n"
						+ "4.Display all computers under a certain a price.\n" + "5.Quit\n" + "Please enter your choice ");
				choice1 = sc.nextInt();
				
				//createdComputers is used to store the count of computers inserted in inventory array
				int createdComputers = Computer.findNumberOfCreatedComputers();

				switch (choice1) {
				case 1:
					System.out.print("\nPlease enter your password ");
					pswd = sc.next();
					
					//passwordChecker function is used to verify the password
					if (!passwordChecker(pswd))
						break;

					int addComputersize = 0;
					int temp = 1;
					
					//below loop is run until the user inputs a value which is less than or equal to createdComputer value
					do {
							//below loop is run until user inputs a positive integer value
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
							
							//Below code is executed only if there is enough space in the inventory array 
							if ((maxComputers - createdComputers) >= addComputersize) {
							for (int i = createdComputers; i < (createdComputers + addComputersize); i++) {

								System.out.println("Enter the details for computer " + temp + " below");
								double pr = 0;
								System.out.print("Enter the brand name of the computer " + temp + ": ");
								String br = sc.next();
								System.out.print("Enter the model of the computer " + temp + ": ");
								String model = sc.next();
								//below loop is run until a positive integer is entered for price
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
						} 
							else {
							//below code is executed when the createdComputers is equal to maximum size i.e., inventory array is full
							if(maxComputers - createdComputers == 0) {
								System.out.println("Your inventory is full. You cannot add new computers!");
								break;
							}
							//below statement is printed if user inputed a value which is greater than createdComputer value
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
					
					//passwordChecker function is used to verify the password
					if (!passwordChecker(pswd))
						break;
					char check2 = 'Y';
					char check4 = 'Y';
					
					//below loop is executed until a valid computer index is entered i.e., index should be <= createdComputer value
					do {
						//below loop is run until a positive integer >=0 is entered in index
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
						
						//if index is greater than createdComputers value, user is asked to choose one of 2 options i.e, either enter another computer number or quit from option 2
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
					
					//if index is correctly specified then the computer details in the specified index are displayed
					System.out.println("\nThe details of the computer with the specified index are:\n");
					inventory[index - 1].displayComputer(inventory[index - 1]);
					System.out.println();

					boolean check3 = true;
					int choice2;
					//user is asked to enter what information of a computer they want to modify and this loop is executed until the user want to quit i.e., enter 5
					do {
						try{
							System.out.print("What information would you like to change?\n" + "1.brand\n" + "2.model\n"
									+ "3.SN\n" + "4.price\n" + "5.Quit\n" + "Enter your choice:" + "");
							choice2 = sc.nextInt();

							switch (choice2) {
							//case 1 is to update brand of the computer
							case 1:
								System.out.print("Enter the brand name you want to update: ");
								inventory[index - 1].setBrand(sc.next());
								System.out.println("\nUpdated details of the computer are:");
								inventory[index - 1].displayComputer(inventory[index - 1]);
								break;
							//case 2 is used to update model of the computer
							case 2:
								System.out.print("Enter the model name you want to update: ");
								inventory[index - 1].setModel(sc.next());
								System.out.println("\nUpdated details of the computer are:");
								inventory[index - 1].displayComputer(inventory[index - 1]);
								break;
							//case 3 is used to update serial Number of the computer
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
							//case 4 is used to update price of the computer
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
							// case 5 is used to quit from option 2
							case 5:
								check3 = false;
								break;
							//if any number other than 1 to 5 is entered default is executed
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
					//findComputersByBrand function is used to display all the brands with specified brand name irrespective of the case i.e., computers with brand Mac or mac are displayed 
					findComputersByBrand(inventory, brandName);
					break;

				case 4:
					double value = 0;
					//bleow loop is run until a positive integer >=0 is entered
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
					//findCheaperThanPrice function is used to display all the computers less than or equal to specified price 
					findCheaperThanPrice(inventory, value);
					break;
				
				case 5:
					check = false;
					System.out.println("==============Thank you for visiting our store. Have a good day!=================");
					break;
				//if any number other than 1 to 5 is entered below default statement is printed
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

	// Function to verify the password, returns true is password is matched 
	public static boolean passwordChecker(String pswd) {
		Scanner sc = new Scanner(System.in);
		final String PASSWORD = "password";
		final int PASSWORDMAXCOUNT = 3;
		int pswdCount = 1;
		//while loop is run if wrong password is given until 3 times
		while (!pswd.equals(PASSWORD) && pswdCount != PASSWORDMAXCOUNT) {
			System.out.println(
					"Your password is Invalid, you've left with " + (PASSWORDMAXCOUNT - pswdCount) + " more tries\n");
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

	// Function to retrieve the computer based on the brand name, prints all the computers with specified brand name irrespective of the case
	public static void findComputersByBrand(Computer[] inventory, String brandName) {
		int createdComputers = Computer.findNumberOfCreatedComputers();
		int count = 0;
		int temp = 1;
		for (int i = 0; i < createdComputers; i++) {
			if (inventory[i].getBrand().equalsIgnoreCase(brandName)) {
				System.out.println("Computer: " + (temp++));
				inventory[i].displayComputer(inventory[i]);
				count++;
				System.out.println();
			}
		}
		//If there are no computers with specified brand name below statement is executed
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
		int temp =1;
		for (int i = 0; i < createdComputers; i++) {
			if (inventory[i].getPrice() <= price) {
				System.out.println("Computer: " + (temp++));
				inventory[i].displayComputer(inventory[i]);
				count++;
				System.out.println();
			}
		}
		//If there are no computers with specified price below statement is executed
		if (count == 0) {
			System.out.println("There are no computers under the specified price\n");
		}
	}

}
