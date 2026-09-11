package com.example.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuNivelesActivity extends AppCompatActivity {
    Button btnNivel1;
    Button btnNivel2;
    Button btnNivel3;

    TextView txtPuntos;

    int puntosTotales = 0;


    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_niveles);


        btnNivel1 = findViewById(R.id.btnNivel1);
        btnNivel2 = findViewById(R.id.btnNivel2);
        btnNivel3 = findViewById(R.id.btnNivel3);

        txtPuntos = findViewById(R.id.txtPuntos);


        txtPuntos.setText("Puntos: " + puntosTotales);

        //el launcher aca pues recibire los puntos ganados de los niveles jugados :]

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK) {

                        Intent data = result.getData();

                        if (data != null) {

                            int puntosRecibidos =
                                    data.getIntExtra("puntosGanados", 0);

                            // Sumar puntos al total

                            puntosTotales += puntosRecibidos;

                            // Actualizar TextView

                            txtPuntos.setText(
                                    "Puntos: " + puntosTotales
                            );
                        }
                    }
                }
        );

        btnNivel1.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MenuNivelesActivity.this,
                            JuegoNivel1Activity.class);

            intent.putExtra("nivel", 1);

            launcher.launch(intent);

        });

        btnNivel2.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MenuNivelesActivity.this,
                            JuegoNivel2Activity.class);

            intent.putExtra("nivel", 2);

            launcher.launch(intent);

        });

        btnNivel3.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MenuNivelesActivity.this,
                            JuegoNivel3Activity.class);

            intent.putExtra("nivel", 3);

            launcher.launch(intent);

        });

    }

}