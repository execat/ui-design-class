package ui.contacts;

/**
 * ClassListViewItem:
 *
 * This class represents a list view item encapsulating the full name and the phone number
 *
 * author: Anuj More (atm140330)
 */
public class ListViewItem {
    String name;
    String phone;

    ListViewItem(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
