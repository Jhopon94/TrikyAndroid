package com.example.tricky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class juego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        ////// Recuperación de datos de la activity anterior /////////
        Intent intent = getIntent();
        // Obtener el valor enviado de la otra activity
        String nombreUsu = intent.getStringExtra("nombreUsu");

        //Usar el valor recuperado
        TextView etNombreUsu = (TextView) findViewById(R.id.etNombreUsu);
        etNombreUsu.setText(nombreUsu);
    }
}