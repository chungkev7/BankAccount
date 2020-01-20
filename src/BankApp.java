
/*
 * @Author: Kevin Chung
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class BankApp {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// Folder and file have already been created
//		createDir();
//		createOurFile();

		LinkedList<User> userList = new LinkedList<>();

		readFromFile(userList);

		int userCount = userList.size();
		boolean idExists = false;
		int userInput = 0;

		System.out.println("Hello! Welcome to the Bank.\n");

		while (userInput != 4) {

			System.out.println("What can we do for you today?");
			userInput = Validator.getInt(scan,
					"1. Deposit/Withdraw money\n2. Transfer money\n3. Sign up for an account\n4. That is all for today\n",
					1, 4);

			if (userInput == 1) {
				int userId = Validator.getInt(scan, "\nWhat is your ID?");
				for (User user : userList) {
					if (user.getId() == userId) {
						User currentUser = user;
						idExists = true;
						String securityCode = Validator.getString(scan,
								"\nFor security purposes, what is your security code?");
						if (securityCode.equals(currentUser.getSecurityCode())) {
							System.out.println("\nHello " + currentUser.getName() + "!. Here is your current balance:");
							System.out.println("Checkings: " + currentUser.getCheckingsAccount());
							System.out.println("Savings: " + currentUser.getSavingsAccount());
							int depositOrWithdraw = Validator.getInt(scan,
									"\nAre you depositing or withdrawing money? (1 for depositing, 2 for withdrawing):",
									1, 2);
							if (depositOrWithdraw == 1) {
								double depositAmount = Validator.getDouble(scan, "\nHow much are you depositing?");
								int checkingsOrSavings = Validator.getInt(scan,
										"\nWhich account are you depositing into?\n1. Checkings\n2. Savings\n3. Nevermind, I am not depositing any money.",
										1, 3);
								if (checkingsOrSavings == 1) {
									currentUser
											.setCheckingsAccount((currentUser.getCheckingsAccount() + depositAmount));
									System.out.println("\n" + depositAmount
											+ " has been deposited into your checkings! Your current checkings balance is "
											+ currentUser.getCheckingsAccount() + "\n");
									break;
								} else if (checkingsOrSavings == 2) {
									currentUser.setSavingsAccount(currentUser.getSavingsAccount() + depositAmount);
									System.out.println("\n" + depositAmount
											+ " has been deposited into your savings! Your current savings balance is "
											+ currentUser.getSavingsAccount() + "\n");
									break;
								} else {
									break;
								}
							} else if (depositOrWithdraw == 2) {
								double withdrawAmount = Validator.getDouble(scan, "\nHow much are you withdrawing?");
								int checkingsOrSavings = Validator.getInt(scan,
										"\nWhich account are you withdrawing from?\n1. Checkings\n2. Savings\n3. Nevermind, I am not withdrawing any money.",
										1, 3);
								if (checkingsOrSavings == 1) {
									if (withdrawAmount > currentUser.getCheckingsAccount()) {
										System.out.println("You cannot withdraw more than your current balance.");
										break;
									} else {
										currentUser.setCheckingsAccount(
												currentUser.getCheckingsAccount() - withdrawAmount);
										System.out.println("\nHere's " + withdrawAmount
												+ " from your checkings account. Your current checkings balance is "
												+ currentUser.getCheckingsAccount() + "\n");
										break;
									}
								} else if (checkingsOrSavings == 2) {
									if (withdrawAmount > currentUser.getSavingsAccount()) {
										System.out.println("You cannot withdraw more than your current balance.");
										break;
									} else {
										currentUser.setSavingsAccount(currentUser.getSavingsAccount() - withdrawAmount);
										System.out.println("\nHere's " + withdrawAmount
												+ " from your checkings account. Your current savings balance is "
												+ currentUser.getSavingsAccount() + "\n");
										break;
									}
								} else {
									break;
								}
							}
						} else {
							System.out.println("The security code does not match.\n");
							break;
						}
					} else {
						idExists = false;
					}
				}

				if (idExists == false) {
					System.out.println(
							"I'm sorry, the Id does not exist in our system. You will have to register for an account.\n");
				}

			} else if (userInput == 2) {
				int userId = Validator.getInt(scan, "\nWhat is your ID?");
				for (User user : userList) {
					if (user.getId() == userId) {
						User currentUser = user;
						idExists = true;
						String securityCode = Validator.getString(scan,
								"\nFor security purposes, what is your security code?");
						if (securityCode.equals(currentUser.getSecurityCode())) {
							System.out.println("\nHello " + currentUser.getName() + "!. Here is your current balance:");
							System.out.println("Checkings: " + currentUser.getCheckingsAccount());
							System.out.println("Savings: " + currentUser.getSavingsAccount());
							int transferChoice = Validator.getInt(scan,
									"Which account are we transferring from (1 for checkings to savings, 2 for savings to checkings):");
							if (transferChoice == 1) {
								double transferAmount = Validator.getDouble(scan,
										"How much would you like to transfer from your checkings to savings account?");
								if (transferAmount > currentUser.getCheckingsAccount()) {
									System.out.println("I'm sorry. " + transferAmount
											+ " is more than your current checkings balance.\n");
									break;
								} else {
									currentUser.setCheckingsAccount(currentUser.getCheckingsAccount() - transferAmount);
									currentUser.setSavingsAccount(currentUser.getSavingsAccount() + transferAmount);
									System.out.println(transferAmount
											+ " has been transferred from checkings to savings. Here is your current balance:");
									System.out.println("Checkings: " + currentUser.getCheckingsAccount());
									System.out.println("Savings: " + currentUser.getSavingsAccount());
									break;
								}
							} else if (transferChoice == 2) {
								double transferAmount = Validator.getDouble(scan,
										"How much would you like to transfer from your savings to checkings account?");
								if (transferAmount > currentUser.getSavingsAccount()) {
									System.out.println("I'm sorry. " + transferAmount
											+ " is more than your current savings balance.\n");
									break;
								} else {
									currentUser.setSavingsAccount(currentUser.getSavingsAccount() - transferAmount);
									currentUser.setCheckingsAccount(currentUser.getCheckingsAccount() + transferAmount);
									System.out.println(transferAmount
											+ " has been transfered from savings to checkings. Here is your current balance:");
									System.out.println("Checkings: " + currentUser.getCheckingsAccount());
									System.out.println("Savings: " + currentUser.getSavingsAccount());
									break;
								}
							} else {
								break;
							}
						} else {
							System.out.println("The security code does not match.\n");
							break;
						}
					} else {
						idExists = false;
					}
				}

				if (idExists == false) {
					System.out.println(
							"I'm sorry, the Id does not exist in our system. You will have to register for an account.\n");
				}

			} else if (userInput == 3) {
				String newUserName = Validator.getString(scan, "Excellent. What is your name?");
				String newUserCode = Validator.getString(scan,
						"What will be your security code? You will be asked for this everytime.");
				double newUserCheckings = Validator.getDouble(scan,
						"How much money are you depositing into your checkings account?");
				double newUserSavings = Validator.getDouble(scan,
						"Now how much money are you depositing into your savings account?");

				User newUser = new User(userCount + 1, newUserName, newUserCode, newUserCheckings, newUserSavings);
				userList.add(newUser);

				System.out.println("\nYour Id is " + newUser.getId() + ". Please remember it.");
				System.out.println("Thank you for joining!\n");
			} else {
				userInput = 4;
			}

		}

		System.out.println("Thank you for your business. Have a nice day!");

		writeToFile(userList);

		scan.close();
	}

	public static LinkedList<User> readFromFile(LinkedList<User> userList) {

		String fileName = "bankaccount.txt";
		Path path = Paths.get("src/resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
//				System.out.println(line);
				String[] userLines = line.split(",");
				User user = new User(Integer.parseInt(userLines[0]), userLines[1], userLines[2],
						Double.parseDouble(userLines[3]), Double.parseDouble(userLines[4]));
				userList.add(user);
				line = br.readLine();
			}

			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file");
		}

		return userList;
	}

	public static void writeToFile(LinkedList<User> userList) {

		String fileName = "bankaccount.txt";
		Path path = Paths.get("src/resources", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			// Set to true to append to end of file, set to false to overwrite
			output = new PrintWriter(new FileOutputStream(file, false));
			for (User user : userList) {
				output.println(user);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, contact customer service!");
		} finally {
			output.close();
		}
	}

	public static void createOurFile() {

		String fileName = "bankaccount.txt";

		Path path = Paths.get("src/resources", fileName);

		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
				System.out.println("The file was created successfully!");
			} catch (IOException e) {
				System.out.println("Something went terribly wrong!");
			}
		} else {
			System.out.println("The file already exists!");
		}

	}

	public static void createDir() {

		String dirPath = "src/resources";

		Path folder = Paths.get(dirPath);

		if (Files.notExists(folder)) {
			try {
				Files.createDirectories(folder);
				System.out.println("The file was created successfully!");
			} catch (IOException e) {
				System.out.println("Something went wrong with the folder creation");
			}
		} else {
			System.out.println("The folder already exists!");
		}

	}
}
