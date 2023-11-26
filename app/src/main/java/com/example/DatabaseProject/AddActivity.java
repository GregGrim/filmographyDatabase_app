package com.example.DatabaseProject;

import static com.example.DatabaseProject.StartActivity.sqdb;
import static com.example.DatabaseProject.StartActivity.sqh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    TextInputEditText filmInput;
    TextInputEditText yearInput;
    TextInputEditText roleInput;
    TextInputEditText directorInput;
    TextView errorMsg;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setupControls();

    }
    @SuppressLint("SetTextI18n")
    public void setupControls() {
        filmInput = findViewById(R.id.filmInputAdd);
        yearInput = findViewById(R.id.yearInputAdd);
        roleInput = findViewById(R.id.roleInputAdd);
        directorInput = findViewById(R.id.directorInputAdd);
        errorMsg = findViewById(R.id.errorWindow);
        addButton = findViewById(R.id.buttonAddRecord);
        addButton.setOnClickListener(v->{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(filmInput.getText()));
            params.add(String.valueOf(yearInput.getText()));
            params.add(String.valueOf(roleInput.getText()));
            params.add(String.valueOf(directorInput.getText()));

            if(params.get(0).equals("")||
                    params.get(1).equals("")||
                    params.get(2).equals("")||
                    params.get(3).equals("")) {
                errorMsg.setTextColor(Color.RED);
                errorMsg.setText("Missing one of the fields!");
            } else {
                sqh.addNewRecord(sqdb, params);
                errorMsg.setTextColor(Color.BLACK);
                errorMsg.setText("Record successfully added!");
            }
        });
    }
}