package ui.contacts;

import java.util.ArrayList;

/**
 * Created by atm on 11/2/15.
 */
public class ContactController {
    static FlatFileContactDAO dao = new FlatFileContactDAO();

    public static Contact save(Contact contact) {
        return dao.addContact(contact);
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
