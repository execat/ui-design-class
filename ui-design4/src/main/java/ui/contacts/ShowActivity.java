package ui.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Class ShowActivity:
 *
 * This activity defines the show page
 *
 * author: Anuj More (atm140330)
 */

public class ShowActivity extends Activity {
    Contact current;
    Bundle extras;
    ContactController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // Store references to the fields
        final EditText firstName = (EditText) findViewById(R.id.firstNameText);
        final EditText lastName = (EditText) findViewById(R.id.lastNameText);
        final EditText phone = (EditText) findViewById(R.id.phoneText);
        final EditText email = (EditText) findViewById(R.id.emailText);

        // Store references to the buttons
        Button save = (Button) findViewById(R.id.saveButton);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        Button delete = (Button) findViewById(R.id.deleteButton);

        extras = getIntent().getExtras();

        if (extras.getString("NEW_OR_EDIT").equals("edit")) {
            int number = extras.getInt("NUMBER");
            current = controller.fetch(number);

            // Set state
            firstName.setText(current.getFirstName());
            lastName.setText(current.getLastName());
            phone.setText(current.getPhoneNumber());
            email.setText(current.getEmailAddress());
        } else if (extras.getString("NEW_OR_EDIT").equals("new")) {
            delete.setEnabled(false);
        }

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Validate empty first name string
                if (firstName.getText().toString().isEmpty()) {
                    firstName.setError("This field cannot be blank");
                } else if (extras.getString("NEW_OR_EDIT").equals("edit")) {
                    int number = extras.getInt("NUMBER");
                    Contact current = new Contact(firstName.getText().toString(), lastName.getText().toString(),
                            phone.getText().toString(), email.getText().toString());
                    // If the first name is not empty (Validation)
                    controller.updateContactAt(number, current);
                    finish();
                } else if (extras.getString("NEW_OR_EDIT").equals("new")) {
                    Contact current = new Contact(firstName.getText().toString(), lastName.getText().toString(),
                            phone.getText().toString(), email.getText().toString());
                    if (!firstName.getText().toString().isEmpty()) {
                        controller.addContact(current);
                        finish();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (extras.getString("NEW_OR_EDIT").equals("edit")) {
                    int number = extras.getInt("NUMBER");
                    controller.delete(number);
                    finish();
                } else if (extras.getString("NEW_OR_EDIT").equals("new")) {
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
