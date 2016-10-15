package com.example.typhene.a449_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

public abstract class BudgetActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

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
}