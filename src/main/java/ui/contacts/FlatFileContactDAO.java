package ui.contacts;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Disk access object class for writing contacts to flat file
 */
public class FlatFileContactDAO {
    // To read the data file
    private Reader reader;

    // Separator of data
    private String separator = "::";    // Use constants

    // Static headers
    private ArrayList<String> flatFileHeaders;

    // Current state of the data
    private ArrayList<String> data;

    // File that will store the data
    private String dataFilePath;
    private File dataFile;

    // File that will store the deleted data // Implement later
    private String backupFilePath;
    private File backupFile;

    /**
     * Constructor
     * Sets up FlatFileContactDAO object
     */

    public FlatFileContactDAO() {
        flatFileHeaders = new ArrayList<String>();
        data = new ArrayList<String>();

        String basePath = "";

        

    }
}
