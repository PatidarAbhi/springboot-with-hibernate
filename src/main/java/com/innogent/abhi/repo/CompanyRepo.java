package com.innogent.abhi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innogent.abhi.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

}
