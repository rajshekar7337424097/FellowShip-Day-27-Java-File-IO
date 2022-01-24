package com.bl.emppayroll;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bl.emppayroll.EmployeePayrollService.IOStream;

public class EmployeePayRollFileTest {
	
	@Test
	public void test() {
		Employee[] empsData = {
				new Employee(1, "Jeff Bezos", 100000),
				new Employee(2, "Mark Zukerberg", 200000),
				new Employee(3, "Bill Gates", 400000),
				new Employee(3, "Elon Musk", 300000),};
		EmployeePayrollService employeePayRollService = new EmployeePayrollService(empsData);
		employeePayRollService.readEmpData(IOStream.FILE_IO);
		employeePayRollService.writeEmpData(IOStream.FILE_IO);
		long  count = employeePayRollService.countEntries();
		System.out.println("Number of Entries :" + count);
		assertEquals(4, count);
		}
	
	@Test
	public void getEmployeeDataFromFile() {
		EmployeePayRollFile employeePayrollFile = new EmployeePayRollFile();
		System.out.println(EmployeePayRollFile.getEmpPayRollData().size());
		int lenght = EmployeePayRollFile.getEmpPayRollData().size();
		System.out.println(lenght);
		assertEquals(4, lenght);
	}
}
