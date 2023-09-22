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

import jakarta.transaction.Transactional;

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
	@PostMapping("/")
	public Company addCompany(@RequestBody Company company) {
		System.out.println(company);
		if (company.getEmployees() == null && company.getEmployees().isEmpty())
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
		return employeeRepo.findByCompanyId(id);
	}

	// getting employee by company id
	@GetMapping("/{cId}/employees/{empId}")
	public Employee getEmployeeByCompanyId(@PathVariable("cId") Long cId,
			@PathVariable("empId") Long empId) {
		return employeeRepo.findByIdAndCompanyId(empId, cId);
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
	@DeleteMapping("/{cId}/employees/{empId}")
	public void deleteEmployeeByCompanyId(@PathVariable("cId") Long cId, @PathVariable("empId") Long empId) {
		employeeRepo.deleteByIdAndCompanyId(empId, cId);
	}
}
