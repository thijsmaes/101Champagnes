package be.fedasil.matchit.backend.facade;

import java.util.List;

import be.fedasil.matchit.backend.model.Contact;

public interface ContactFacade {

	/**
	 * 
	 * @return Returns list of  Contacts
	 */

	List<Contact> findAllContacts();
	
	/***
	 * Find Contact by id 
	 * @param id
	 * @return Returns the Contact
	 */

	Contact findContactById(int id);
	
	/**
	 *  Create Contact in the database
	 *  @param Contact
	 */

	void createContactsById(Contact contact);
	
	/**
	 * Update Contact in database
	 * @param Contact
	 * 
	 */
	void updateContactsById(Contact contact);
	
	
	
	/**
	 * Delete Contact from database
	 * @param Contact
	 * 
	 */
	void deleteContactById(Contact contact);
	
}
