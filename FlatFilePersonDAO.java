package ui;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by atm on 9/26/15.
 */
public class FlatFilePersonDAO implements PersonDAO {

    // To read the data file
    private Reader reader;

    // Separator of the data
    private String separator = Constants.separator;

    // Static headers
    private ArrayList<Person> csvHeaders;

    // Will save current state of the data
    private ArrayList<Person> data;

    // File that will store the data
    private String dataPath;
    private File dataFile;

    // File that will store the deleted data
    private String backupPath;
    private File backupFile;

    /**
     * Constructor
     * Sets up FlatFilePerson object
     *
     * Working
     * The constructor will do the following things:
     * 1. Set constants like CSV headers, data and backup paths
     * 2. If files mentioned in the data and backup paths do not exist, it will create them
     * 3. Save references to file objects on dataFile and backupFile
     */
    public FlatFilePersonDAO() {
        csvHeaders = new ArrayList();
        data = new ArrayList();

        /*
        Person headerPerson = new Person("firstName", "middleInitial", "lastName", "address1", "address2", "city",
                "state", "zipCode", "email", "phone", "proofOfPurchase", "date");
        data.add(headerPerson);
        csvHeaders.add(headerPerson);
        */

        String basePath = "src/ui/data/";

        // TODO: remove code duplication in the next two sections by creating verify(File f)
        dataPath =  basePath + "data.txt";
        dataFile = new File(dataPath);
        if (!dataFile.exists()) {
            createFile(dataFile);
        }

        backupPath = basePath + "backup.txt";
        backupFile = new File(backupPath);
        if (!backupFile.exists()) {
            createFile(backupFile);
        }

        try {
            reader = new FileReader(dataPath);
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    /**
     * close
     * Removes references to all objects
     *
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
     * createFile
     * Creates an empty file
     *
     * Description:
     * This method first creates the directory structure if not present, then creates the file, then writes
     * the CSV headers (because those need to be in both files)
     *
     * @param file
     * @return
     */
    private boolean createFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch(Exception e) { return false; };
        return true;
    }

    /**
     * writeToFile
     * Writes a persons array to a file
     *
     * Description:
     * This method:
     * 1. reads contents of File in a temp persons array
     * 2. writes CSV notation of Person[] to file
     *
     * @param persons
     * @param file
     * @return
     */

    // TODO: find if this can be called on final close

    private boolean writeToFile(ArrayList<Person> persons, File file) {
        System.out.println("CALLING WRITE TO FILE" + persons);
        createFile(file);

        FileWriter fw;
        try {
            // These should ideally be two try-catch blocks
            fw = new FileWriter(file);

            for (Person person: persons) {
                fw.write(person.toString() + "\n");
            }

            fw.flush();
            fw.close();
        } catch (IOException e) { e.printStackTrace(); return false; }

        return true;
    }

    public boolean verifyValidity(Person person, ArrayList<Person> excludeList) {
        for (Person existingPerson : data) {
            // If names match
            if ((existingPerson.getFirstName().equals(person.getFirstName())) &&
                    (existingPerson.getMiddleInitial().equals(person.getMiddleInitial())) &&
                    (existingPerson.getLastName().equals(person.getLastName()))) {

                if (excludeList == null) {
                    return false;
                } else {
                    // If object is not in the exclude list then return false
                    if (!excludeList.contains(existingPerson)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Person addPerson(Person person) {
        // Check if addition is valid
        if (!verifyValidity(person, null)) {
            return null;
        }

        // Add and save to file
        data.add(person);
        writeToFile(data, dataFile);
        return person;
    }

    @Override
    public Person updatePerson(Person oldPerson, Person newPerson) {
        // Check if update is valid
        ArrayList<Person> excludeList = new ArrayList<Person>();
        excludeList.add(oldPerson);
        if (!verifyValidity(newPerson, excludeList)) {
            return null;
        }

        // Update
        int index = data.indexOf(oldPerson);
        deletePerson(oldPerson);
        data.add(index, newPerson);
        writeToFile(data, dataFile);
        return newPerson;
    }

    @Override
    public Person deletePerson(Person person) {
        data.remove(person);
        writeToFile(data, dataFile);
        return person;
    }

    // TODO: Is returning Person object a good idea?
    public Person deleteIndex(int i) {
        Person person = data.get(i);
        deletePerson(person);
        writeToFile(data, dataFile);
        return person;
    }

    public ArrayList<Person> fetchAll() {
        ArrayList<Person> all = new ArrayList<Person>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strs = line.split(separator);
                Person tmp = new Person(strs[0], strs[1], strs[2], strs[3], strs[4], strs[5], strs[6], strs[7], strs[8], strs[9], strs[10], strs[11]);
                all.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ArrayList<Person>();
        }

        return all;
    }

    public static void main(String[] args) throws Exception {
        FlatFilePersonDAO dao = new FlatFilePersonDAO();
        dao.createFile(dao.dataFile);

        // Welcome to the test suite!
        // There is no code after this that is important for functioning of the program till the EOF.
        // Initially, there is no data
        /*
        System.out.println(dao.data.size() == 0);

        // Then we add one person
        Person p1 = new Person("Anuj", "T", "More", "7815 McCallum Blvd", "#3202", "Dallas", "TX", "75252",
                "atm140330@utdallas.edu", "+1 214 603 7414", "true", "12-11-1991");
        Person response = dao.addPerson(p1);
        // Response should be "true", size of dao.data should be 1
        System.out.println(response != null);
        System.out.println(dao.data.size() == 1);

        // And another one
        Person p2 = new Person("John", "W", "Cole", "7815 McCallum Blvd", "#3203", "Dallas", "TX", "75252",
                "john.cole@utdallas.edu", "+1 214 603 7415", "true", "12-11-1961");
        response = dao.addPerson(p2);
        // Response should be "true", size of dao.data should be 2
        System.out.println(response != null);
        System.out.println(dao.data.size() == 2);

        // Now we edit p1
        int p1Index = dao.data.indexOf(p1);
        Person p1New = new Person(p1);
        p1New.setFirstName("Walrus");
        p1New.setLastName("Abbey");
        response = dao.updatePerson(p1, p1New);
        // Response should be "true", size of dao.data should be 2, index should have be same as first element
        // but the element at that index should not be same as the element that was there
        System.out.println(response != null);
        System.out.println(dao.data.size() == 2);
        System.out.println(p1Index == dao.data.indexOf(p1New));
        System.out.println(p1 != p1New);

        // Now we add a couple more entries just for enough data
        Person p = new Person("Hello", "W", "World", "9470 Abbey Road", "", "Dallas", "TX", "75250",
                "hello.world@utdallas.edu", "+1 214 888 909", "true", "12-10-1971");
        dao.addPerson(p);
        p = new Person("Rofl", "M", "Cat", "1470 Strawberry Road", "#550", "Houston", "TX", "85250",
                "roflcat@utdallas.edu", "+1 914 899 909", "true", "7-10-1971");
        dao.addPerson(p);
        System.out.println(dao.data.size() != 2);
        System.out.println(dao.data.size() != 5);
        System.out.println(dao.data.size() == 4);

        // Now delete index = 1 (John Cole), make sure none of the records match John Cole
        // Except the deleted one
        for (Person tmp: dao.data) {
            System.out.println("Entry: " + tmp);
        }
        Person deleted = dao.deleteIndex(1);
        for (Person person : dao.data) {
            System.out.println(person.getEmail() != "john.cole@utdallas.edu");
        }
        System.out.println(deleted.getEmail() == "john.cole@utdallas.edu");

        // No duplicate records must be allowed to be created
        p = new Person("Hello", "W", "World", "Different", "Hello", "San Jose", "CA", "74740",
                "hello.different@utdallas.edu", "+1 222 760 909", "true", "2-10-1971");
        response = dao.addPerson(p);
        System.out.println(response == null);

        // No update must be allowed that makes the records invalid
        p = dao.updatePerson(p2, new Person("Walrus", "T", "Abbey", "LMAO", "Oshbini", "Dallas", "TX", "75252", "walrys140330@utdallas.edu", "+1 212 683 7224", "true", "1-06-1997"));
        System.out.println(p == null);

        System.out.println("Length of all records:" + dao.fetchAll().size());
        System.out.println("All records:" + dao.fetchAll());
        */
    }
}
