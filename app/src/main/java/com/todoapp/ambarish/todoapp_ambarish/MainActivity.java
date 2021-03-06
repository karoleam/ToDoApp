package com.todoapp.ambarish.todoapp_ambarish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> todoItems;
    ArrayAdapter<String> aToDoAdapter;
    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);

        setupListViewListener();
        setupEditViewListener();
    }


    public void populateArrayItems() {
//        todoItems = new ArrayList<String>();
//        todoItems.add("Item 1");
//        todoItems.add("Item 2");
//        todoItems.add("Item 3");
        readItems();
        aToDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }


    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt ");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            todoItems = new ArrayList<String>();
        }
    }


    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt ");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void setupEditViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        i.putExtra("listvalue", todoItems);
                        i.putExtra("listpos", pos);
                        startActivityForResult(i, REQUEST_CODE);
                    }

                }
        );
    }


    protected void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        todoItems.remove(pos);
                        aToDoAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onAddItem(View view) {

        aToDoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }
}
