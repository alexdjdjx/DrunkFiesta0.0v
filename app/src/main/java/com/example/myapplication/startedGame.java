package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class startedGame extends AppCompatActivity {
    private TextView textView;
    private ArrayList<String> lista = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_game);
       textView = (TextView) findViewById(R.id.nJugador);
        lista = getIntent().getStringArrayListExtra("Jugadores");
        String [] jugadores = new String[lista.size()];
        for (int nJugadores = 0; nJugadores < jugadores.length; nJugadores++){
            jugadores[nJugadores] = lista.get(nJugadores);
        }
        textView.setText(jugadores[(int) (Math.random() * lista.size())]);

    }
}