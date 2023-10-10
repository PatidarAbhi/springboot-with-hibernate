	package com.innogent.abhi.dto;
	
	import java.time.LocalDate;
	
	public class EmployeeDTO {
		
		    private Long id;
		    private String name;
		    private LocalDate doj;
		    private Long companyId;
		    private String companyName;
	
		    // Constructors, getters, and setters
	
		    public EmployeeDTO(Long id, String name, LocalDate doj, Long companyId, String companyName) {
		        this.id = id;
		        this.name = name;
		        this.doj = doj;
		        this.companyId = companyId;
		        this.companyName = companyName;
		    }
	
		    // Other constructors if needed
	
		    public EmployeeDTO() {
		    }
	
		    // Getters and setters for all fields
	
		    public Long getId() {
		        return id;
		    }
	
		    public void setId(Long id) {
		        this.id = id;
		    }
	
		    public String getName() {
		        return name;
		    }
	
		    public void setName(String name) {
		        this.name = name;
		    }
	
		    public LocalDate getDoj() {
		        return doj;
		    }
	
		    public void setDoj(LocalDate doj) {
		        this.doj = doj;
		    }
	
		    public Long getCompanyId() {
		        return companyId;
		    }
	
		    public void setCompanyId(Long companyId) {
		        this.companyId = companyId;
		    }
	
		    public String getCompanyName() {
		        return companyName;
		    }
	
		    public void setCompanyName(String companyName) {
		        this.companyName = companyName;
		    }
	
		    @Override
		    public String toString() {
		        return "EmployeeDTO{" +
		                "id=" + id +
		                ", name='" + name + '\'' +
		                ", doj=" + doj +
		                ", companyId=" + companyId +
		                ", companyName='" + companyName + '\'' +
		                '}';
		    }
		}
	
	
	
