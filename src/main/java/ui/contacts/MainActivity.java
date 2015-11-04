package ui.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ContactController controller;
    ArrayList<Contact> data;
    ArrayList<String> stringData;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new ContactController();
        data = controller.fetchAll();
        stringData = new ArrayList<String>();
        for (Contact row : data) {
            stringData.add(row.getFullName());
        }

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.single_row, R.id.textView, stringData
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(view.getContext(), ShowActivity.class);
                intent.putExtra("NEW_OR_EDIT", "edit");
                intent.putExtra("NUMBER", position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onCreate(null);
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
            case R.id.action_settings:
                return true;

            case R.id.action_add:
                Intent intent = new Intent(this, ShowActivity.class);
                intent.putExtra("NEW_OR_EDIT", "new");
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
