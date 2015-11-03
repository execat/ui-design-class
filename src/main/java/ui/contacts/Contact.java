package ui.contacts;

/**
 * The model of the contact resource
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    private String frequency; // Implement this to see which contact gets opened most frequently
    private long timestampCreated; // Implement this to sort by latest added

    public Contact() {}

    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;

        this.frequency = "0";
        this.timestampCreated = System.currentTimeMillis() / 1000L;
    }

    public static void main(String[] args) {
        Contact c1 = new Contact();
        Contact c2 = new Contact("a", "b", "c", "d");
    }
}
