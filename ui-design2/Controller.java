/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;

/**
 *
 * @author atm
 */
public class Controller {
    static FlatFilePersonDAO dao = new FlatFilePersonDAO();

    public static Person saveAndNew(Person p) {
        return dao.addPerson(p);
    }

    public static ArrayList<Person> fetchAll() {
        return dao.fetchAll();
    }

    public static ArrayList<Person> delete(int[] selectedRows) {
        ArrayList<Person> deleted = new ArrayList<>();
        ArrayList<Person> all = fetchAll();

        for (int i = selectedRows.length - 1; i > -1; i--) {
            int current = selectedRows[i];
            Person item = dao.deleteIndex(current);
            System.out.println("Removing item " + current + " :: First name " + item.getFirstName());
            deleted.add(item);
        }

        return deleted;
    }
}
