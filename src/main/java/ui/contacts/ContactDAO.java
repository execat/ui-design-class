package ui.contacts;

/**
 * Defines contact specific methods to modify the disk
 */
public interface ContactDAO {
    Contact addContact(Contact);
    Contact updateContact(Contact, Contact);
    Contact deleteContact(Contact);
}
