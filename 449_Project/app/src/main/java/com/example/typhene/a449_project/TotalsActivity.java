package com.example.typhene.a449_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class TotalsActivity extends AppCompatActivity {

    int number=MainActivity.total;
    private void updateTotal() {
        TextView t = (TextView) findViewById(R.id.total_num);
        t.setText(("$") + Integer.toString(number));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals_layout);

        // Receive extra data or parameter
        Intent intent3 = getIntent();
        String message = intent3.getStringExtra(MainActivity.EXTRA_DATA3);

        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        /*Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

        Intent i = getIntent();

        int number = i.getIntExtra("int", -1);
        updateTotal();

// NUMBER = 5
// STR = "HELLO"
        Toast toast2 = Toast.makeText(context, "The Total is $" + number, duration);
        toast2.show();
        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // count = extras.getInt(START_COUNT_PARM);
            int Total = extras.getInt(MainActivity.EXTRA_DATA3);
            Toast toast2 = Toast.makeText(context, "The Total is " + Total, duration);
            toast2.show();
        }*/
        }

    }
