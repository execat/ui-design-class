package ui.contacts;

/**
 * Created by atm on 11/4/15.
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
