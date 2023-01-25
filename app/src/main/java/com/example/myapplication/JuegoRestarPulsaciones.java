package com.example.myapplication;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class JuegoRestarPulsaciones extends AppCompatActivity {

    private ArrayList<String> players = new ArrayList<String>();
    private TextView jugador1,jugador2,prejuego;
    private ImageView cerveza;
    private ImageButton bJugador1,bJugador2;
    private ConstraintLayout fondo;
    private int i;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_restar_pulsaciones);
        jugador1 = (TextView) findViewById(R.id.textJugador1);
        prejuego = (TextView) findViewById(R.id.textPrejuego);
        cerveza = (ImageView) findViewById(R.id.imgCerveza);
        jugador2 = (TextView) findViewById(R.id.textJugador2);
        bJugador1 = (ImageButton) findViewById(R.id.bJugador1);
        bJugador2 = (ImageButton) findViewById(R.id.bJugador2);
        fondo = (ConstraintLayout) findViewById(R.id.fondoRestar);
        players = getIntent().getStringArrayListExtra("Jugadores");
        String [] jugadores = new String[players.size()];
        for (int nJugadores = 0; nJugadores < jugadores.length; nJugadores++){
            jugadores[nJugadores] = players.get(nJugadores);
        }
        int jugadorplayer1 = (int) (Math.random() * players.size());
        int jugadorplayer2 = (int) (Math.random() * players.size());

        while (jugador1 == jugador2){
            jugadorplayer2 = (int) (Math.random() * players.size());
        }
        jugador1.setText(jugadores[jugadorplayer1]);
        jugador2.setText(jugadores[jugadorplayer2]);
        prejuego.setText(jugadores[jugadorplayer1]+" y "+jugadores[jugadorplayer2]+" ¡Teneis que presionar el boton lo más rápido que podáis, cuando el contador llegue a 0 ganas");
        int finalJugadorplayer = jugadorplayer2;
        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prejuego.setVisibility(View.INVISIBLE);
                jugador1.setVisibility(View.INVISIBLE);
                jugador2.setVisibility(View.INVISIBLE);
                bJugador1.setVisibility(View.VISIBLE);
                bJugador2.setVisibility(View.VISIBLE);
                cerveza.setVisibility(View.VISIBLE);
                bJugador1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cerveza.setTranslationY(i);
                        i+=50;
                        if (cerveza.getY() >= bJugador2.getY()-150){
                            prejuego.setText("¡Has ganado "+jugadores[jugadorplayer1]+"!");
                            prejuego.setVisibility(View.VISIBLE);
                            bJugador1.setVisibility(View.INVISIBLE);
                            bJugador2.setVisibility(View.INVISIBLE);
                            cerveza.setVisibility(View.INVISIBLE);
                            fondo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        sleep(1000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    Random generator = new Random();
                                    int number = generator.nextInt(4) + 1;
                                    Class activity =null;
                                    switch (number) {
                                        case 1:
                                            activity = Ruleta.class;
                                            break;
                                        case 2:
                                            activity = startedGame.class;
                                            break;
                                        case 3:
                                            activity = Mimica.class;
                                            break;
                                        case 4:
                                            activity = JuegoRestarPulsaciones.class;
                                            break;
                                        default:
                                            activity = JuegoPulsarBoton.class;
                                            break;
                                    }
                                    Intent i = new Intent(JuegoRestarPulsaciones.this, activity);
                                    i.putStringArrayListExtra("Jugadores", (ArrayList<String>) players);
                                    startActivity(i);
                                    overridePendingTransition(0, 0);
                                }
                            });
                        }
                    }
                });
                bJugador2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cerveza.setTranslationY(i);
                        i-=50;
                        if (cerveza.getY() <=  bJugador1.getY()){
                            prejuego.setText("¡Has ganado "+jugadores[finalJugadorplayer]+"!");
                            prejuego.setVisibility(View.VISIBLE);
                            bJugador1.setVisibility(View.INVISIBLE);
                            bJugador2.setVisibility(View.INVISIBLE);
                            cerveza.setVisibility(View.INVISIBLE);
                            fondo.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    try {
                                        sleep(1000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    Random generator = new Random();
                                    int number = generator.nextInt(3) + 1;
                                    Class activity =null;
                                    switch (number) {
                                        case 1:
                                            activity = Ruleta.class;
                                            break;
                                        case 2:
                                            activity = startedGame.class;
                                            break;
                                        default:
                                            activity = JuegoPulsarBoton.class;
                                            break;
                                    }
                                    Intent i = new Intent(JuegoRestarPulsaciones.this, activity);
                                    i.putStringArrayListExtra("Jugadores", (ArrayList<String>) players);
                                    startActivity(i);
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}