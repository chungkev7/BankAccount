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
		boolean idExists = true;
		int userCount = 1;
		
		userList.add(new User("Test", "Test", 50.05, 10));
		userList.get(0).setId(1);
		
		System.out.println("Hello! Welcome to the Bank. What can we do for you today?\n");
		
		int userInput = Validator.getInt(scan, "1. Deposit/Withdraw money\n2. Transfer money\n3. Sign up for an account\n4. That is all for today.", 1, 4);
		
		if (userInput == 1) {
			int userId = Validator.getInt(scan, "\nWhat is your ID?");	
				for (User user : userList) {
					if(user.getId() == userId) {
						User currentUser = user;
						String securityCode = Validator.getString(scan, "\nFor security purposes, what is your security code?");
						if (securityCode.equalsIgnoreCase(currentUser.getSecurityCode())) {
							System.out.println("\nHere is your current balance:");
							System.out.println("Checkings: " + currentUser.getCheckingsAccount());
							System.out.println("Savings: " + currentUser.getSavingsAccount());
							int depositOrWithdraw = Validator.getInt(scan, "\nAre you depositing or withdrawing money? (1 for depositing, 2 for withdrawing):", 1, 2);
								if (depositOrWithdraw == 1) {
									double depositAmount = Validator.getDouble(scan, "\nHow much are you depositing?");
									int checkingsOrSavings = Validator.getInt(scan, "\nWhich account are you depositing into?\n1. Checkings\n2. Savings\n3. Nevermind, I am not depositing any money.", 1, 3);
										if (checkingsOrSavings == 1) {
											currentUser.setCheckingsAccount((currentUser.getCheckingsAccount() + depositAmount));
											System.out.println("\n" + depositAmount + " has been deposited into your checkings! Your current checkings balance is " + currentUser.getCheckingsAccount());
										} else if (checkingsOrSavings == 2) {
											currentUser.setSavingsAccount(currentUser.getSavingsAccount() + depositAmount);
											System.out.println("\n" + depositAmount + " has been deposited into your savings! Your current savings balance is " + currentUser.getSavingsAccount());
										} else {
											break;
										}
								} else if (depositOrWithdraw == 2) {
									double withdrawAmount = Validator.getDouble(scan, "\nHow much are you withdrawing?");
									int checkingsOrSavings = Validator.getInt(scan, "\nWhich account are you withdrawing from?\n1. Checkings\n2. Savings\n3. Nevermind, I am not withdrawing any money.", 1, 3);
										if(checkingsOrSavings == 1) {
											if (withdrawAmount > currentUser.getCheckingsAccount()) {
												System.out.println("You cannot withdraw more than your current balance.");
											} else {
												currentUser.setCheckingsAccount(currentUser.getCheckingsAccount() - withdrawAmount);
												System.out.println("\nHere's " + withdrawAmount + " from your checkings account. Your current checkings balance is " + currentUser.getCheckingsAccount());
											}
										} else if (checkingsOrSavings == 2) {
											if (withdrawAmount > currentUser.getSavingsAccount()) {
												System.out.println("You cannot withdraw more than your current balance.");
											} else {
												currentUser.setSavingsAccount(currentUser.getSavingsAccount() - withdrawAmount);
												System.out.println("\nHere's " + withdrawAmount + " from your checkings account. Your current savings balance is " + currentUser.getSavingsAccount());
											}											
										}
								}
						} else {
							System.out.println("The security code does not match.");
						}
					} else {
						idExists = false;
					}
				}
		} else if (userInput == 3) {
			String newUserName = Validator.getString(scan, "Excellent. What is your name?");
			String newUserCode = Validator.getString(scan, "What will be your security code? You will be asked for this everytime.");
			double newUserCheckings = Validator.getDouble(scan, "How much money are you depositing into your checkings account?");
			double newUserSavings = Validator.getDouble(scan, "Now how much money are you depositing into your savings account?");
			
			User newUser = new User(newUserName, newUserCode, newUserCheckings, newUserSavings);
			newUser.setId(userCount);
			userCount++;
			
			System.out.println("Thank you for joining!");
		}
		
		
		scan.close();
	}
	
	public static void readFromFile() {

		String fileName = "bankaccount.txt";
		Path path = Paths.get("src/resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file");
		}

	}

	public static void writeToFile(User user) {

		String fileName = "bankaccount.txt";
		Path path = Paths.get("src/resources", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			// Set to true to append to end of file, set to false to overwrite
			output = new PrintWriter(new FileOutputStream(file, true));
			output.println(user);
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
