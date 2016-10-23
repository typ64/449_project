package com.example.typhene.a449_project;

/*public abstract class BudgetActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

   private int s_budget = 0;

    class Item
    {
        public int budget;

        @Override
        public String toString(){
            return  "Your budget is $" + budget;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity_layout);


        View prevButton = findViewById(R.id.Bbutton1);
        prevButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        EditText bud_text = (EditText)findViewById(R.id.editBText1);
        String b = bud_text.getText().toString();
        int s_budget = Integer.parseInt (bud_text.getText().toString());
    }
}*/

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

public class BudgetActivity extends AppCompatActivity implements View.OnClickListener {
    private int t_budget= 0;

    private void updateBudget() {
        TextView t = (TextView) findViewById(R.id.rem_bud_num);
        t.setText(("$") + Integer.toString(t_budget));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity_layout);

        View prevButton = findViewById(R.id.Bbutton1);
        prevButton.setOnClickListener(this);
        // Receive extra data or parameter
        Intent intent2 = getIntent();
        String message = intent2.getStringExtra(MainActivity.EXTRA_DATA2);

        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        returnValueFromActivity();
    }
    private void returnValueFromActivity() {
        Intent intent = new Intent();
        intent.putExtra("t_budget", t_budget);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View arg0) {
        Assert.assertNotNull(arg0);
        updateBudget();
    }
}
