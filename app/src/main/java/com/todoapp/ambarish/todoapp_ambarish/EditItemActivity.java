package com.todoapp.ambarish.todoapp_ambarish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by akarole on 1/24/16.
 */
public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText editText = (EditText) findViewById(R.id.eItemtext);
        ArrayList<String> receivedItems = getIntent().getStringArrayListExtra("listvalue");
        int receivedPos = getIntent().getExtras().getInt("listpos");
        editText.setText(receivedItems.get(receivedPos));
        editText.setSelection(editText.length());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    public void onSave(View v) {
        EditText editText = (EditText) findViewById(R.id.eItemtext);
        String itemText = editText.getText().toString();
        int receivedPos = getIntent().getExtras().getInt("listpos");
        Intent data = new Intent();
        data.putExtra("newstring", itemText);
        data.putExtra("pos", receivedPos);
        setResult(RESULT_OK, data);
        finish();
    }
}