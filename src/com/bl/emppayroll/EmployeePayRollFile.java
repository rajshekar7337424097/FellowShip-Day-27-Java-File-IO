package com.bl.emppayroll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayRollFile {

	public static String EMP_FILE_PATH = "emp_payroll_data.txt";

	public void writeEmpData(List<Employee>empList) {
		StringBuffer empBuffer = new  StringBuffer();
		empList.forEach(emp ->{
			empBuffer.append(emp + "\n");
		});
		System.out.println("Write data from file");
		try {
			Files.write(Paths.get(EMP_FILE_PATH), empBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countLines() {
		long count = 0;
		try {
			count = Files.lines(Paths.get(EMP_FILE_PATH)).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static List<Employee> getEmpPayRollData(){
		System.out.println("Storing emp Date from file to obj");
		List<Employee> empDataFromFile = new ArrayList<>();

		try {
			Files.lines(Paths.get(EMP_FILE_PATH)).map(line ->{
				int id = Integer.parseInt(line.split(",")[0].split(":")[1]);
				String name = line.split(",")[1].split(":")[1];
				double salary = Double.parseDouble(line.split(",")[2].split(":")[1]);
				Employee emp = new Employee(id, name, salary);
				return emp;
			}).forEach(emp->empDataFromFile.add(emp));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empDataFromFile;
	}

	public void readEmpData() {
		
		try {
			System.out.println("Read Data From File");
			Files.lines(Paths.get(EMP_FILE_PATH)).map(line-> line.trim()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
