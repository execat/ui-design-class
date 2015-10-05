/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author atm
 */
class Person {

    // Fields

    private String separator = Constants.separator;
    private SimpleStringProperty firstName;
    private SimpleStringProperty middleInitial;
    private SimpleStringProperty lastName;
    private SimpleStringProperty address1;
    private SimpleStringProperty address2;
    private SimpleStringProperty city;
    private SimpleStringProperty state;
    private SimpleStringProperty zipCode;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty proofOfPurchase;
    private SimpleStringProperty date;

    // Constructors

    public Person() {}

    public Person(String firstName, String middleInitial, String lastName, String address1,
                  String address2, String city, String state, String zipCode, String email,
                  String phone, String proofOfPurchase, String date) {
        this.firstName = new SimpleStringProperty(firstName);
        this.middleInitial = new SimpleStringProperty(middleInitial);
        this.lastName = new SimpleStringProperty(lastName);
        this.address1 = new SimpleStringProperty(address1);
        this.address2 = new SimpleStringProperty(address2);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.proofOfPurchase = new SimpleStringProperty(proofOfPurchase);
        this.date = new SimpleStringProperty(date);
    }

    // For testing only
    public Person(Person person) {
        this.firstName = person.firstName;
        this.middleInitial = person.middleInitial;
        this.lastName = person.lastName;
        this.address1 = person.address1;
        this.address2 = person.address2;
        this.city = person.city;
        this.state = person.state;
        this.zipCode = person.zipCode;
        this.email = person.email;
        this.phone = person.phone;
        this.proofOfPurchase = person.proofOfPurchase;
        this.date = person.date;
    }

    // Getters

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial.get();
    }

    public SimpleStringProperty middleInitialProperty() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress1() {
        return address1.get();
    }

    public SimpleStringProperty address1Property() {
        return address1;
    }

    public String getAddress2() {
        return address2.get();
    }

    public SimpleStringProperty address2Property() {
        return address2;
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public String getProofOfPurchase() {
        return proofOfPurchase.get();
    }

    public SimpleStringProperty proofOfPurchaseProperty() {
        return proofOfPurchase;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public SimpleStringProperty zipCodeProperty() {
        return zipCode;
    }

// Setters

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial.set(middleInitial);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setAddress1(String address1) {
        this.address1.set(address1);
    }

    public void setAddress2(String address2) {
        this.address2.set(address2);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setProofOfPurchase(String proofOfPurchase) {
        this.proofOfPurchase.set(proofOfPurchase);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    @Override
    public String toString() {
        return (firstName.get() + separator + middleInitial.get() + separator + lastName.get() + separator + address1.get() +
                separator + address2.get() + separator + city.get() + separator + state.get() + separator + zipCode.get() + separator +
                email.get() + separator + phone.get() + separator + proofOfPurchase.get() + separator + date.get());
    }
}
