package ui.contacts;

import java.util.ArrayList;

/**
 * Created by atm on 11/2/15.
 */
public class ContactController {
    static FlatFileContactDAO dao;

    ContactController() {
        dao = new FlatFileContactDAO();
    }

    public static Contact addContact(Contact contact) {
        return dao.addContact(contact);
    }

    public static Contact updateContactAt(int i, Contact contact) {
        return dao.updateContactAt(i, contact);
    }

    public static ArrayList<Contact> fetchAll() {
        return dao.fetchAll();
    }

    public static Contact fetch(int index) {
        return dao.fetch(index);
    }

    public static Contact delete(int index) {
        return dao.deleteContactAt(index);
    }
}
