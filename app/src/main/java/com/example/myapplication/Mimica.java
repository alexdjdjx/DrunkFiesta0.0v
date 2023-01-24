package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;


public class Mimica extends AppCompatActivity {
private ArrayList<String> lista;
private ConstraintLayout fondo;
private TextView textView,personajes,texto;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimica);
        fondo = (ConstraintLayout) findViewById(R.id.fondoMimica);
        lista = getIntent().getStringArrayListExtra("Jugadores");
        textView = (TextView) findViewById(R.id.textViewMimica);
        texto = (TextView) findViewById(R.id.textoMimica);
        personajes = (TextView) findViewById(R.id.textPersonajesMimica);
        String [] jugadores = new String[lista.size()];
        for (int nJugadores = 0; nJugadores < jugadores.length; nJugadores++){
            jugadores[nJugadores] = lista.get(nJugadores);
        }
        textView.setText(jugadores[(int) (Math.random() * lista.size())]);
        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] imitables = {"Bad bunny","Shakira","Un pato","Quevedo","Romeo Santos"};
                personajes.setText("Imita a "+imitables[(int)(Math.random()*imitables.length)] + " quien lo acierte reparte 2 tragos");
                personajes.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);
                texto.setVisibility(View.INVISIBLE);
                fondo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Random generator = new Random();
                        int number = generator.nextInt(4) + 1;
                        Class activity = null;
                        switch (number) {
                            case 1:
                                activity = Ruleta.class;
                                break;
                            case 2:
                                activity = startedGame.class;
                                break;
                            case 3:
                                activity= Mimica.class;
                                break;
                            default:
                                activity = JuegoPulsarBoton.class;
                                break;

                        }
                        Intent i = new Intent(Mimica.this, activity);
                        i.putStringArrayListExtra("Jugadores", (ArrayList<String>) lista);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0, 0);
                    }
                });
            }
        });
    }

}