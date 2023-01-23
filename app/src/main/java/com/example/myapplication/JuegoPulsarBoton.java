package com.example.myapplication;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class JuegoPulsarBoton extends AppCompatActivity {
private ArrayList<String> lista = new ArrayList<String>();
private TextView textView;
private TextView player1;
private int [] pulsado = new int[1];
private Button bjugador1,bjugador2;
private TextView player2;
private TextView label;
private TextView label2;
private ConstraintLayout fondo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juego_pulsar_boton);
        pulsado[0]=0;
        label2 = (TextView) findViewById(R.id.labelJBotones2);
        label2.setVisibility(View.INVISIBLE);
        label = (TextView) findViewById(R.id.labelJBotones);
        textView = (TextView) findViewById(R.id.juegoBotonPlaceholder);
        player1 = (TextView) findViewById(R.id.player1PH);
        player2 = (TextView) findViewById(R.id.player2PH);
        fondo = (ConstraintLayout) findViewById(R.id.fondo2);
        bjugador1 = (Button) findViewById(R.id.buttonjg1);
        bjugador2 = (Button) findViewById(R.id.buttonjg2);

        lista = getIntent().getStringArrayListExtra("Jugadores");
        String [] jugadores = new String[lista.size()];
        for (int nJugadores = 0; nJugadores < jugadores.length; nJugadores++){
            jugadores[nJugadores] = lista.get(nJugadores);
        }
        int jugador1 = (int) (Math.random() * lista.size());
        int jugador2 = (int) (Math.random() * lista.size());

        while (jugador1 == jugador2){
            jugador2 = (int) (Math.random() * lista.size());
        }
        bjugador1.setText("¡Pulsame "+jugadores[jugador1]+"!");
        bjugador2.setText("¡Pulsame "+jugadores[jugador2]+"!");
        textView.setText(jugadores[jugador1] +" y "+jugadores[jugador2]);
        player1.setText("Aquí "+jugadores[jugador1]);
        player2.setText("Aquí "+jugadores[jugador2]);

        int finalJugador = jugador2;
        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.INVISIBLE);
                player1.setVisibility(View.INVISIBLE);
                player2.setVisibility(View.INVISIBLE);
                label.setVisibility(View.INVISIBLE);
                label2.setVisibility(View.VISIBLE);


                    Handler handler = new Handler(Looper.getMainLooper());
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {

                                label2.setVisibility(View.INVISIBLE);
                                int numeroAleatorio = (int) (Math.random() * (3 - 1) + 1);
                                if (numeroAleatorio == 1) {

                                        bjugador1.setVisibility(View.VISIBLE);

                                    bjugador1.setText("¡Pulsame "+jugadores[jugador1]+"!");
                                    bjugador2.setText("¡Pulsame "+jugadores[finalJugador]+"!");

                                    bjugador1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            fondo.setOnClickListener(null);
                                            bjugador1.setVisibility(View.INVISIBLE);
                                            bjugador2.setVisibility(View.INVISIBLE);
                                            label2.setText("¡Has ganado "+jugadores[jugador1]+"!");
                                            label2.setVisibility(View.VISIBLE);

                                        }
                                    });


                                        bjugador2.setVisibility(View.VISIBLE);

                                    bjugador2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            fondo.setOnClickListener(null);
                                            bjugador1.setVisibility(View.INVISIBLE);
                                            bjugador2.setVisibility(View.INVISIBLE);
                                            label2.setText("¡Has ganado "+jugadores[finalJugador]+"!");
                                            label2.setVisibility(View.VISIBLE);
                                        }
                                    });

                                } else {

                                    handler.postDelayed(this::run,(int) (Math.random() * 5000)+1000);

                                    bjugador1.setText("¡NO me Pulses "+jugadores[jugador1]+"!");

                                        bjugador1.setVisibility(View.VISIBLE);

                                    bjugador1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            fondo.setOnClickListener(null);
                                            bjugador1.setVisibility(View.INVISIBLE);
                                            bjugador2.setVisibility(View.INVISIBLE);
                                            label2.setText("Has perdido "+jugadores[jugador1]+" :(");
                                            label2.setVisibility(View.VISIBLE);
                                            handler.removeCallbacksAndMessages(null);

                                        }
                                    });
                                    bjugador2.setText("¡NO me Pulses "+jugadores[finalJugador]+"!");


                                        bjugador2.setVisibility(View.VISIBLE);

                                    bjugador2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            fondo.setOnClickListener(null);
                                            bjugador1.setVisibility(View.INVISIBLE);
                                            bjugador2.setVisibility(View.INVISIBLE);
                                            label2.setText("Has perdido "+jugadores[finalJugador]+" :(");
                                            label2.setVisibility(View.VISIBLE);
                                            handler.removeCallbacksAndMessages(null);
                                        }
                                    });





                                }



                        };
                            };



                    handler.postDelayed(runnable, (int) (Math.random() * 5500)+1000);


            }

        });

            }
        };

