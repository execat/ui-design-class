package ui.contacts;

/**
 * Class Contact:
 *
 * The model of the contact resource
 * Contains constructor
 * Contains getFullName
 * Contains getters
 */
public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    private String frequency;           // Implement this to see which contact gets opened most frequently
    private long timestampCreated;      // Implement this to sort by latest added

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

    /**
     * getFullName
     *
     * For firstName "Anuj", lastName "More", returns "Anuj More"
     *
     * @return
     */

    public String getFullName() {
        StringBuilder sb = new StringBuilder();

        if(!firstName.isEmpty()) {
            sb.append(firstName);
            sb.append(" ");
        }

        if(!lastName.isEmpty()) {
            sb.append(lastName);
        }

        return sb.toString();
    }

    /**
     * toString
     *
     * Returns the notation to be written to the file
     *
     * @return
     */
    @Override
    public String toString() {
        final String separator = "::";
        return (firstName + separator + lastName + separator + phoneNumber + separator +
                emailAddress + separator + frequency + separator + timestampCreated);
    }

    /**
     * Setters
     *
     * setFrequency()
     */

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * Getters
     *
     * getFirstName()
     * getLastName()
     * getPhoneNumber()
     * getEmailAddress()
     * getFrequency()
     * getTimestampCreated()
     *
     */

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFrequency() {
        return frequency;
    }

    public long getTimestampCreated() {
        return timestampCreated;
    }

}
