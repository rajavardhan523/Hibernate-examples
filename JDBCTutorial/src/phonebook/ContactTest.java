package phonebook;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactTest {
	public static void main(String[] args) throws SQLException {
		Contact contact = new Contact();
		Address address = new Address();
		PhoneNumber phoneNumber = new PhoneNumber();
		PhoneNumber phoneNumber1 = new PhoneNumber();
		List<Contact> contacts = new ArrayList<Contact>();

		address.setStreet("Claflin Rd #Apt18");
		address.setCity("Manhattan");
		address.setState("KS");
		address.setZipCode("66502");

		phoneNumber.setPhoneNumber("7857706111");
		phoneNumber.setPhoneType("HomePhone");
		phoneNumber1.setPhoneNumber("7857706XXX");
		phoneNumber1.setPhoneType("OfficePhone");

		contact.setFirstName("Rajavardhan");
		contact.setLastName("Malladi");
		contact.setId("1");
		contact.setAddress(address);
		Set<PhoneNumber> phno = new HashSet<PhoneNumber>();
		phno.add(phoneNumber);
		phno.add(phoneNumber1);
		contact.setPhoneNumbers(phno);

		contacts.add(contact);

		ContactDAO contactDAO = new ContactDAO();

		contactDAO.createContacts(contacts);

		contactDAO.readContact("Malladi");
		phoneNumber1.setPhoneNumber("XXX7706111");
		contactDAO.updateContacts(contacts);

		contactDAO.deleteContact("Malladi");
	}
}
