package com.innogent.abhi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.innogent.abhi.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>
{
    public Employee findByIdAndCompanyId(Long empid,long cId);	
    public List <Employee> findByCompanyId(long cId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = :empId AND e.company.id = :cId")
    public void deleteByIdAndCompanyId(Long empId, long cId);
    

}
