package com.example.DatabaseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView aboutTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutTextView = findViewById(R.id.AboutTextView);
        aboutTextView.setText("Program has been created by the student of Wrexham University Hryhorii Hrymailo.\nversion: 1.0.0");
        aboutTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}