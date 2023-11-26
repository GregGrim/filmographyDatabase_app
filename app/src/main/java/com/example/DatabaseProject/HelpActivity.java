package com.example.DatabaseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    TextView helpTextView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        helpTextView = findViewById(R.id.HelpTextView);
        helpTextView.setText("If you have any issues while using the program, please contact our support team" +
                "\ns23007636@mail.glyndwr.ac.uk");
        helpTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}