package com.innogent.abhi.controller;

import java.util.ArrayList;
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

import com.innogent.abhi.dto.EmployeeDTO;
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

	/**
	 * For Getting List Of Companies
	 * 
	 * @return List<Company>
	 */
	@GetMapping("/")
	public List<Company> getAllComapny() {
		System.out.println("companies::" + companyRepo.findAll());
		return companyRepo.findAll();
	}

	/**
	 * For Getting Company By Id
	 * 
	 * @return Company
	 */
	@GetMapping("/{id}")
	public Company getCompnyById(@PathVariable Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;
	}

	/**
	 * For Adding New Company
	 * 
	 * @return Company
	 */
	@PostMapping("/addComp")
	public Company addCompany(@RequestBody Company company) {
		System.out.println(company);
		if (company.getEmployees() == null || company.getEmployees().isEmpty())
			return companyRepo.save(company);
		else {
			List<Employee> employee = company.getEmployees();
			employee.stream().forEach(e -> e.setCompany(company));
			company.setEmployees(employee);
			return companyRepo.save(company);
		}
	}

	/**
	 * For Delete Company By Id
	 * 
	 * @return String
	 */
	@DeleteMapping("/{id}")
	public String deleteCompnyById(@PathVariable Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			companyRepo.deleteById(id);
			return "successfully deleted";
		} else
			return "company does not exist";
	}

	/**
	 * For Update Company
	 * 
	 * @return Company
	 */
	@PutMapping("/")
	public Company updateCompnay(@RequestBody Company company) {
		if (company.getEmployees() == null || company.getEmployees().isEmpty()) {
			List<Employee> employees = companyRepo.findById(company.getId()).get().getEmployees();
			company.setEmployees(employees);
			return companyRepo.save(company);
		} else {
			List<Employee> employees = company.getEmployees();
			if (employees != null) {
				employees.stream().forEach(e -> e.setCompany(company));
				company.setEmployees(employees);
				return companyRepo.save(company);
			}
			return companyRepo.save(company);
		}
	}

	/**
	 * For Getting list of Employee By Company Id
	 * 
	 * @return List<Employee>
	 */
	@GetMapping("/{id}/employees")
	public List<EmployeeDTO> getAllEmployeeByCompanyId(@PathVariable Long id) {
		/*
		 * List<Employee> employees = employeeRepo.findByCompanyId(id);
		 * List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		 * System.out.println("normal employe::" + employees); ; for (Employee employee
		 * : employees) { EmployeeDTO employeeDTO = new EmployeeDTO();
		 * employeeDTO.setId(employee.getId()); employeeDTO.setName(employee.getName());
		 * employeeDTO.setDoj(employee.getDoj());
		 * 
		 * // Map company data to the DTO Company company = employee.getCompany(); if
		 * (company != null) { employeeDTO.setCompanyId(company.getId());
		 * employeeDTO.setCompanyName(company.getName()); }
		 * 
		 * employeeDTOs.add(employeeDTO); } System.out.println("employee" +
		 * employeeDTOs);
		 * 
		 * return employeeDTOs;
		 */	
		
		System.out.println("dtos");
		return employeeRepo.findEmployeeDTOsByCompanyId(id);
		
	}

	/**
	 * For Getting Employee By Id And Company Id
	 * 
	 * @return Employee
	 */
	@GetMapping("/{cId}/employees/{empId}")
	public EmployeeDTO getEmployeeByCompanyId(@PathVariable("cId") Long cId,
			@PathVariable("empId") Long empId) {
		return employeeRepo.findEmployeeDTOByIdAndCompnayId(empId, cId);
	}

	/**
	 * For Adding Employee By Company Id
	 * 
	 * @return Employee
	 */
	@PostMapping("/{id}/employees")
	public Employee addEmployeeByCompanyId(@PathVariable("id") Long id, @RequestBody Employee employee) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			Company company = optional.get();
			employee.setCompany(company);
			company.getEmployees().add(employee);
			companyRepo.save(company);
			return employee;
		}
		return null;
	}

	/**
	 * For Delete Employee By Id And Company Id
	 * 
	 * @return Employee
	 */
	@DeleteMapping("/{cId}/employees/{empId}")
	public void deleteEmployeeByCompanyId(@PathVariable("cId") Long cId, @PathVariable("empId") Long empId) {
		employeeRepo.deleteByIdAndCompanyId(empId, cId);
	}

	/**
	 * For find Employees
	 * 
	 * @return List<Employee>
	 */
	/*
	 * @GetMapping("getEmp") public List<Employee> getAllEmployee() { return
	 * employeeRepo.findAll(); }
	 */
		@GetMapping("getEmp")
		public List<EmployeeDTO> getAllEmployee() {
			
			return employeeRepo.findAllEmployeeDTOs();
	}

	/**
	 * For delete Employee
	 * 
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public String deletEmployeeById(@PathVariable Long id) {
		Optional<Employee> emp = employeeRepo.findById(id);
		if (emp.isPresent()) {
			employeeRepo.delete(emp.get());
			return "succsess";
		} else {
			return "fail";
		}
	}

	
	@PutMapping("/update")
	public Employee updateEmp(@RequestBody Employee emp) {
		System.out.println("CompanyController.updateEmp()");
		System.out.println("emp is" + emp);
		Employee existingEmployee = employeeRepo.findById(emp.getId()).get();
		System.out.println(existingEmployee);
		if (existingEmployee != null) {
			Company existingCompany = existingEmployee.getCompany();
			emp.setCompany(existingCompany);
			return employeeRepo.save(emp);
		} else
			return null;
	}
	
	
	  @DeleteMapping("/byName/{id}") 
	  public void deleteByName(@PathVariable Long id) 
	  { 
		  //here we do not need any @Teransaction annotaion
		 //employeeRepo.deleteById(id);
	  }
	 

}
