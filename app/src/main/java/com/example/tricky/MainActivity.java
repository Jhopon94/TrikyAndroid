package com.example.tricky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cajitaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajitaUsuario = (EditText) findViewById(R.id.cajitaNombreUsu);
    }

    public void ObtenerNombreUsu(View vista){
        String cadenaAux = cajitaUsuario.getText().toString();
        if(cadenaAux.isEmpty())Toast.makeText(this, "Debes ingresar un nombre de usuario", Toast.LENGTH_SHORT).show();
        else CambiarVista(cadenaAux);
    }

    private void CambiarVista(String nombreUsu){
        //Instanciar la otra activity
        Intent intent = new Intent(MainActivity.this, juego.class);
        //Pasar el nombre del usuario
        intent.putExtra("nombreUsu", nombreUsu);
        //Iniciar el activity
        startActivity(intent);
    }
}