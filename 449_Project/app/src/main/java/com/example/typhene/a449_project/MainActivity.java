package com.example.typhene.a449_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener {


    // Any List Interface Data Structure
    private ArrayList<Item> listItems = new ArrayList<>();
    private ArrayAdapter<Item> adapter;
    public final static String EXTRA_DATA = "com.example.typhene.a449_project.ABOUTDATA";
    public final static String EXTRA_DATA3 = "com.example.typhene.a449_project.TOTALDATA";
    private static int total = 0;
    public int t_budget;
    public int r_bud;
    private static final int SELECT_PICTURE = 1;
    public static final String $ = "$";

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        MainActivity.total = total;
    }

    class Item {
        public int price;
        public String item;

        @Override
        public String toString(){
            return item + " " + $ + price + "        " + "(Click to add picture)" +  "   " + R.drawable.burger;
        }
    }

    private void updateTotal() {
        TextView t = (TextView) findViewById(R.id.total_num);
        t.setText($ + Integer.toString(getTotal()));
    }
    private void updateRemBud() {
        TextView t2 = (TextView) findViewById(R.id.rem_bud_num);
        t2.setText($ + Integer.toString(r_bud));
    }
    private void updateTBudget() {
        TextView t = (TextView) findViewById(R.id.bud_num);
        t.setText($ + Integer.toString(t_budget));
    }
    //clears the item text after adding item to list
    private void clearItem() {
        TextView t = (TextView) findViewById(R.id.editText1);
        t.setText("");
    }
//clears the price text after adding item to list
    private void clearPrice() {
        TextView t = (TextView) findViewById(R.id.editText2);
        t.setText("");
    }
//moves the cursor back to the item after adding item to list
    private void restartCursor() {
        TextView t = (TextView) findViewById(R.id.editText1);
        t.requestFocus();
    }
    private void clearList() {
        listItems.clear();
        ListView listView = (ListView) this.findViewById(R.id.listOfSomething);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }
    private void updateBudgets() {
        updateTBudget();
        updateRemBud();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View prevButton = findViewById(R.id.button1);
        prevButton.setOnClickListener(this);

        ListView listView = (ListView) this.findViewById(R.id.listOfSomething);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Intent start_main = getIntent();
        t_budget = start_main.getIntExtra("int_value", 0);
        r_bud=t_budget;

        updateBudgets();

        Toast toast2 = Toast.makeText(context, "The Budget is $" + t_budget, duration);
        toast2.show();
    }

    // This is for button clicks
    @Override
    public void onClick(View arg0) {
        try {
            Assert.assertNotNull(arg0);
            // Get string entered
            Item i = new Item();
            TextView tv = (TextView) findViewById(R.id.editText1);
            TextView subTV = (TextView) findViewById(R.id.editText2);
            // Add string to underlying data structure
            i.item = (tv.getText().toString());
            i.price = Integer.parseInt(subTV.getText().toString());
            listItems.add(i);
            // Notify adapter that underlying data structure changed
            adapter.notifyDataSetChanged();
            for (int r = 0; r < listItems.size() / listItems.size(); r++) {
                setTotal(getTotal() + i.price);
                updateTotal();
            }
            if (t_budget > 0) {
                r_bud = t_budget - total;
                if (r_bud < 5) {
                    if (r_bud < 0) {
                        Context lcontext = getApplicationContext();
                        int lduration = Toast.LENGTH_LONG;
                        Toast ltoast = Toast.makeText(lcontext, "YOU ARE OVER BUDGET!!! CONSIDER REVISING SHOPPING CART.", lduration);
                        ltoast.show();
                    } else {
                        Context context = getApplicationContext();
                        int bduration = Toast.LENGTH_LONG;
                        Toast btoast = Toast.makeText(context, "BE CAREFUL. YOU ONLY HAVE $" + r_bud + " REMAINING", bduration);
                        btoast.show();
                    }
                }
            }
            clearPrice();
            clearItem();
            restartCursor();
            updateBudgets();
        }
        catch(Exception e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, "You must enter a item and price.", duration);
            toast.show();
        }
    }

    // This is for selecting an item from the list
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent pickIntent = new Intent();
        pickIntent.setType("image/*");
        pickIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
        Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
        chooserIntent.putExtra
                (
                        Intent.EXTRA_INITIAL_INTENTS,
                        new Intent[] { takePhotoIntent }
                );

        startActivityForResult(chooserIntent, SELECT_PICTURE);
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
            case R.id.reset:
                setTotal(0);
                t_budget = 0;
                r_bud = 0;
                updateTotal();
                updateBudgets();
                clearList();
                return true;
            case R.id.totals:
                Intent intent3 = new Intent(this, TotalsActivity.class);
                intent3.putExtra("int", getTotal());
                startActivity(intent3);
                return true;
            case R.id.about:
                Intent intent = new Intent(this, AboutMenu.class);
                intent.putExtra(EXTRA_DATA, "Welcome to Budget Watch");
                startActivity(intent);
                return true;
            default:
                Assert.fail("Event has no event handler.");
                return true;
        }
    }
}
