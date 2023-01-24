package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class startedGame extends AppCompatActivity {
    private TextView textView;
    private String[] retos = new String[5];
    private ArrayList<String> lista = new ArrayList<String>();
    private TextView textoReto;
    private ConstraintLayout fondo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_game);
        fondo = (ConstraintLayout) findViewById(R.id.fondoRetos2);
        textView = (TextView) findViewById(R.id.nJugador);
        textoReto = (TextView) findViewById(R.id.textoReto);
        lista = getIntent().getStringArrayListExtra("Jugadores");
        String[] jugadores = new String[lista.size()];
        for (int nJugadores = 0; nJugadores < jugadores.length; nJugadores++) {
            jugadores[nJugadores] = lista.get(nJugadores);
        }

        retos[0] = "Haz el pino o bebe 2 tragos";
        retos[1] = "Baila con una prenda menos o bebe 2 tragos";
        retos[2] = "Envia un whatsapp de te quiero a tus 3 primeros contactos o bebe 3 tragos";
        retos[3] = "Di en voz alta el nombre de la persona que te gusta o bebe 2 tragos";
        retos[4] = "Cuenta un secreto o bebe 3 tragos";
        textoReto.setText(retos[(int) (Math.random() * retos.length)]);
        textView.setText(jugadores[(int) (Math.random() * lista.size())]);
        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random generator = new Random();
                int number = generator.nextInt(3) + 1;
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

                } Intent i = new Intent(startedGame.this,activity);
                i.putStringArrayListExtra("Jugadores", (ArrayList<String>) lista);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
       }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(startedGame.this);
        mBuilder.setTitle("¿Estás seguro de que quieres salir del juego?");
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent il = new Intent(startedGame.this, Juego.class);
                startActivity(il);

            }


        });
        mBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        mBuilder.create().show();
    }
}