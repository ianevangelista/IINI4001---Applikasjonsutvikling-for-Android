package com.example.oving2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private int grense = 100;
    int request_random = 1;
    private int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,RandomActivity.class);
                  intent.putExtra("Grense",grense);
                  startActivityForResult(intent, request_random);
              }
        }
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request_random && resultCode == RESULT_OK){
            rand = data.getIntExtra("randTall", rand);
            Toast.makeText(this, String.valueOf(rand), Toast.LENGTH_LONG).show();
        }
    }


}