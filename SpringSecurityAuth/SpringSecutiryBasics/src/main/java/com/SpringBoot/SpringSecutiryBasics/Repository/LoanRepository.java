package com.SpringBoot.SpringSecutiryBasics.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.SpringBoot.SpringSecutiryBasics.Models.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Integer> {
	/* @PreAuthorize("hasRole('USER')") */
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}