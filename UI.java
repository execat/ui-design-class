/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author atm
 */
public class UI extends javax.swing.JFrame {

    public static boolean error;

    private static int firstNameLimit = 20;
    private static int middleInitialLimit = 1;
    private static int lastNameLimit = 20;
    private static int address1Limit = 35;
    private static int address2Limit = 35;
    private static int cityLimit = 25;
    private static int stateLimit = 2;
    private static int zipCodeLimit = 10;
    private static int phoneNumberLimit = 21;
    private static int emailAddressLimit = 60;
    private static int SHOULD_EXIST = 1;

    private static boolean firstNameBoolean;
    private static boolean middleInitialBoolean;
    private static boolean lastNameBoolean;
    private static boolean address1Boolean;
    private static boolean address2Boolean;
    private static boolean cityBoolean;
    private static boolean stateBoolean;
    private static boolean zipCodeBoolean;
    private static boolean phoneNumberBoolean;
    private static boolean emailAddressBoolean;
    private static boolean dateReceivedBoolean;

    private ArrayList<Person> data;

    /**
     * Creates new form Frame
     */
    public UI() {
        initComponents();
        data = Controller.fetchAll();
        repopulateTable(data);
        repopulateBooleans();
        checkSavability();
    }

    private void setStatusBar(String str) {
        statusBar.setText(str);
    }

    private void repopulateBooleans() {
        firstNameBoolean = false;
        middleInitialBoolean = true;
        lastNameBoolean = false;
        address1Boolean = false;
        address2Boolean = true;
        cityBoolean = false;
        stateBoolean = false;
        zipCodeBoolean = false;
        phoneNumberBoolean = false;
        emailAddressBoolean = false;
        dateReceivedBoolean = false;
    }

    private void resetColors() {
        setNormalBorder(firstNameTextField);
        setNormalBorder(middleInitialTextField);
        setNormalBorder(lastNameTextField);
        setNormalBorder(address1TextField);
        setNormalBorder(address2TextField);
        setNormalBorder(cityTextField);
        setNormalBorder(stateTextField);
        setNormalBorder(zipCodeTextField);
        setNormalBorder(phoneNumberTextField);
        setNormalBorder(emailAddressTextField);
        setNormalBorder(dateReceivedTextField);
    }

    private void repopulateTable(ArrayList<Person> data) {
        DefaultTableModel tableModel = (DefaultTableModel) dataTable.getModel();
        tableModel.setRowCount(0);

        ArrayList<Person> list = new ArrayList<Person>();
        int i = 0;
        for (Person current : data) {
            i = i + 1;
            String[] insert = {String.valueOf(i), current.getFullName(), current.getPhone()};
            tableModel.addRow(insert);
        }
        dataTable.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    private void editPerson(int index) {
        data = Controller.fetchAll();
        Person person = data.get(index);

        firstNameTextField.setText(person.getFirstName());
        middleInitialTextField.setText(person.getMiddleInitial());
        lastNameTextField.setText(person.getLastName());
        address1TextField.setText(person.getAddress1());
        address2TextField.setText(person.getAddress2());
        cityTextField.setText(person.getCity());
        stateTextField.setText(person.getState());
        zipCodeTextField.setText(person.getZipCode());
        phoneNumberTextField.setText(person.getPhone());
        emailAddressTextField.setText(person.getEmail());
        dateReceivedTextField.setText(person.getDate());
        proofOfPurchaseCheckbox.setSelected(person.getProofOfPurchase().equals("true"));
    }

    private void resetForm() {
        data = Controller.fetchAll();
        setStatusBar("Ready to take a new entry");
        repopulateTable(data);
        repopulateBooleans();
        resetColors();

        firstNameTextField.setText("");
        middleInitialTextField.setText("");
        lastNameTextField.setText("");
        address1TextField.setText("");
        address2TextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        zipCodeTextField.setText("");
        phoneNumberTextField.setText("");
        emailAddressTextField.setText("");
        dateReceivedTextField.setText("");
        proofOfPurchaseCheckbox.setSelected(false);
    }

    private void setErrorBorder(JComponent component) {
        component.setBackground(Color.ORANGE);
    }

    private void setNormalBorder(JComponent component) {
        component.setBackground(Color.WHITE);
    }

    private boolean validateTextField(JTextField field, int limit) {
        String text = field.getText();

        if (!Validator.minLength(text, SHOULD_EXIST)) {
            setErrorBorder(field);
            return false;
        } else if (!Validator.maxLength(text, limit)) {
            setErrorBorder(field);
            return false;
        } else {
            setNormalBorder(field);
            return true;
        }
    }

    private boolean validateNonCompulsoryTextField(JTextField field, int limit) {
        String text = field.getText();

        if (!Validator.maxLength(text, limit)) {
            setErrorBorder(field);
            return false;
        } else {
            setNormalBorder(field);
            return true;
        }
    }

    // Nothing here yet
    private boolean validateDate(JTextField field) {
        String text = field.getText();

        if (!Validator.date(text)) {
            setErrorBorder(field);
            return false;
        } else {
            setNormalBorder(field);
            return true;
        }
    }

    private void checkSavability() {
        boolean savable = firstNameBoolean && middleInitialBoolean && lastNameBoolean &&
                address1Boolean && address2Boolean && cityBoolean && stateBoolean && zipCodeBoolean &&
                phoneNumberBoolean && emailAddressBoolean && dateReceivedBoolean;
        saveAndNew.setEnabled(savable);
    }

    /**
     * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        middleInitialLabel = new javax.swing.JLabel();
        middleInitialTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        address1Label = new javax.swing.JLabel();
        address1TextField = new javax.swing.JTextField();
        address2Label = new javax.swing.JLabel();
        address2TextField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        stateLabel = new javax.swing.JLabel();
        stateTextField = new javax.swing.JTextField();
        zipCodeLabel = new javax.swing.JLabel();
        zipCodeTextField = new javax.swing.JTextField();
        phoneNumberLabel = new javax.swing.JLabel();
        emailAddressLabel = new javax.swing.JLabel();
        dateReceivedLabel = new javax.swing.JLabel();
        phoneNumberTextField = new javax.swing.JTextField();
        emailAddressTextField = new javax.swing.JTextField();
        dateReceivedTextField = new javax.swing.JTextField();
        proofOfPurchaseCheckbox = new javax.swing.JCheckBox();
        saveAndNew = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        statusBar = new javax.swing.JLabel();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Rebate Central");

        dataTable.setAutoCreateRowSorter(true);
        dataTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "#", "Name", "Phone Number"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(dataTable);
        dataTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(0).setMinWidth(20);
            dataTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            dataTable.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstNameLabel.setText("First Name");

        firstNameTextField.setActionCommand("<Not Set>");
        firstNameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        firstNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstNameTextFieldFocusGained(evt);
            }
        });
        firstNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstNameTextFieldKeyPressed(evt);
            }
        });

        middleInitialLabel.setText("Middle Initial");

        middleInitialTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                middleInitialTextFieldFocusGained(evt);
            }
        });
        middleInitialTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                middleInitialTextFieldKeyReleased(evt);
            }
        });

        lastNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameTextFieldFocusGained(evt);
            }
        });
        lastNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameTextFieldKeyReleased(evt);
            }
        });

        lastNameLabel.setText("Last Name");

        address1Label.setText("Address 1");

        address1TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                address1TextFieldFocusGained(evt);
            }
        });
        address1TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                address1TextFieldKeyReleased(evt);
            }
        });

        address2Label.setText("Address 2");

        address2TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                address2TextFieldFocusGained(evt);
            }
        });
        address2TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                address2TextFieldKeyReleased(evt);
            }
        });

        cityLabel.setText("City");

        cityTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cityTextFieldFocusGained(evt);
            }
        });
        cityTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cityTextFieldKeyReleased(evt);
            }
        });

        stateLabel.setText("State");

        stateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stateTextFieldFocusGained(evt);
            }
        });
        stateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stateTextFieldKeyReleased(evt);
            }
        });

        zipCodeLabel.setText("Zip Code");

        zipCodeTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                zipCodeTextFieldFocusGained(evt);
            }
        });
        zipCodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                zipCodeTextFieldKeyReleased(evt);
            }
        });

        phoneNumberLabel.setText("Phone Number");

        emailAddressLabel.setText("Email Address");

        dateReceivedLabel.setText("Date Received");

        phoneNumberTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phoneNumberTextFieldFocusGained(evt);
            }
        });
        phoneNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNumberTextFieldKeyReleased(evt);
            }
        });

        emailAddressTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailAddressTextFieldFocusGained(evt);
            }
        });
        emailAddressTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailAddressTextFieldKeyReleased(evt);
            }
        });

        dateReceivedTextField.setColumns(10);
        dateReceivedTextField.setText("MM/DD/YY");
        dateReceivedTextField.setToolTipText("");
        dateReceivedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateReceivedTextFieldFocusGained(evt);
            }
        });
        dateReceivedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateReceivedTextFieldKeyReleased(evt);
            }
        });

        proofOfPurchaseCheckbox.setText("Proof of Purchase");
        proofOfPurchaseCheckbox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                proofOfPurchaseCheckboxFocusGained(evt);
            }
        });

        saveAndNew.setText("Save and New");
        saveAndNew.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                saveAndNewFocusGained(evt);
            }
        });
        saveAndNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndNewActionPerformed(evt);
            }
        });

        clear.setText("Clear");
        clear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                clearFocusGained(evt);
            }
        });
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(dateReceivedLabel)
                                                        .addComponent(emailAddressLabel)
                                                        .addComponent(phoneNumberLabel)
                                                        .addComponent(zipCodeLabel)
                                                        .addComponent(stateLabel)
                                                        .addComponent(cityLabel)
                                                        .addComponent(address2Label)
                                                        .addComponent(address1Label)
                                                        .addComponent(middleInitialLabel)
                                                        .addComponent(firstNameLabel)
                                                        .addComponent(lastNameLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(firstNameTextField)
                                                        .addComponent(middleInitialTextField)
                                                        .addComponent(lastNameTextField)
                                                        .addComponent(address1TextField)
                                                        .addComponent(address2TextField)
                                                        .addComponent(cityTextField)
                                                        .addComponent(stateTextField)
                                                        .addComponent(zipCodeTextField)
                                                        .addComponent(phoneNumberTextField)
                                                        .addComponent(emailAddressTextField)
                                                        .addComponent(dateReceivedTextField)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(proofOfPurchaseCheckbox)
                                                                .addGap(0, 197, Short.MAX_VALUE))))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(saveAndNew)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameLabel)
                                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(middleInitialLabel)
                                        .addComponent(middleInitialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastNameLabel)
                                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(address1Label)
                                        .addComponent(address1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(address2Label)
                                        .addComponent(address2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cityLabel)
                                        .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(stateLabel)
                                        .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(zipCodeLabel)
                                        .addComponent(zipCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneNumberLabel)
                                        .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailAddressLabel)
                                        .addComponent(emailAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateReceivedLabel)
                                        .addComponent(dateReceivedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(proofOfPurchaseCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveAndNew, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        statusBar.setText("Status Label");
        statusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        delete.setText("Delete");
        delete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                deleteFocusGained(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statusBar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(header)
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        resetForm();
    }//GEN-LAST:event_clearActionPerformed

    private void saveAndNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndNewActionPerformed
        String firstName = firstNameTextField.getText();
        String middleInitial = middleInitialTextField.getText();
        String lastName = lastNameTextField.getText();
        String address1 = address1TextField.getText();
        String address2 = address2TextField.getText();
        String city = cityTextField.getText();
        String zipCode = zipCodeTextField.getText();
        String state = stateTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String emailAddress = emailAddressTextField.getText();
        String proofOfPurchase = proofOfPurchaseCheckbox.isSelected() ? "true" : "false";
        String date = dateReceivedTextField.getText();

        Person p = new Person(firstName, middleInitial, lastName, address1, address2, city, state, zipCode, emailAddress, phoneNumber, proofOfPurchase, date);
        Controller.saveAndNew(p);
        resetForm();
    }//GEN-LAST:event_saveAndNewActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        Controller.delete(dataTable.getSelectedRows());
        data = Controller.fetchAll();
        repopulateTable(data);
    }//GEN-LAST:event_deleteActionPerformed

    private void firstNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameTextFieldFocusGained
        setStatusBar("First name must be " + firstNameLimit + " characters or fewer");
    }//GEN-LAST:event_firstNameTextFieldFocusGained

    private void firstNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameTextFieldFocusLost
    }//GEN-LAST:event_firstNameTextFieldFocusLost

    private void middleInitialTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleInitialTextFieldFocusGained
        setStatusBar("Middle initial must be " + middleInitialLimit + " character [Not compulsory]");
    }//GEN-LAST:event_middleInitialTextFieldFocusGained

    private void middleInitialTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middleInitialTextFieldFocusLost
    }//GEN-LAST:event_middleInitialTextFieldFocusLost

    private void lastNameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameTextFieldFocusGained
        setStatusBar("Last name must be " + lastNameLimit + " characters or fewer");
    }//GEN-LAST:event_lastNameTextFieldFocusGained

    private void lastNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameTextFieldFocusLost
    }//GEN-LAST:event_lastNameTextFieldFocusLost

    private void address1TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_address1TextFieldFocusGained
        setStatusBar("Address 1 must be " + address1Limit + " characters or fewer");
    }//GEN-LAST:event_address1TextFieldFocusGained

    private void address1TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_address1TextFieldFocusLost
    }//GEN-LAST:event_address1TextFieldFocusLost

    private void address2TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_address2TextFieldFocusGained
        setStatusBar("Address 2 must be " + address2Limit + " characters or fewer [Not compulsory]");
    }//GEN-LAST:event_address2TextFieldFocusGained

    private void address2TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_address2TextFieldFocusLost
    }//GEN-LAST:event_address2TextFieldFocusLost

    private void cityTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityTextFieldFocusGained
        setStatusBar("City must be " + cityLimit + " characters or fewer");
    }//GEN-LAST:event_cityTextFieldFocusGained

    private void cityTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityTextFieldFocusLost
    }//GEN-LAST:event_cityTextFieldFocusLost

    private void stateTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateTextFieldFocusGained
        setStatusBar("State must be " + stateLimit + " characters");
    }//GEN-LAST:event_stateTextFieldFocusGained

    private void stateTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stateTextFieldFocusLost
    }//GEN-LAST:event_stateTextFieldFocusLost

    private void zipCodeTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_zipCodeTextFieldFocusGained
        setStatusBar("Zip must be " + zipCodeLimit + " characters or fewer");
    }//GEN-LAST:event_zipCodeTextFieldFocusGained

    private void zipCodeTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_zipCodeTextFieldFocusLost
    }//GEN-LAST:event_zipCodeTextFieldFocusLost

    private void phoneNumberTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldFocusGained
        setStatusBar("Phone must be " + phoneNumberLimit + " characters or fewer");
    }//GEN-LAST:event_phoneNumberTextFieldFocusGained

    private void phoneNumberTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldFocusLost
    }//GEN-LAST:event_phoneNumberTextFieldFocusLost

    private void emailAddressTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAddressTextFieldFocusGained
        setStatusBar("Email address must be " + emailAddressLimit + " characters or fewer");
    }//GEN-LAST:event_emailAddressTextFieldFocusGained

    private void emailAddressTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAddressTextFieldFocusLost
    }//GEN-LAST:event_emailAddressTextFieldFocusLost

    private void dateReceivedTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateReceivedTextFieldFocusGained
        if (dateReceivedTextField.getText().equals("MM/DD/YY")) {
            dateReceivedTextField.setText("");
        }
        setStatusBar("Date received must written as MM/DD/YY");
    }//GEN-LAST:event_dateReceivedTextFieldFocusGained

    private void dateReceivedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateReceivedTextFieldFocusLost
    }//GEN-LAST:event_dateReceivedTextFieldFocusLost

    private void proofOfPurchaseCheckboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_proofOfPurchaseCheckboxFocusGained
        setStatusBar("Tick if proof of purchase was presented");
    }//GEN-LAST:event_proofOfPurchaseCheckboxFocusGained

    private void deleteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deleteFocusGained
        setStatusBar("Delete the selected entries");
    }//GEN-LAST:event_deleteFocusGained

    private void saveAndNewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_saveAndNewFocusGained
        setStatusBar("Save the entry and go to a new entry");
    }//GEN-LAST:event_saveAndNewFocusGained

    private void clearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_clearFocusGained
        setStatusBar("Clear all the fields");
    }//GEN-LAST:event_clearFocusGained

    private void firstNameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameTextFieldKeyPressed
        firstNameBoolean = validateTextField(firstNameTextField, firstNameLimit);
        checkSavability();
    }//GEN-LAST:event_firstNameTextFieldKeyPressed

    private void middleInitialTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_middleInitialTextFieldKeyReleased
        middleInitialBoolean = validateNonCompulsoryTextField(middleInitialTextField, middleInitialLimit);
        checkSavability();
    }//GEN-LAST:event_middleInitialTextFieldKeyReleased

    private void lastNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameTextFieldKeyReleased
        lastNameBoolean = validateTextField(lastNameTextField, lastNameLimit);
        checkSavability();
    }//GEN-LAST:event_lastNameTextFieldKeyReleased

    private void address1TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_address1TextFieldKeyReleased
        address1Boolean = validateTextField(address1TextField, address1Limit);
        checkSavability();
    }//GEN-LAST:event_address1TextFieldKeyReleased

    private void address2TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_address2TextFieldKeyReleased
        address2Boolean = validateNonCompulsoryTextField(address2TextField, address2Limit);
        checkSavability();
    }//GEN-LAST:event_address2TextFieldKeyReleased

    private void cityTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cityTextFieldKeyReleased
        cityBoolean = validateTextField(cityTextField, cityLimit);
        checkSavability();
    }//GEN-LAST:event_cityTextFieldKeyReleased

    private void stateTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stateTextFieldKeyReleased
        stateBoolean = validateTextField(stateTextField, stateLimit);
        checkSavability();
    }//GEN-LAST:event_stateTextFieldKeyReleased

    private void zipCodeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zipCodeTextFieldKeyReleased
        zipCodeBoolean = validateTextField(zipCodeTextField, zipCodeLimit);
        checkSavability();
    }//GEN-LAST:event_zipCodeTextFieldKeyReleased

    private void phoneNumberTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldKeyReleased
        phoneNumberBoolean = validateTextField(phoneNumberTextField, phoneNumberLimit);
        checkSavability();
    }//GEN-LAST:event_phoneNumberTextFieldKeyReleased

    private void emailAddressTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailAddressTextFieldKeyReleased
        emailAddressBoolean = validateTextField(emailAddressTextField, emailAddressLimit);
        checkSavability();
    }//GEN-LAST:event_emailAddressTextFieldKeyReleased

    private void dateReceivedTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateReceivedTextFieldKeyReleased
        dateReceivedBoolean = validateDate(dateReceivedTextField);
        checkSavability();
    }//GEN-LAST:event_dateReceivedTextFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

		/* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address1Label;
    private javax.swing.JTextField address1TextField;
    private javax.swing.JLabel address2Label;
    private javax.swing.JTextField address2TextField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JButton clear;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel dateReceivedLabel;
    private javax.swing.JTextField dateReceivedTextField;
    private javax.swing.JButton delete;
    private javax.swing.JLabel emailAddressLabel;
    private javax.swing.JTextField emailAddressTextField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel middleInitialLabel;
    private javax.swing.JTextField middleInitialTextField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JCheckBox proofOfPurchaseCheckbox;
    private javax.swing.JButton saveAndNew;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JTextField stateTextField;
    private javax.swing.JLabel statusBar;
    private javax.swing.JLabel zipCodeLabel;
    private javax.swing.JTextField zipCodeTextField;
    // End of variables declaration//GEN-END:variables
}
