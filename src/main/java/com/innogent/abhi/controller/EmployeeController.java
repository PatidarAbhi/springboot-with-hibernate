package com.innogent.abhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innogent.abhi.entity.Employee;
import com.innogent.abhi.repo.EmployeeRepo;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeRepo;

	public EmployeeController() {
		// TODO Auto-generated constructor stubtr
		System.out.println("EmployeeController.EmployeeController()");
	}

	@GetMapping("/")
	public List<Employee> getAllEmployee() {
		System.out.println("Inside getAll");
		System.out.println(employeeRepo.findAll());
		return employeeRepo.findAll();
	}

	@PostMapping("/")
	public Employee addEmployee(Employee employee) {
		System.out.println("EmployeeController.addEmployee()");
		Employee emp = employeeRepo.save(employee);
		System.out.println(emp);
		return emp;
	}

}
