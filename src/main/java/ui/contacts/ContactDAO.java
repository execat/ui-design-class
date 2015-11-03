package ui.contacts;

/**
 * Defines contact specific methods to modify the disk
 */
public interface ContactDAO extends DAO {
    Contact addContact(Contact contact);
    Contact updateContact(Contact oldContact, Contact newContact);
    Contact deleteContact(Contact contact);
    Contact updateContactAt(int index, Contact contact);
    Contact deleteContactAt(int index);
}
