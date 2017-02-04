package com.example.jaind.instaveritas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchActivity extends Activity {

    private static final String[] typeOfSearch = {"Title", "Director", "Actor"};
    TextView searchButton;
    EditText searchName;
    Context context;
    private Spinner searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = this;

        searchName = (EditText) findViewById(R.id.search_name);


        searchType = (Spinner) findViewById(R.id.search_type);
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, typeOfSearch);

        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchType.setAdapter(userAdapter);


        searchButton = (TextView) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Search : ", searchName.getText().toString() + " : " + searchType.getSelectedItem().toString());
                if (!searchType.getSelectedItem().toString().equals("Title")) {

                    Appcontroller.search(context, searchName.getText().toString(), searchType.getSelectedItem().toString());
                } else {
                    Appcontroller.searchTitle(context, searchName.getText().toString());
                }


            }

        });

    }

}

