package ui.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Class MainActivity:
 *
 * This activity populates and shows the screen on starting a fresh instance of the app
 */
public class MainActivity extends Activity {
    ContactController controller;
    ArrayList<Contact> data;
    ArrayList<ListViewItem> listViewItems;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        controller = new ContactController();
        data = controller.fetchAll();
        listViewItems = new ArrayList<ListViewItem>();
        for (Contact row : data) {
            listViewItems.add(new ListViewItem(row.getFullName(), row.getPhoneNumber()));
        }

        listView = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, listViewItems);
        listView.setAdapter(adapter);

        // Set click event on the list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(view.getContext(), ShowActivity.class);
                // Assign extra information about the click (edit contact or new contact)
                intent.putExtra("NEW_OR_EDIT", "edit");
                intent.putExtra("NUMBER", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, ShowActivity.class);
                // Assign extra information about the click (edit contact or new contact)
                intent.putExtra("NEW_OR_EDIT", "new");
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
