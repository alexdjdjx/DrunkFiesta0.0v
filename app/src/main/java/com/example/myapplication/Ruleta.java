package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta extends AppCompatActivity {
private static final String[] sectors ={"1","2","3","4"};
private static final int [] sectorGrados = new int[sectors.length];
private static final Random random = new Random();
private ImageView ruleta;
    private TextView textView;
    private ArrayList<String> lista = new ArrayList<String>();
private int degree = 0;
private boolean isSpinning = false;
private ConstraintLayout fondo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);
        ruleta = findViewById(R.id.ruleta);
        fondo = findViewById(R.id.fondoRuleta);
        Button boton = findViewById(R.id.bRuleta);
        textView =(TextView) findViewById(R.id.placeHolder);
        getDegreeSectors();
        lista = getIntent().getStringArrayListExtra("Jugadores");
        String [] jugadores = new String[lista.size()];
        for (int nJugadores = 0; nJugadores < jugadores.length; nJugadores++){
            jugadores[nJugadores] = lista.get(nJugadores);
        }
        textView.setText(jugadores[(int) (Math.random() * lista.size())]);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(!isSpinning){
            spin();
            isSpinning = true;
            }
                boton.setVisibility(View.INVISIBLE);
                fondo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!isSpinning) {
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
                            Intent i = new Intent(Ruleta.this, activity);
                            i.putStringArrayListExtra("Jugadores", (ArrayList<String>) lista);
                            startActivity(i);
                            overridePendingTransition(0, 0);
                        }
                    }
                });}

        });
    }
    private void spin(){
        degree = random.nextInt(sectors.length);
        RotateAnimation rotateAnimation = new RotateAnimation(0,(360*sectors.length)+sectorGrados[degree],RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(Ruleta.this,"Te ha tocado la opcion"+sectors[sectors.length-(degree+1)],Toast.LENGTH_SHORT).show();
                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ruleta.startAnimation(rotateAnimation);
    }

    private void getDegreeSectors (){
        int sectorGrado = 360/ sectors.length;
        for (int i = 0; i < sectors.length; i++){
            sectorGrados[i] = (i+1)*sectorGrado;
        }
    }
    public void onBackPressed() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Ruleta.this);
        mBuilder.setTitle("¿Estás seguro de que quieres salir del juego?");
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent il = new Intent(Ruleta.this, Juego.class);
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