package com.example.DatabaseProject;

import static com.example.DatabaseProject.OpenDatabase.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StartActivity extends AppCompatActivity {
    private static String DATABASE_PATH_AND_NAME;
    private static String CHECK_DATABASES_FOLDER;
    private static final String LOG_TAG = "ACTOR_DB";

    OpenDatabase sqh;
    SQLiteDatabase sqdb;
    Context ctx;

    Button searchButton;
    TextInputEditText inputText;
    TextView outputView;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setupControls();
        setupDatabaseStrings();
        setUpDatabase();

        InitDataBase();
    }
    public void setupControls(){
        inputText = findViewById(R.id.inputText);
        scrollView = findViewById(R.id.scrollView);
        outputView = findViewById(R.id.outputView);
        searchButton = findViewById(R.id.buttonSearch);
        searchButton.setOnClickListener(v -> {
            if(String.valueOf(inputText.getText()).equals("all")){
                outputView.setText(sqh.allRecordsInActorTable(sqdb));
            } else{
                if(!String.valueOf(inputText.getText()).equals("")){
                    outputView.setText(sqh.recordsMatchingCodeword(sqdb, String.valueOf(inputText.getText())));
                }
                //TODO seatching by codeword
            }
        });
    }
    public void InitDataBase()
    {
        sqh = new OpenDatabase(this);

        sqdb = sqh.getWritableDatabase();

    }
    @SuppressLint("SdCardPath")
    protected void setupDatabaseStrings()
    {

        DATABASE_PATH_AND_NAME = "/data/data/" + getApplicationContext().getPackageName() +
                "/databases/" + DATABASE_NAME;
        // Used to check if the "databases" folder exists
        CHECK_DATABASES_FOLDER = "/data/data/" + getApplicationContext().getPackageName() +
                "/databases";

        Log.i("DATABASE_PATH_AND_NAME","DATABASE_PATH_AND_NAME = " + DATABASE_PATH_AND_NAME);
        Log.i("CHECK_DATABASES_FOLDER","CHECK_DATABASES_FOLDER = " + CHECK_DATABASES_FOLDER);
    }

    protected void setUpDatabase()
    {
        ctx = this.getBaseContext();
        try
        {
            CopyDataBaseFromAsset();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    protected void CopyDataBaseFromAsset() throws IOException
    {
        InputStream in = ctx.getAssets().open(DATABASE_NAME);
        Log.w( LOG_TAG , "Starting copying...");
        String outputFileName = DATABASE_PATH_AND_NAME;
        File databaseFolder = new File( CHECK_DATABASES_FOLDER );
        if ( !databaseFolder.exists() )
        {
            databaseFolder.mkdir();

            OutputStream out = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ( (length = in.read(buffer)) > 0 )
            {
                out.write(buffer,0,length);
            }
            out.flush();
            out.close();
            in.close();
            Log.w(LOG_TAG, "Completed.");
        }
    }

}