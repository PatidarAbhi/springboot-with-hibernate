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

	/**
	 * For getting list of companies
	 * 
	 * @return List<Company>
	 */
	@GetMapping("/")
	public List<Company> getAllComapny() {
		return companyRepo.findAll();
	}

	// for getting company by id
	@GetMapping("/{id}")
	public Company getCompnyById(@PathVariable Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;
	}

	// for add new company
	@PostMapping("/")
	public Company addCompany(@RequestBody Company company) {
		if (company.getEmployees().isEmpty())
			return companyRepo.save(company);
		else {
			List<Employee> employee = company.getEmployees();
			employee.stream().forEach(e -> e.setCompany(company));
			company.setEmployees(employee);
			return companyRepo.save(company);
		}
	}

	// delete company by id
	@DeleteMapping("/{id}")
	public String deleteCompnyById(@PathVariable Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			companyRepo.deleteById(id);
			return "successfully deleted";
		} else
			return "company does not exist";
	}

	// update company
	@PutMapping("/")
	public Company updateCompnay(@RequestBody Company company) {
		if (company.getEmployees() == null) {
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

	// getting list of employee by company id
	@GetMapping("/{id}/employees")
	public List<Employee> getAllEmployeeByCompanyId(@PathVariable Long id) {
		Optional<Company> findById = companyRepo.findById(id);
		if (findById.isPresent()) {
			Company company = findById.get();
			return company.getEmployees();
		} else
			return null;
	}

	// getting employee by company id
	@GetMapping("/{id}/employees/{empId}")
	public Employee getEmployeeByCompanyId(@PathVariable("id") Long id, @PathVariable("empId") Long empId) {
		Optional<Company> findById = companyRepo.findById(id);
		if (findById.isPresent()) {
			Company company = findById.get();
			System.out.println(company);
			Optional<Employee> emps = company.getEmployees().stream().filter(a -> a.getId().equals(empId)).findAny();
			if (emps.isPresent()) {
				return emps.get();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// add employee by company id
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

	// delete employee by company id
	@DeleteMapping("/{id}/employees/{empId}")
	public String deleteEmployeeByCompanyId(@PathVariable("id") Long id, @PathVariable("empId") Long empId) {
		Optional<Company> comp = companyRepo.findById(id);
		if (comp.isPresent()) {
			List<Employee> empList = comp.get().getEmployees();
			Optional<Employee> emp = employeeRepo.findById(empId);
			if (emp.isPresent()) {
				if (empList.contains(emp.get())) {
					empList.remove(emp.get());
					employeeRepo.deleteById(emp.get().getId());
					return "Employee deleted successfully";
				} else {
					return "Employee does not exist in this company";
				}
			} else {
				return "Employee does not exist";
			}
		} else {
			return "Company does not exist";
		}

	}
}
