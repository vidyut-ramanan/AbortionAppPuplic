package com.vidyut.abortionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class StateInfo extends AppCompatActivity {
    String state;
    UrlConstructor.Info desiredInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_info);
        Bundle bundleMain = getIntent().getExtras();
        this.getSupportActionBar().setTitle(bundleMain.getString("selectedState"));


        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);




        Button gestational = findViewById(R.id.gestLim);
        Button minors = findViewById(R.id.minors);
        Button waiting = findViewById(R.id.waitPeriod);



        TextView stateText = findViewById(R.id.stateName);

        state = bundleMain.getString("selectedState");

        stateText.setText(state);

        Button[] buttons = {gestational, minors, waiting};

        for (Button b : buttons) {

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StateInfo.this, StateSpecificInfo.class);
                    intent.putExtra("state", state);
                    switch (b.getText().toString()) {
                        case "Gestational Limits":
                            intent.putExtra("info", "Gestational Limits");
                            break;
                        case "Insurance Coverage":
                            intent.putExtra("info", "Insurance Coverage");
                            break;
                        case "Minors":
                            intent.putExtra("info", "Minors");
                            break;
                        case "Waiting Period":
                            intent.putExtra("info", "Waiting Period");
                            break;
                    }
                    startActivity(intent);
                }
            });
        }


    }

    //back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
