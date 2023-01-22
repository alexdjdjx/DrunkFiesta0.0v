package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Juego extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @SuppressLint("MissingInflatedId")


    private Button regresar;
    private Button anyadir;
    private Button empezar;
    private Button showMdialog;
    private ListView listView;
    private EditText mTexto;
    private List<String> players = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        empezar = (Button)findViewById(R.id.bEmpezar);
        mTexto = (EditText)findViewById(R.id.addText);
        anyadir = (Button)findViewById(R.id.button);
        regresar = (Button)findViewById(R.id.bRegresar);
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mTexto.getText().toString().equalsIgnoreCase("")) {
                    String texto = mTexto.getText().toString().trim();
                    players.add(texto);
                    mTexto.getText().clear();
                    adapter = new ArrayAdapter<>(Juego.this, android.R.layout.simple_list_item_1, players);
                    listView.setAdapter(adapter);
                }
            }
        });

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Juego.this, Ruleta.class);
                i.putStringArrayListExtra("Jugadores", (ArrayList<String>) players);
                startActivity(i);
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                case R.id.bRegresar:
                Intent i = new Intent(Juego.this, MainActivity.class);
                startActivity(i);
                }
            }
        });
    }





    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"Item clicked"+i, Toast.LENGTH_SHORT).show();
    }
}