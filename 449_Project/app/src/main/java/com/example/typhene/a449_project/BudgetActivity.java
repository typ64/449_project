package com.example.typhene.a449_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

public class BudgetActivity extends AppCompatActivity{// implements View.OnClickListener {
    /*private int t_budget= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity_layout);

        //View prevButton = findViewById(R.id.Bbutton1);
       // prevButton.setOnClickListener(this);
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

    public void sendMessage(View view)
    {
        //Assert.assertNotNull(arg0);
        Intent start_main = new Intent(BudgetActivity.this, MainActivity.class);
        startActivity(start_main);

    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity_layout);

        // Receive extra data or parameter
        Intent intent2 = getIntent();
        String message = "Enter your budget";

        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toastb = Toast.makeText(context, text, duration);
        toastb.show();
    }
    public void sendMessage(View view)
    {
        TextView tv = (TextView) findViewById(R.id.editBText1);
       int t_budget = Integer.parseInt(tv.getText().toString());
        Intent start_main = new Intent(BudgetActivity.this, MainActivity.class);
        start_main.putExtra("int_value", t_budget);
        startActivity(start_main);

    }
}
