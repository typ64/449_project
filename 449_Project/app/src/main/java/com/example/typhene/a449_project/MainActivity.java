package com.example.typhene.a449_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnItemClickListener {

    private ArrayAdapter<String> adapter;
    private int total_number = 0;
    public final static String EXTRA_DATA = "com.example.typhene.a449_project.ABOUTDATA";
    // Any List Interface Data Structure
    private ArrayList<String> listItems = new ArrayList<String>();;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View prevButton = findViewById(R.id.button1);
        prevButton.setOnClickListener(this);

        ListView listView = (ListView)this.findViewById(R.id.listOfSomething);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    private void updateTotal() {
        TextView t = (TextView)findViewById(R.id.total_num);
        t.setText(Integer.toString(total_number));
    }
    // This is for button clicks
    @Override
    public void onClick(View arg0) {
        Assert.assertNotNull(arg0);
        // Get string entered
        TextView tv = (TextView) findViewById(R.id.editText1);
        TextView subTV = (TextView) findViewById(R.id.editText2);
        String text = subTV.getText().toString();
        int n = 0;
        if(text.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
        {
            n = Integer.parseInt(text);
        }
        else
        {
            System.out.println("not a valid number");
        }
        int total_number = n+n;
        updateTotal();
        // Add string to underlying data structure
        listItems.add(tv.getText().toString());
        listItems.add(subTV.getText().toString());
        // Notify adapter that underlying data structure changed
        adapter.notifyDataSetChanged();
    }

    // This is for selecting an item from the list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get item from ListView
        String item = (String) parent.getItemAtPosition(position);
        String price = (String) parent.getItemAtPosition(position+1);
        //String price = (String) parent.getItemAtPosition(position);
        if (position % 2 == 0) {
            String text = "Item number " + (position + 1)
                    + " (" + item + ")" + " cost $" + price + ".";
            // Use a toast message to show which item selected
            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(this, AboutMenu.class);
                intent.putExtra(EXTRA_DATA, "Welcome to Umpire Buddy");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
