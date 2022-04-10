package in.ashokit.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Contact;
import in.ashokit.props.AppProperties;
import in.ashokit.service.IContactService;

@RestController
public class ContactRestController {

	@Autowired
	private IContactService service;
	
	@Autowired
	private AppProperties appProps;
	
	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact) {
		
		Map<String, String> messages = appProps.getMessages();
		
		String msg="";
		Boolean status = service.saveContact(contact);
		if (status) {
			//return "Contact Saved Successfully";
			System.out.println(AppConstants.CONTACT_SAVE_SUCCESS);
		msg=messages.get(AppConstants.CONTACT_SAVE_SUCCESS);
		} else {
			//return "Failed To Save Contact";
			System.out.println(AppConstants.CONTACT_SAVE_FAIL);
		msg= messages.get(AppConstants.CONTACT_SAVE_FAIL);
		}
		return msg;
	}

	// get All Contacts
	@GetMapping("/contacts")
	public List<Contact> getAllContact() {
		return service.getAllContacts();
	}

	@GetMapping("/contact/{cid}")
	public Contact editContact(@PathVariable("cid") Integer contactId) {
		return service.getContactById(contactId);

	}

	@DeleteMapping("/contact/{cid}")
	public String deleteContact(@PathVariable("cid") Integer contactId) {
	
		Map<String,String> messages = appProps.getMessages();
		String msg="";
		
		Boolean status = service.deleteContactById(contactId);
		if (status) {

			//return "Contact Deleted";
			msg=messages.get(AppConstants.CONTACT_DEL_SUCCESS);
		} else {
			//	return "failed to Delete";
			msg= messages.get(AppConstants.CONTACT_DEL_FAIL);
		}
		return msg;
	}

}
