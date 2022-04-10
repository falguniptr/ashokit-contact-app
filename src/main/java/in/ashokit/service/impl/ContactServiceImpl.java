package in.ashokit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Contact;
import in.ashokit.repository.ContactRepository;
import in.ashokit.service.IContactService;

@Service
public class ContactServiceImpl implements IContactService {

	// ContactRepository dependency
	@Autowired
	private ContactRepository contactRepo;

	@Override
	public boolean saveContact(Contact contact) {

		// TODO Auto-generated method stub
		contact.setActiveSw("Y");
		Contact contactSave = contactRepo.save(contact);
		if (contactSave.getContactId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {

		// TODO Auto-generated method stub
		Contact contactSave = new Contact();
		contactSave.setActiveSw("Y");
		return contactRepo.findAll(Example.of(contactSave));
	}

	@Override
	public Contact getContactById(Integer contactId) {

		// TODO Auto-generated method stub
		Optional<Contact> findById = contactRepo.findById(contactId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		// TODO Auto-generated method stub

		Optional<Contact> findById = contactRepo.findById(contactId);
		if (findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActiveSw("N");
			contactRepo.save(contact);
			return true;

		}

		return false;
	}

}
