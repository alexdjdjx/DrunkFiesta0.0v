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
import java.util.Random;

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

                if(players.size() > 1) {
                    Random generator = new Random();
                    int number = generator.nextInt(2) + 1;
                    Class activity =null;
                    switch (number) {
                        case 1:
                            activity = Ruleta.class;
                            break;
                        default:
                            activity = startedGame.class;
                            break;
                    }
                    Intent i = new Intent(Juego.this, activity);
                    i.putStringArrayListExtra("Jugadores", (ArrayList<String>) players);
                    startActivity(i);
                }else{
                    Toast.makeText(Juego.this,"Minimo 2 jugadores para empezar",Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int listItem, long l) {
                new AlertDialog.Builder(Juego.this).setTitle("¿Quieres eliminar el nombre "+ players.get(listItem)+"?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        players.remove(listItem);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();

                return false;
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