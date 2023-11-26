package com.example.DatabaseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button aboutButton;
    Button helpButton;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupControls();
    }
    @SuppressLint("SetTextI18n")
    private void setupControls() {
        text = findViewById(R.id.nameView);
        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        text.setText("DiCaprio Filmography Database application");
        startButton = findViewById(R.id.buttonStart);
        startButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(aboutIntent);
        });
        aboutButton = findViewById(R.id.buttonAbout);
        aboutButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        });
        helpButton = findViewById(R.id.buttonHelp);
        helpButton.setOnClickListener(view -> {
            Intent aboutIntent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(aboutIntent);
        });
    }
}