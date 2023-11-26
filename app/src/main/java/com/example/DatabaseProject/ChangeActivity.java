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

public class ChangeActivity extends AppCompatActivity {
    Button buttonChangeFilm;
    Button buttonChangeYear;
    Button buttonChangeRole;
    Button buttonChangeDirector;
    TextView errorTextChange;
    TextInputEditText inputID;
    TextInputEditText inputParameter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        setupControls();
    }
    @SuppressLint("SetTextI18n")
    public void setupControls() {
        errorTextChange = findViewById(R.id.errorTextChange);
        inputID = findViewById(R.id.inputIDChange);
        inputParameter = findViewById(R.id.inputParameterChange);
        buttonChangeFilm = findViewById(R.id.buttonChangeFilm);
        buttonChangeFilm.setOnClickListener(v->{
            String id = String.valueOf(inputID.getText());
            String parameter = String.valueOf(inputParameter.getText());
            if(validateInputs(id, parameter)) {
                sqh.changeFilmByID(sqdb, id, parameter);
                inputParameter.setText("");
                inputID.setText("");
                errorTextChange.setTextColor(Color.BLACK);
                errorTextChange.setText("field 'film' has been changed !");
            }
        });
        buttonChangeYear = findViewById(R.id.buttonChangeYear);
        buttonChangeYear.setOnClickListener(v->{
            String id = String.valueOf(inputID.getText());
            String parameter = String.valueOf(inputParameter.getText());
            if(validateInputs(id, parameter)) {
                sqh.changeYearByID(sqdb, id, parameter);
                inputParameter.setText("");
                inputID.setText("");
                errorTextChange.setTextColor(Color.BLACK);
                errorTextChange.setText("field 'year' has been changed !");
            }
        });
        buttonChangeRole = findViewById(R.id.buttonChangeRole);
        buttonChangeRole.setOnClickListener(v->{
            String id = String.valueOf(inputID.getText());
            String parameter = String.valueOf(inputParameter.getText());
            if(validateInputs(id, parameter)) {
                sqh.changeRoleByID(sqdb, id, parameter);
                inputParameter.setText("");
                inputID.setText("");
                errorTextChange.setTextColor(Color.BLACK);
                errorTextChange.setText("field 'role' has been changed !");
            }
        });
        buttonChangeDirector = findViewById(R.id.buttonChangeDirector);
        buttonChangeDirector.setOnClickListener(v->{
            String id = String.valueOf(inputID.getText());
            String parameter = String.valueOf(inputParameter.getText());
            if(validateInputs(id, parameter)) {
                sqh.changeDirectorByID(sqdb, id, parameter);
                inputParameter.setText("");
                inputID.setText("");
                errorTextChange.setTextColor(Color.BLACK);
                errorTextChange.setText("field 'director' has been changed !");
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public boolean validateInputs(String id, String parameter) {
        if(!id.matches("\\d+")){
            errorTextChange.setTextColor(Color.RED);
            errorTextChange.setText("Enter numeric ID !");
        } else if(!sqh.checkExistence(sqdb, Integer.parseInt(id))) {
            errorTextChange.setTextColor(Color.RED);
            errorTextChange.setText("No such ID in database !");
        } else if(parameter.equals("")){
            errorTextChange.setTextColor(Color.RED);
            errorTextChange.setText("Enter new word !");
        } else return true;
        return false;
    }

}