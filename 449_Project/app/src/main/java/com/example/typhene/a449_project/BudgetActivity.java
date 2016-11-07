package com.example.typhene.a449_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends AppCompatActivity{
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
        try {
            TextView tv = (TextView) findViewById(R.id.editBText1);
            int t_budget = Integer.parseInt(tv.getText().toString());
            Intent start_main = new Intent(BudgetActivity.this, MainActivity.class);
            start_main.putExtra("int_value", t_budget);
            startActivity(start_main);
        }
        catch(Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Shop Without A Budget?");
            builder.setMessage("You did not enter a budget. Do you want to continue without a budget?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent start_main = new Intent(BudgetActivity.this, MainActivity.class);
                    startActivity(start_main);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }
}
