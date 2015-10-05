package ui;

/**
 * Created by atm on 9/26/15.
 */
public interface PersonDAO extends DAO {
    Person addPerson(Person person);
    Person updatePerson(Person oldPerson, Person newPerson);
    Person deletePerson(Person person);
}
