package com.example.oving7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity {

    DatabaseManager db;
    SharedPreferences perf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set background Color
        perf = getDefaultSharedPreferences(this);
        setColor();

        try {
            setupFile();
            dataBase();
            ArrayList<String> res = db.getAllBooks();
            System.out.println("dette er her:");

            showResults(res);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Button bookBtn = findViewById(R.id.bookBtn);
        Button autBtn = findViewById(R.id.autBtn);
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> res = db.getAllBooks();
                showResults(res);
            }
        });
        autBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> res = db.getAllAuthors();
                showResults(res);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setColor();
    }

    public void setupFile() {
        String s1 = "Ian Evangelista, Harry Potter 1";
        String s2 = "Nikolai Dokken, Harry Potter 2";
        String s3 = "Emir Derouiche, Harry Potter 3";
        String s4 = "Magnus Farstad, Harry Potter 4";
        String s5 = "Zaim Imran, Harry Potter 5";
        String[] strings = {s1, s2, s3, s4, s5};

        String hel = "";
        for (String s : strings) {
            hel += s + ", ";
        }
        fileManager.writeToFile(getFilesDir(), "text.txt", hel);
        System.out.println("forbi skriving av fil --------------------");

    }

    public String[]fromFile(){
        String fil = fileManager.readFile(getFilesDir(), "text.txt");
        try {
            String[] forkort = fil.split("[,\\:]");
            return forkort;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void dataBase(){
        String[] fil = fromFile();
        try {
            db = new DatabaseManager(this);
            db.clean();
            for (int i = 1; i < fil.length; i += 2) {
                db.insert(fil[i-1].trim(),fil[i].trim());
            }
            System.out.println("forbi skriving til database --------------------");

        } catch (Exception e) {
        }
    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = findViewById(R.id.tw1);
        t.setText(res);
    }

    private void setColor() {
        RelativeLayout t = findViewById(R.id.background);
        String value = perf.getString("color", "#424242");
        Log.d("COLOR", value);
        t.setBackgroundColor(Color.parseColor(value));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            // launch settings activity
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
