package com.example.oving2_b;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int request_random = 1;
    int request_random2 = 2;

    private int svar;
    private int grense;
    private int tilfTall;
    private int tall1;
    private int tall2;

    TextView num1;
    TextView num2;
    EditText txtAns;
    EditText txtLimit;
    Button btnMulti;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMulti = findViewById(R.id.btnMulti);
        btnAdd = findViewById(R.id.btnAdd);
        txtAns = findViewById(R.id.txtAns);
        txtLimit = findViewById(R.id.txtLimit);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    reCheck(v);
                    knappTrykk(tall1*tall2, v);
                }catch (Exception e){
                    System.out.print("Feil " + e);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    reCheck(v);
                    knappTrykk(tall1+tall2, v);
                }catch (Exception e){
                    System.out.print("Feil " + e);
                }
            }
        });

    }

    public void knappTrykk(int i, View v){

        if( svar == i){
            Toast.makeText(getApplicationContext(), getString(R.string.riktig), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.feil) + " " + Integer.toString(i), Toast.LENGTH_SHORT).show();
        }
        nyeTall(v);

    }

    public void reCheck(View v){
        tall1 = Integer.parseInt(num1.getText().toString());
        tall2 = Integer.parseInt(num2.getText().toString());
        svar = Integer.parseInt(txtAns.getText().toString());
        grense = Integer.parseInt(txtLimit.getText().toString());
    }

    public void nyeTall(View v){
        Intent intent = new Intent(MainActivity.this,RandomActivity.class);
        intent.putExtra("Grense",grense);
        startActivityForResult(intent, request_random);
        startActivityForResult(intent, request_random2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request_random && resultCode == RESULT_OK){
            tilfTall = data.getIntExtra("randTall", tilfTall);
            num1.setText(Integer.toString(tilfTall));
        }
        if(requestCode==request_random2 && resultCode == RESULT_OK){
            tilfTall = data.getIntExtra("randTall", tilfTall);
            num2.setText(Integer.toString(tilfTall));
        }
    }
}