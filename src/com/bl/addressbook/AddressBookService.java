package com.bl.addressbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
	public static String address_DATA_FILE_PATH = "AddressBook.txt";
	
	public void writeAddressBookDataData(List<AddressBook> addressBookList) {
		StringBuffer addressbookBuffer = new StringBuffer();
		addressBookList.forEach(ab -> {
			addressbookBuffer.append(ab+"\n");
		});
		try {
			Files.write(Paths.get(address_DATA_FILE_PATH), addressbookBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long countLines() {
		long count = 0;
		try {
			count = Files.lines(Paths.get(address_DATA_FILE_PATH)).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void readEmpPayRollData() {
		System.out.println("Reading address book data from the file");
		try {
			Files.lines(Paths.get(address_DATA_FILE_PATH)).map(line -> line.trim()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Contact> getAddressBookData(){
		System.out.println("Storing emp Payroll data from the file to adj");
		List<Contact> addressDataFromFile = new ArrayList<>();
		try {
			Files.lines(Paths.get(address_DATA_FILE_PATH)).map(line ->{
				String firstName = line.split(",")[0].split("=")[1];
				String lastName = line.split(",")[1].split("=")[1];
				String phoneNumber = line.split(",")[2].split("=")[1];
				String email = line.split(",")[3].split("=")[1];
				String city = line.split(",")[4].split("=")[1];
				int zip = Integer.parseInt(line.split(",")[5].split("=")[1]);
				String state = line.split(",")[6].split("=")[1];
				
				Contact ab = new Contact();
				return ab;
				
			}).forEach(ab -> addressDataFromFile.add(ab));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressDataFromFile;
	}
}
