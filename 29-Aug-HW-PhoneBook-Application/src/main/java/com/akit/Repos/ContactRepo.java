package com.akit.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akit.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

}
