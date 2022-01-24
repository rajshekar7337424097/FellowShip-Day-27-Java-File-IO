package com.bl.emppayroll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
	enum IOStream{
		CONSOLE_IO, FILE_IO,
	}
	
	List<Employee> serviceList;
	
	public EmployeePayrollService() {
		serviceList = new ArrayList<>();
	}
	
	public  EmployeePayrollService(Employee[] empsData) {
		serviceList = Arrays.asList(empsData);
	}
	
	public static void main(String[] args) {
		EmployeePayrollService service = new EmployeePayrollService();
		service.readEmpData(IOStream.CONSOLE_IO);
		service.writeEmpData(IOStream.CONSOLE_IO);
	}
	
	public void writeEmpData(IOStream ioStream) {
		if (ioStream.equals(IOStream.CONSOLE_IO)) {
			System.out.println("Employee payroll details" + serviceList);
		} else if(ioStream.equals(IOStream.FILE_IO)) {
			EmployeePayRollFile empService = new EmployeePayRollFile();
			empService.writeEmpData(serviceList);
		}		
	}

	public void readEmpData(IOStream ioStream) {
		if (ioStream.equals(IOStream.CONSOLE_IO)) {
			Scanner consoleScn = new Scanner(System.in);
			System.out.println("Enter the id");
			int id = consoleScn.nextInt();
			
			System.out.println("Enter the name");
			String name = consoleScn.next();
			
			System.out.println("Enter Salary");
			double salary = consoleScn.nextDouble();
			
			serviceList.add(new Employee(id, name, salary));
			
			consoleScn.close();
		} else if(ioStream.equals(IOStream.FILE_IO)) {
			EmployeePayRollFile empService = new EmployeePayRollFile();
			empService.readEmpData();
		}
	}
		
		public long countEntries() {
			long count = 0;
			EmployeePayRollFile employeePayrollFile = new EmployeePayRollFile();
			count = employeePayrollFile.countLines();
			return  count;
	}
}
