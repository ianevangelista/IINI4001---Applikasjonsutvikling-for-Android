package com.example.oving1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("Ian");
        menu.add("Evangelista");
        Log.i("onCreateOptionsMenu()","meny laget");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().equals("Ian")){
            Log.i("onOptionsItemSelected()","Ian er trykket av brukeren");
        }
        else if (item.getTitle().equals("Evangelista")){
            Log.i("onOptionsItemSelected()","Evangelista er trykket av brukeren");
        }
        return true;

    }
}