// https://github.com/execat/ui-design-class/blob/master/ui-design2/FlatFilePersonDAO.java
package ui.contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Disk access object class for writing contacts to flat file
 */
public class FlatFileContactDAO implements ContactDAO {
    // To read the data file
    private Reader reader;

    // Separator of data
    private String separator = "::";    // Use constants

    // Static headers
    private ArrayList<String> flatFileHeaders;

    // Current state of the data
    private ArrayList<Contact> data;

    // File that will store the data
    private String dataFilePath;
    private File dataFile;

    // File that will store the deleted data // Implement later
    private String backupFilePath;
    private File backupFile;

    /**
     * Constructor
     *
     * Sets up FlatFileContactDAO object
     * Creates data and backup file if they don't exist
     * Reads the data file into "data"
     */

    public FlatFileContactDAO() {
        flatFileHeaders = new ArrayList<String>();
        data = new ArrayList<Contact>();

        // Assures data file is created
        String dataBasePath = "src/main/java/ui/contacts/";
        String dataFileName = "data.txt";
        String dataPath = dataBasePath + dataFileName;
        dataFile = new File(dataPath);
        assureFileExists(dataPath);

        // Assures backup file is created
        String backupBasePath = "src/main/java/ui/contacts/";
        String backupFileName = "backup.txt";
        String backupPath = backupBasePath + backupFileName;
        backupFile = new File(backupPath);
        assureFileExists(backupPath);

        data = fetchAll();

        try {
            reader = new FileReader(dataPath);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * close
     *
     * Closes all the connections
     * @throws Exception
     */

    @Override
    public void close() throws Exception {
        data = null;
        dataFile = null;
        backupFile = null;
        reader = null;
    }

    /**
     * fetchAll
     *
     * Fetches all the records from the datafile and returns an ArrayList of Contacts
     * @return
     */

    public ArrayList<Contact> fetchAll() {
        ArrayList<Contact> all = new ArrayList<Contact>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strs = line.split(separator);
                Contact tmp = new Contact(strs[0], strs[1], strs[2], strs[3]);
                all.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ArrayList<Contact>();
        }

        return all;

        /*
        Try returning just data
         */
    }

    /**
     * fetch
     *
     * Fetches a Contact at a given position
     *
     * @param index
     * @return
     */
    public Contact fetch(int index) {
        return data.get(index);
    }

    /**
     * assureFileExists
     *
     * Assures that a file with a given path exists
     * Creates an empty file if the path does not exist
     *
     * @param path
     * @return
     */
    private boolean assureFileExists(String path) {
        if(!dataFile.exists()) {
            return createFile(dataFile);
        }
        return true;
    }

    /**
     * createFile
     *
     * Creates the file if the file doesn't exist
     *
     * @param file
     * @return
     */

    private boolean createFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Something went wrong while trying to create the file: " + file.getPath());
            return false;
        }
        return true;
    }

    /**
     * writeToFile
     *
     * Writes a contacts array to a file
     *
     * This method reads contents of File in a temp array and writes the CSV-like notation of Contact[] to file
     *
     * @param contacts
     * @param file
     * @return
     */
    private boolean writeToFile(ArrayList<Contact> contacts, File file) {
        System.out.println("CALLING WRITE TO FILE: \n\n" + contacts);
        createFile(file);

        FileWriter fw;
        try {
            // These should ideally be two try-catch blocks
            fw = new FileWriter(file);

            for (Contact contact: contacts) {
                fw.write(contact.toString() + "\n");
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * verifyValidity
     *
     * Impelemnt later, currently does nothing
     *
     * @param contact
     * @return
     */
    private boolean verifyValidity(Contact contact, ArrayList<Contact> excludeList) {
        return true;
    }

    /**
     * addContact
     *
     * Adds the passed contact to the end of the list
     *
     * @param contact
     * @return
     */
    @Override
    public Contact addContact(Contact contact) {
        // Check
        if (!verifyValidity(contact, null)) {
            return null;
        }

        // Add
        data.add(contact);
        writeToFile(data, dataFile);
        return contact;
    }

    /**
     * updateContact
     *
     * Updates an oldContact to a new one subject to validity criteria
     *
     * @param oldContact
     * @param newContact
     * @return
     */
    @Override
    public Contact updateContact(Contact oldContact, Contact newContact) {
        // Check
        ArrayList<Contact> excludeList = new ArrayList<Contact>();
        excludeList.add(oldContact);
        if (!verifyValidity(newContact, excludeList)) {
            return null;
        }

        // Update
        int index = data.indexOf(oldContact);
        deleteContact(oldContact);
        data.add(index, newContact);
        writeToFile(data, dataFile);
        return newContact;
    }

    /**
     * deleteContact
     *
     * Deletes a passed contact
     *
     * @param contact
     * @return
     */
    @Override
    public Contact deleteContact(Contact contact) {
        data.remove(contact);
        writeToFile(data, dataFile);
        return contact;
    }

    /**
     * updateContactAt
     *
     * Updates a contact to the passed contact at the given index subject to validation preconditions
     *
     * @param index
     * @param contact
     * @return
     */
    @Override
    public Contact updateContactAt(int index, Contact contact) {
        Contact oldContact = data.get(index);
        updateContact(oldContact, contact);
        writeToFile(data, dataFile);
        return contact;
    }

    /**
     * deleteContactAt
     *
     * Deletes a contact at the given position
     *
     * @param index
     * @return
     */
    @Override
    public Contact deleteContactAt(int index) {
        Contact contact = data.get(index);
        deleteContact(contact);
        writeToFile(data, dataFile);
        return contact;

        /* Alternative
        data.remove(index)
        writeToFile(data, dataFile);
        return contact;
         */
    }
}
