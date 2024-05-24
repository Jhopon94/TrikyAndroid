package com.example.tricky;

import androidx.appcompat.app.AppCompatActivity;
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
        Toast.makeText(this, cadenaAux, Toast.LENGTH_LONG).show();
    }
}