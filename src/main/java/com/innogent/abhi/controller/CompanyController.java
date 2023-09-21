package com.innogent.abhi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innogent.abhi.entity.Company;
import com.innogent.abhi.entity.Employee;
import com.innogent.abhi.repo.CompanyRepo;
import com.innogent.abhi.repo.EmployeeRepo;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	public CompanyController() {
		// TODO Auto-generated constructor stubtr
		System.out.println("CompanyController.CompanyController()");
	}

	@GetMapping("/")
	public List<Company> getAllComapny() {
		return companyRepo.findAll();
	}

	@PostMapping("/")
	public Company addCompany(@RequestBody Company company) {
		System.out.println("EmployeeController.addEmployee()");
		if (company.getEmployees().isEmpty())
			return companyRepo.save(company);
		else {
			List<Employee> employee = company.getEmployees();
			employee.stream().forEach(e -> e.setCompany(company));
			System.out.println(employee);
			company.setEmployees(employee);
			return companyRepo.save(company);
		}
	}

	@GetMapping("/{id}")
	public Company getCompnyById(@PathVariable Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;
	}

	@DeleteMapping("/{id}")
	public void deleteCompnyById(@PathVariable Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			companyRepo.deleteById(id);
		}
	}

	@PutMapping("/")
	public Company updateCompnyById(@RequestBody Company comp) {
		return companyRepo.save(comp);
	}

	// employee functions
	@GetMapping("/getemp")
	public List<Employee> getAllEmployee() {
		System.out.println("Inside getAll");
		System.out.println(employeeRepo.findAll());
		return employeeRepo.findAll();
	}

	@PostMapping("/addemp")
	public Employee addEmployee(Employee employee) {
		System.out.println("EmployeeController.addEmployee()");
		Employee emp = employeeRepo.save(employee);
		System.out.println(emp);
		return emp;
	}

	@GetMapping("/{id}/employees")
	public List<Employee> getAllEmployeeByCompanyId(@PathVariable Long id) {
		Optional<Company> findById = companyRepo.findById(id);
		if (findById.isPresent()) {
			Company company = findById.get();
			return company.getEmployees();
		} else
			return null;
	}

}
