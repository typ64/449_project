package com.example.typhene.a449_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    //public final static String EXTRA_DATA2 = "com.example.typhene.a449_project.BUDGETDATA";
    public static int total = 0;
    public int t_budget;
    public int r_bud;

    class Item {
        public int price;
        public String item;

        @Override
        public String toString() {
            return item + " " + "$" + price;
        }

    }

    private void updateTotal() {
        TextView t = (TextView) findViewById(R.id.total_num);
        t.setText(("$") + Integer.toString(total));
    }
    private void updateRemBud() {
        TextView t2 = (TextView) findViewById(R.id.rem_bud_num);
        t2.setText(("$") + Integer.toString(r_bud));
    }
    private void updateTBudget() {
        TextView t = (TextView) findViewById(R.id.bud_num);
        t.setText(("$") + Integer.toString(t_budget));
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
    private void startAt1() {
        TextView t = (TextView) findViewById(R.id.editText1);
        t.requestFocus();
    }
    private void clearList() {
        listItems.clear();
        ListView listView = (ListView) this.findViewById(R.id.listOfSomething);
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View prevButton = findViewById(R.id.button1);
        prevButton.setOnClickListener(this);

        ListView listView = (ListView) this.findViewById(R.id.listOfSomething);
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Intent start_main = getIntent();
        t_budget = start_main.getIntExtra("int_value", 0);
        r_bud=t_budget;

        updateTBudget();
        updateRemBud();

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
                total += i.price;
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
            startAt1();
            updateTBudget();
            updateRemBud();
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
        Item item = (Item)  parent.getItemAtPosition(position);
       // Add string to underlying data structure
        /*String text = i.item + "is item number " + (position + 1) +
                " and cost $"; //+ price + ".";*/
        String text = "Item #" + (position + 1) + " = " + item + ".";
       // Get item from ListView
        // Use a toast message to show which item selected
       Toast toastt = Toast.makeText(this, text, Toast.LENGTH_LONG);
       toastt.show();
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
                total = 0;
                t_budget = 0;
                updateTotal();
                updateTBudget();
                updateRemBud();
                clearList();
                return true;
            case R.id.totals:
                Intent intent3 = new Intent(this, TotalsActivity.class);
                intent3.putExtra("int", total);
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

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        setContentView(R.layout.budget_activity_layout);

       // View prevButton = findViewById(R.id.Bbutton1);
       //prevButton.setOnClickListener(this);
        // Receive extra data or parameter
      // Intent intent2 = getIntent();
       // String message = intent2.getStringExtra(MainActivity.EXTRA_DATA2);

       if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                int result = data.getIntExtra("t_budget", -1);
                Toast toastb = Toast.makeText(this, "return value " + result, Toast.LENGTH_LONG);
                toastb.show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }*/
}
