package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.Contact;

public interface IContactService {
	
	public boolean saveContact(Contact contact);
	public List<Contact> getAllContacts();
	public Contact getContactById(Integer contactId);
	public boolean deleteContactById(Integer contactId);
	

}
