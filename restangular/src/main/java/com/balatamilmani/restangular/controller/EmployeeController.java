package com.balatamilmani.restangular.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balatamilmani.restangular.messaging.JmsQueueSender;
import com.balatamilmani.restangular.model.Address;
import com.balatamilmani.restangular.model.Employee;

@RestController
@RequestMapping(value = { "employeeService" })
public class EmployeeController {

	@Autowired
	private JmsQueueSender jmsQueueSender;
	
	private static final List<Employee> employeeList = new ArrayList<Employee>();
	
	/**
	 * @param employee Save the employee Object in memory
	 * @return success message
	 */
	@RequestMapping(produces="text/plain", consumes="application/json", method=RequestMethod.POST )
	public String addEmployee(@RequestBody Employee employee){
		System.out.println(employee);
		employeeList.add(employee);
		jmsQueueSender.simpleSend("hello queue world");
		return "Employee added successfully";
	}
	
	
	@RequestMapping(value="/sendMsgToQueue/{jobId}")
	public String sendMsgToQueue(@PathVariable String jobId){
		jmsQueueSender.simpleSend(jobId);
		return "message sent successfully";
	}
	/**
	 * @return all the employees
	 */
	@RequestMapping(produces = "application/json", method=RequestMethod.GET)
	public List<Employee> getAllEmployee() {
		return employeeList;
	}	
	
	/**
	 * @return One employee
	 */
	@RequestMapping(value="getEmployee", produces = "application/json")
	public Employee getEmployee() {
		Employee employee = null;
		Address address = null;

		employee = new Employee();
		address = new Address();

		address.setStreet("13653 Lee Jackson Memorial Hwy");
		address.setCity("Chantilly");
		address.setState("VA");
		address.setZip("20151");

		employee.setId("1111");
		employee.setFirstName("Balamurugan");
		employee.setLastName("Tamilmani");
		employee.setAddress(address);

		return employee;
	}

	
	/**
	 * Initializes Two employees
	 */
	@PostConstruct
	public void init() {
		Employee employee = null;
		Address address = null;


		// Employee 1
		employee = new Employee();
		address = new Address();

		address.setStreet("13653 Lee Jackson Memorial Hwy");
		address.setCity("Chantilly");
		address.setState("VA");
		address.setZip("20151");

		employee.setId("1111");
		employee.setFirstName("Balamurugan");
		employee.setLastName("Tamilmani");
		employee.setAddress(address);

		employeeList.add(employee);

		// Employee 2
		employee = new Employee();
		address = new Address();

		address.setStreet("2571 John Milton Drive Herndon, VA ");
		address.setCity("Herndon");
		address.setState("VA");
		address.setZip("20171");

		employee.setId("2222");
		employee.setFirstName("John");
		employee.setLastName("Smith");
		employee.setAddress(address);

		employeeList.add(employee);
	}
}
