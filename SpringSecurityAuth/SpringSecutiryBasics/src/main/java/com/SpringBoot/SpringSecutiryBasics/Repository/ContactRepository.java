package com.SpringBoot.SpringSecutiryBasics.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.SpringSecutiryBasics.Models.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
	
}
