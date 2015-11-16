package ui.contacts;

import java.util.ArrayList;

/**
 * Class ContactController:
 *
 * Controls requests coming from the UI layer and passes them to the disk access objects
 *
 * author: Anuj More (atm140330)
 */
public class ContactController {
    static FlatFileContactDAO dao;

    ContactController() {
        dao = new FlatFileContactDAO();
    }

    // Adds contact
    public static Contact addContact(Contact contact) {
        return dao.addContact(contact);
    }

    // Updates contact at a position
    public static Contact updateContactAt(int i, Contact contact) {
        return dao.updateContactAt(i, contact);
    }

    // Fetches all the contacts
    public static ArrayList<Contact> fetchAll() {
        return dao.fetchAll();
    }

    // Fetches a contact at position "index"
    public static Contact fetch(int index) {
        return dao.fetch(index);
    }

    // Deletes a contact at position "index"
    public static Contact delete(int index) {
        return dao.deleteContactAt(index);
    }
}
