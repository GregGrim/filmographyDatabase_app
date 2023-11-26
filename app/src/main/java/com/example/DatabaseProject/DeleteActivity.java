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

public class DeleteActivity extends AppCompatActivity {


    Button buttonDeleteRecord;
    TextView errorText;
    TextInputEditText inputID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        setupControls();
    }
    @SuppressLint("SetTextI18n")
    public void setupControls() {
        errorText = findViewById(R.id.errorTextDelete);
        inputID = findViewById(R.id.inputdelete);
        buttonDeleteRecord = findViewById(R.id.buttonDeleteRecord);
        buttonDeleteRecord.setOnClickListener(v->{
            String id = String.valueOf(inputID.getText());
            if(!id.matches("\\d+")){
                errorText.setTextColor(Color.RED);
                errorText.setText("Enter numeric ID !");
            } else if(!sqh.checkExistence(sqdb, Integer.parseInt(id))) {
                errorText.setTextColor(Color.RED);
                errorText.setText("No such ID in database !");
            } else {
                sqh.deleteRecordByID(sqdb, Integer.parseInt(id));
                errorText.setTextColor(Color.BLACK);
                errorText.setText("Record has been deleted !");
                inputID.setText("");
            }
        });
    }
}