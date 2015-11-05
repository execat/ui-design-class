package ui.contacts;

/**
 * Interface ContactDAO:
 *
 * Defines contact specific methods to modify the disk
 *
 * author: Anuj More (atm140330)
 */
public interface ContactDAO extends DAO {
    Contact addContact(Contact contact);
    Contact updateContact(Contact oldContact, Contact newContact);
    Contact deleteContact(Contact contact);
    Contact updateContactAt(int index, Contact contact);
    Contact deleteContactAt(int index);
}
