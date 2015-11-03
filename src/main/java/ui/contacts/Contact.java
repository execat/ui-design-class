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

    /**
     * Constructors
     */

    public Contact() {}

    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;

        this.frequency = "0";
        this.timestampCreated = System.currentTimeMillis() / 1000L;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();

        if(!firstName.isEmpty()) {
            sb.append(firstName);
        }

        if(!lastName.isEmpty()) {
            sb.append(lastName);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        final String separator = "::";
        return (firstName + separator + lastName + separator +
                phoneNumber + separator + emailAddress + "\n");
    }

    /**
     * Getters and setters
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public long getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(long timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

}
