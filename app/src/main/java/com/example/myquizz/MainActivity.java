package com.example.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnJugar = findViewById(R.id.btnJugar);

        btnJugar.setOnClickListener(v -> {
            Intent intent =
                    new Intent(MainActivity.this,
                            MenuNivelesActivity.class);

            startActivity(intent);

        });

    }

}