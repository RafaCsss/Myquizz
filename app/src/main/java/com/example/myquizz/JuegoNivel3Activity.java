package com.example.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JuegoNivel3Activity extends AppCompatActivity {

    ImageButton c1, c2, c3, c4,
            c5, c6, c7, c8,
            c9, c10, c11, c12,
            c13, c14, c15, c16;

    ImageButton[] cartas = new ImageButton[16];

    Button btnComprobar;

    TextView puntuacion;

    int[] imagenes = {

            R.drawable.circulo_amarillo,
            R.drawable.cuadrado_rojo,
            R.drawable.estrella_azul,
            R.drawable.triangulo_verde,

            R.drawable.corazon_rosa,
            R.drawable.luna_amarilla,
            R.drawable.sol,
            R.drawable.diamante,

            R.drawable.cuadrado_rojo,
            R.drawable.circulo_amarillo,
            R.drawable.triangulo_verde,
            R.drawable.estrella_azul,

            R.drawable.diamante,
            R.drawable.sol,
            R.drawable.luna_amarilla,
            R.drawable.corazon_rosa
    };

    int seleccion1 = -1;
    int seleccion2 = -1;

    boolean[] descubiertas = new boolean[16];

    int puntos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juego_nivel3);

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

        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);

        c13 = findViewById(R.id.c13);
        c14 = findViewById(R.id.c14);
        c15 = findViewById(R.id.c15);
        c16 = findViewById(R.id.c16);

        cartas[0] = c1;
        cartas[1] = c2;
        cartas[2] = c3;
        cartas[3] = c4;

        cartas[4] = c5;
        cartas[5] = c6;
        cartas[6] = c7;
        cartas[7] = c8;

        cartas[8] = c9;
        cartas[9] = c10;
        cartas[10] = c11;
        cartas[11] = c12;

        cartas[12] = c13;
        cartas[13] = c14;
        cartas[14] = c15;
        cartas[15] = c16;

        for (int i = 0; i < 16; i++) {
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