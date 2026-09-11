package com.example.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JuegoNivel2Activity extends AppCompatActivity {

    ImageButton c1, c2, c3, c4, c5, c6, c7, c8;
    ImageButton[] cartas = new ImageButton[8];
    Button btnComprobar;
    TextView puntuacion;
    int[] imagenes = {

            R.drawable.circulo_amarillo,
            R.drawable.cuadrado_rojo,

            R.drawable.estrella_azul,
            R.drawable.triangulo_verde,

            R.drawable.cuadrado_rojo,
            R.drawable.circulo_amarillo,

            R.drawable.triangulo_verde,
            R.drawable.estrella_azul

    };

    int seleccion1 = -1;
    int seleccion2 = -1;
    boolean[] descubiertas = new boolean[8];
    int puntos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juego_nivel2);

        puntuacion = findViewById(R.id.puntuacion);

        btnComprobar = findViewById(R.id.btnComprobar);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);

        cartas[0] = c1;
        cartas[1] = c2;
        cartas[2] = c3;
        cartas[3] = c4;
        cartas[4] = c5;
        cartas[5] = c6;
        cartas[6] = c7;
        cartas[7] = c8;

        for (int i = 0; i < 8; i++) {
            int index = i;
            cartas[i].setOnClickListener(v -> seleccionar(index));

        }

        btnComprobar.setOnClickListener(v -> comprobar());

    }

    void seleccionar(int i) {
        if (descubiertas[i]) return;
        if (seleccion1 != -1 && seleccion2 != -1) return;

        cartas[i].setImageResource(imagenes[i]);

        if (seleccion1 == -1) {
            seleccion1 = i;

        }

        else if (i != seleccion1) {
            seleccion2 = i;

        }
    }

    void comprobar() {

        if (seleccion1 == -1 || seleccion2 == -1) {
            Toast.makeText(
                    this,
                    "Selecciona 2 cartas",
                    Toast.LENGTH_SHORT
            ).show();

            return;

        }

        if (imagenes[seleccion1] == imagenes[seleccion2]) {
            Toast.makeText(
                    this,
                    "Son iguales",
                    Toast.LENGTH_SHORT
            ).show();

            descubiertas[seleccion1] = true;
            descubiertas[seleccion2] = true;

            puntos += 20;

        }

        else {
            Toast.makeText(
                    this,
                    "No son iguales",
                    Toast.LENGTH_SHORT
            ).show();

            cartas[seleccion1]
                    .setImageResource(R.drawable.carta);

            cartas[seleccion2]
                    .setImageResource(R.drawable.carta);

        }

        seleccion1 = -1;
        seleccion2 = -1;

        puntuacion.setText(
                "Puntos: " + puntos
        );

        boolean gano = true;

        for (int i = 0; i < descubiertas.length; i++) {
            if (!descubiertas[i]) {
                gano = false;

            }

        }

        if (gano) {
            Intent intent = new Intent();
            intent.putExtra(
                    "puntosGanados",
                    puntos
            );

            setResult(RESULT_OK, intent);
            finish();

        }

    }
}