package com.innogent.abhi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.innogent.abhi.dto.EmployeeDTO;
import com.innogent.abhi.entity.Employee;



@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>
{
    public Employee findByIdAndCompanyId(Long empid,long cId);	
    public List <Employee> findByCompanyId(long cId);
 
    @Transactional
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = :empId AND e.company.id = :cId")
    public void deleteByIdAndCompanyId(Long empId, long cId);
    
  
    
       @Query("SELECT NEW com.innogent.abhi.dto.EmployeeDTO(e.id, e.name, e.doj, c.id, c.name) " +
            "FROM Employee e LEFT JOIN e.company c")
         List<EmployeeDTO> findAllEmployeeDTOs();
       
       
       @Query("SELECT NEW com.innogent.abhi.dto.EmployeeDTO(e.id, e.name, e.doj, c.id, c.name) " +
    	        "FROM Employee e LEFT JOIN e.company c " +
    	        "WHERE c.id = :companyId")
    	List<EmployeeDTO> findEmployeeDTOsByCompanyId(@Param("companyId") Long companyId);
       
       
       @Query("SELECT NEW com.innogent.abhi.dto.EmployeeDTO(e.id, e.name, e.doj, c.id, c.name) " +
   	        "FROM Employee e LEFT JOIN e.company c " +
   	        "WHERE c.id = :companyId AND e.id=:empId")
         EmployeeDTO findEmployeeDTOByIdAndCompnayId(
    		   @Param("empId") Long empId,@Param("companyId") Long companyId);
       
       
//       @Transactional
//       @Modifying
//       @Query("UPDATE Employee e SET e.name=:name WHERE e.id=:id")
//       public int updateEmployeeById(@PathVariable("id") int id,@PathVariable("name") String name);
       
        
       
       
       //we need to put @Transactional annotaion  
       @Transactional
       public int deleteByName(String name);
       
}
