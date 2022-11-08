package com.vidyut.abortionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final int LENGTH_SHORT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setTitle("Select a State");


        Spinner spinner = (Spinner) findViewById(R.id.states_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.myCoordinatorLayout);

        Button goButton = findViewById(R.id.button);

        goButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String selectedState = spinner.getSelectedItem().toString();

                if (selectedState.equals("Select a State")) {
                    Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Please select a state from the drop down menu",
                                    Snackbar.LENGTH_SHORT)
                            .show();
                } else {

                    Bundle bundleMain = new Bundle();
                    bundleMain.putString("selectedState", selectedState);


                    Intent intentMain = new Intent(MainActivity.this, StateInfo.class);
                    intentMain.putExtras(bundleMain);
                    startActivity(intentMain);
                }
            }
        }
        );
    }

}