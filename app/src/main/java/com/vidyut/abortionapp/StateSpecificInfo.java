package com.vidyut.abortionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.vidyut.abortionapp.json.interpreter.GestationalInfo;
import com.vidyut.abortionapp.json.interpreter.InsuranceInfo;
import com.vidyut.abortionapp.json.interpreter.MinorsInfo;
import com.vidyut.abortionapp.json.interpreter.WaitingInfo;

import org.json.JSONException;

public class StateSpecificInfo extends AppCompatActivity {

    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_specific_info);
        TextView scrollingView = findViewById(R.id.info_text_view);

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        this.getSupportActionBar().setTitle(intent.getStringExtra("state"));
        state = intent.getStringExtra("state");
        UrlConstructor.Info info = null;

        switch (intent.getStringExtra("info")) {
            case "Gestational Limits":
                info = UrlConstructor.Info.Gestation;
                break;
            case "Insurance Coverage":
                info = UrlConstructor.Info.Insurance;
                break;
            case "Minors":
                info = UrlConstructor.Info.Minors;
                break;
            case "Waiting Period":
                info = UrlConstructor.Info.Waiting;
                break;
        }

        UrlConstructor urlConstructor = new UrlConstructor(intent.getStringExtra("state"), info);
        HttpGet httpGet = new HttpGet(urlConstructor.getURLString());

        httpGet.sendGet();

        while (!httpGet.requestDone) {
            System.out.println("loading");
        }

        if (!httpGet.requestFailed) {
            String response = httpGet.getResponse();

            switch (intent.getStringExtra("info")) {
                case "Gestational Limits":
                    System.out.println("Gestational Limits");
                    GestationalInfo gestationalInfo = new GestationalInfo(response, state);
                    scrollingView.setText(gestationalInfo.text);
                    break;
                case "Insurance Coverage":
                    System.out.println("Insurance Coverage");
                    InsuranceInfo insuranceInfo = new InsuranceInfo(response, state);
                    scrollingView.setText(insuranceInfo.text);
                    break;
                case "Minors":
                    System.out.println("Minors");
                    MinorsInfo minorsInfo = new MinorsInfo(response, state);
                    scrollingView.setText(minorsInfo.text);
                    break;
                case "Waiting Period":
                    System.out.println("Waiting Period");
                    WaitingInfo waitingInfo = new WaitingInfo(response, state);
                    scrollingView.setText(waitingInfo.text);
                    break;
            }

        } else {
            scrollingView.setText(R.string.could_not_load);
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