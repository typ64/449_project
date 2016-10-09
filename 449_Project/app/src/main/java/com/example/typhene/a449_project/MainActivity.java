package com.example.typhene.a449_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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

    // Any List Interface Data Structure
    private ArrayList<Item> listItems = new ArrayList<>();;
    private ArrayAdapter<Item> adapter;
    public final static String EXTRA_DATA = "com.example.typhene.a449_project.ABOUTDATA";
    public int Total = 0;

    class Item
    {
        public int price;
        public String item;


        @Override
        public String toString(){
            return item + " " + "$" + price;
        }

    }

    private void updateTotal() {
        TextView t = (TextView)findViewById(R.id.total_num);
        t.setText(Integer.toString(Total));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View prevButton = findViewById(R.id.button1);
        prevButton.setOnClickListener(this);

        ListView listView = (ListView)this.findViewById(R.id.listOfSomething);
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1 , listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    // This is for button clicks
    @Override
    public void onClick(View arg0) {
        Assert.assertNotNull(arg0);
        // Get string entered
        Item i = new Item();
        TextView tv = (TextView) findViewById(R.id.editText1);
        TextView subTV = (TextView) findViewById(R.id.editText2);
        // Add string to underlying data structure
        i.item =(tv.getText().toString());
        i.price = Integer.parseInt (subTV.getText().toString());
       listItems.add(i);
       // listItems.add(subTV.getText().toString());
        // Notify adapter that underlying data structure changed
        adapter.notifyDataSetChanged();
        for (int r = 0; r < listItems.size(); r++) {
           int total_number =0;
            total_number = i.price;
            Total =+ total_number;
        }
        updateTotal();
    }

    // This is for selecting an item from the list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get item from ListView
        Item i = new Item();
        TextView tv = (TextView) findViewById(R.id.editText1);
        TextView subTV = (TextView) findViewById(R.id.editText2);
        // Add string to underlying data structure
        i.item =(tv.getText().toString());
        i.price = Integer.parseInt (subTV.getText().toString());
        /*String clickSpot = (String) parent.getItemAtPosition(position);
        String iR = clickSpot;
        String pR = clickSpot;
        String itemHere = iR.replaceAll("\\d", "");
        String priceHere = pR.replaceAll("[^\\d.]", "");
        */
        Item itemHere = (Item) parent.getItemAtPosition(position);
        Item priceHere = (Item) parent.getItemAtPosition(position);
        String text = "Item number " + (position + 1)
                + " (" + itemHere + ")" + " cost $" + priceHere + ".";
        // Use a toast message to show which item selected
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
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

        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(this, AboutMenu.class);
                intent.putExtra(EXTRA_DATA, "Welcome to Budget Watch");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
