package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Juego extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Button regresar;
        Button showMdialog;
        regresar = (Button)findViewById(R.id.bRegresar);
        showMdialog = (Button)findViewById(R.id.btnShowMdialog);
        showMdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Juego.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_spinner,null);
                mBuilder.setTitle("Elige el numero de jugadores");
                Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Juego.this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.NumeroJugadores));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Elige...")){
                            Toast.makeText(Juego.this,mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();

                        }
                    }
                });
                mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Juego.this,"Cancelaste",Toast.LENGTH_SHORT).show();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( Juego.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}