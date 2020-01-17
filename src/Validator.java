import java.util.Scanner;

public class Validator {

	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scan, String prompt) {
		// This approach use "hasNext" look ahead.
		System.out.print(prompt);
		while (!scan.hasNextInt()) {
			scan.nextLine(); // clear bad line.
			System.out.println("\nSorry, I can't read that. Enter a whole number, using digits.\n");
			System.out.println(prompt);
		}
		int result = scan.nextInt();
		scan.nextLine(); // clear for next line of input.
		return result;
	}

	/**
	 * Get any valid double.
	 */
	public static double getDouble(Scanner scan, String prompt) {
		// This approach use "hasNext" look ahead.
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = scan.hasNextDouble();
			if (!isValid) {
				scan.nextLine(); // clear bad line.
				System.out.println("\nSorry, I can't read that. Enter a number, using digits.\n");
			}
		} while (!isValid);

		double number = scan.nextDouble();
		scan.nextLine(); // clear for next line of input.
		return number;
	}

	/**
	 * Get any string.
	 */
	public static String getString(Scanner scan, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		return scan.nextLine();
	}

	/**
	 * Get any valid integer between min and max.
	 */
	public static int getInt(Scanner scan, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getInt(scan, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("\nThe number must be at least " + min + ".\n");
			} else if (number > max) {
				isValid = false;
				System.out.println("\nThe number must not be larger than " + max + ".\n");
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get any valid double between min and max.
	 */
	public static double getDouble(Scanner scan, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = getDouble(scan, prompt);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scan, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = getString(scan, prompt);

			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input must match the appropriate format.");
				isValid = false;
			}

		} while (!isValid);
		return input;
	}

}