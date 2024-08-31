package com.akit.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akit.Repos.ContactRepo;
import com.akit.entities.Contact;

@Service
public class ContactServiceImpl implements ContactService {
	
	private ContactRepo contRepo;
	
	public ContactServiceImpl(ContactRepo contRepo) {
		this.contRepo=contRepo;
	}

	@Override
	public List<Contact> getContactList() {
		List<Contact> contactList = contRepo.findAll();
		return contactList;
	}

}
