package com.bl.addressbook;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressBook {
	static List<Contact> addressBook;

	public static void main(String[] args) {
		addressBook = new LinkedList<Contact>();

		boolean isExit = false;

		System.out.println("welcome to the address book, Manage your Contacts" + "with the address book");
		Scanner sc = new Scanner(System.in);
		while(!isExit) {
			System.out.println("Select the option from below");
			if(addressBook.isEmpty()) {
				System.out.println("1.Add contact" + "\n5.Exit");
			}else {
				System.out.println("1.Add contact"+"\n2. display Contact\n3.Edit Contact"+"\n4.Delete contact\n5.Exit");
			}
			String option = sc.nextLine();

			switch(option) {
			case "1":
				addContact(sc);
				break;
			case "2":
				showContact();
				break;
			case "3":
				editContact(sc);
				break;
			case "4":
				deleteContact(sc);
				break;
			case "5":
				isExit = true;
				showContact();
				break;
			default:
				break;

			}
		}
		sc.close();
	}

	private static String validateZip(String zip, Scanner sc) {
		String resultPattern = "^[0-9]{6}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(zip);
		
		while (!inputMatcher.matches()) {
			System.out.println("Error: Invalid Zip code, please try again ");
			zip = sc.nextLine();
			inputMatcher = regex.matcher(zip);
		}
		return zip;
	}

	private static String validateState(String state, Scanner sc) {
		String resultPattern = "^[A-Z]{1}[a-z]{2,9}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(state);
		
		while (!inputMatcher.matches()) {
			System.out.println("Error: Invalid city name, please try again");
			System.out.println("Length must not exceeds 10 (ex:Hyderabad");
			state = sc.nextLine();
			inputMatcher = regex.matcher(state);
		}
		return state;
	}

	private static String validateCity(String city, Scanner sc) {
		String resultPattern = "^[A-Z]{1}[a-z]{2,9}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(city);
		
		while (!inputMatcher.matches()) {
			System.out.println("Error: Invalid city name, please try again");
			System.out.println("Length must not exceeds 10 (ex:Hyderabad");
			city = sc.nextLine();
			inputMatcher = regex.matcher(city);
		}
		return city;
	}

	private static String validatePhone(String phoneNumber, Scanner sc) {
		String resultPattern = "^[+]{0,1}[0-9]{0,2}[0-9]{10}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(phoneNumber);
		
		while (!inputMatcher.matches()) {
			System.out.println("Error : Invalid phone number, please try again");
			phoneNumber = sc.nextLine();
			inputMatcher = regex.matcher(phoneNumber);
		}
		return phoneNumber;
	}

	private static String validateEmail(String email, Scanner sc) {
		String resultPattern = "^[a-z]{2,30}@{1}[a-z]{3,10}.[a-z]{3}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(email);
		
		while (!inputMatcher.matches()) {
			System.out.println("Error: invalid email, please try again");
			email = sc.nextLine();
			inputMatcher = regex.matcher(email);
		}
		return email;
	}

	private static String validateLastName(String lastName, Scanner sc) {
		String resultPattern = "^[A-Z]{1}[a-z]{3,9}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(lastName);
		
		while(!inputMatcher.matches()) {
			System.out.println("Error: Invalid lastName, please try again");
			System.out.println("Lenght must not exceed 10(ex.Reddy");
			lastName = sc.nextLine();
			inputMatcher = regex.matcher(lastName);
		}
		return lastName;
	}

	private static String validateFirstName(String firstName, Scanner sc) {
		String resultPattern = "^[A-Z]{1}[a-z]{3,9}$";
		Pattern regex = Pattern.compile(resultPattern);
		Matcher inputMatcher = regex.matcher(firstName);
		
		while(!inputMatcher.matches()) {
			System.out.println("Error: Invalid first name, please try again");
			System.out.println("Length must not exceeds 10(ex : Rajshekar");
			firstName = sc.nextLine();
			inputMatcher = regex.matcher(firstName);
		}
		return firstName;
	}
	
	private static void showContact() {
		if (addressBook.isEmpty()) {
			System.out.println("Address Book is empty.");
		} else {
			for (Contact contact : addressBook) {
				System.out.println(contact);
			}
		}
	}

	private static void deleteContact(Scanner sc) {
		System.out.println("Which contact you want to delete? (Enter the first Name");
		String firstName = sc.nextLine();
		
		Contact deleteContact = null;
		for (int i = 0; i < addressBook.size(); i++) {
			if (firstName.equals(addressBook.get(i).getFirstName())) {
				deleteContact = addressBook.remove(i);
			}
		}
		
		if (deleteContact == null) {
			System.out.println("no Contact found with name"+firstName);
		} else {
			System.out.println(deleteContact.getFirstName()+"Contact has been removed from your addressBook.");
		}
	}

	private static void editContact(Scanner sc) {
		System.out.println("which contact you want to edit?(Enter first name");
		String firstName = sc.nextLine();

		Contact editContact = null;
		for(int i = 0; i < addressBook.size(); i++) {
			if(firstName.equals(addressBook.get(i).getFirstName())) {
				editContact = addressBook.get(i);
			}
		}

		if(editContact == null) {
			System.out.println("No contact found with name" + firstName);
		}else {
			editContactDetails(editContact, sc);
		}

	}

	private static void editContactDetails(Contact editContact, Scanner sc) {
		System.out.println("Enter First name :");
		String firstName = sc.nextLine();
		editContact.setFirstName(validateFirstName(firstName, sc));

		System.out.println("Enter your email :");
		String email = sc.nextLine();
		editContact.setEmail(validateEmail(email, sc));

		System.out.println("Enter phoneNumber :");
		String phoneNumber = sc.nextLine();
		editContact.setPhoneNumber(validatePhone(phoneNumber, sc));

		System.out.println("Contact has been edited.");
	}

	private static void addContact(Scanner sc) {
		Contact contact = new Contact();

		System.out.println("Enter first name");
		String firstName = sc.nextLine();
		contact.setFirstName(validateFirstName(firstName, sc));

		System.out.println("Enter Last name");
		String lastName = sc.nextLine();
		contact.setLastName(validateLastName(lastName, sc));

		System.out.println("Enter your email");
		String email = sc.nextLine();
		contact.setEmail(validateEmail(email, sc));

		System.out.println("Enter phone number");
		String phoneNumber = sc.nextLine();
		contact.setPhoneNumber(validatePhone(phoneNumber, sc));

		System.out.println("Enter city name");
		String city = sc.nextLine();
		contact.setCity(validateCity(city, sc));

		System.out.println("Enter state name");
		String state = sc.nextLine();
		contact.setState(validateState(state, sc));

		System.out.println("Enter zip code");
		String zip = sc.nextLine();
		contact.setZip(validateZip(zip, sc));

		addressBook.add(contact);
		System.out.println("Contacts have been saved");
	}
}
