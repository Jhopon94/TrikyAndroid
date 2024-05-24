package com.example.tricky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class juego extends AppCompatActivity {

    //matriz del juego
    String[][] matrizTriky;


    //////// Obtener celdas de la matriz ////////
    TextView etUno;
    TextView etDos;
    TextView etTres;
    TextView etCuatro;
    TextView etCinco;
    TextView etSeis;
    TextView etSiete;
    TextView etOcho;
    TextView etNueve;
    /////////////////////////////////////////////

    // Variable para turno
    boolean meToca;

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

        InicializarEtiqutas();
        meToca = false;

        //Poner texto vacío en cada celda de triky
        matrizTriky= new String[3][3];
        AsignarEmpty();
        LimpiarEtiquetas();
        TurnoMaquina();
    }

    private void InicializarEtiqutas(){
        etUno = (TextView) findViewById(R.id.etUno);
        etDos = (TextView) findViewById(R.id.etDos);
        etTres = (TextView) findViewById(R.id.etTres);
        etCuatro = (TextView) findViewById(R.id.etCuatro);
        etCinco = (TextView) findViewById(R.id.etCinco);
        etSeis = (TextView) findViewById(R.id.etSeis);
        etSiete = (TextView) findViewById(R.id.etSiete);
        etOcho = (TextView) findViewById(R.id.etOcho);
        etNueve = (TextView) findViewById(R.id.etNueve);
    }

    private void AsignarEmpty(){
        for(int fila = 0; fila < 3; fila++){
            for(int columna = 0; columna < 3; columna++){
                matrizTriky[fila][columna] = "";
            }
        }
    }

    private int[] CeldaRandom(){
        //Vector auxiliar
        int[] auxVector = new int[2];
        //Numero random entre 0 y 2
        Random random = new Random();
        int filaRandom = random.nextInt(3);//cero inclusive, 3 exclusive
        auxVector[0] = filaRandom;
        int columnaRandom = random.nextInt(3);//cero inclusive, 3 exclusive
        auxVector[1] = columnaRandom;
        return auxVector;
    }

    public void NuevoJuego(View vista){
        meToca = false;
        AsignarEmpty();
        LimpiarEtiquetas();
        TurnoMaquina();
    }

    private void TurnoMaquina(){
        if(!TrikyLleno()){
            int[] auxVector = CeldaRandom();
            int auxFila = auxVector[0];
            int auxColum = auxVector[1];
            String textoCelda = matrizTriky[auxFila][auxColum];
            //Si está disponible la celda para escribir y es el urno de la máquna
            if(textoCelda.isEmpty() && !meToca){
                EscribirLetraMaquina(auxVector);
            }else{
                //Recursividad
                TurnoMaquina();
            }
        }else{
            Toast.makeText(this, "Inicia un nuevo juego!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean TrikyLleno(){
        boolean bandera = true;
        for (int fila = 0; fila < 3; fila++){
            for(int colum = 0; colum < 3; colum++){
                if (matrizTriky[fila][colum].isEmpty()) {
                    bandera = false;
                    //Para salir del ciclo
                    fila = 3;
                    colum = 3;
                }
            }
        }
        return bandera;
    }

    private void LimpiarEtiquetas(){
        etUno.setText("");
        etDos.setText("");
        etTres.setText("");
        etCuatro.setText("");
        etCinco.setText("");
        etSeis.setText("");
        etSiete.setText("");
        etOcho.setText("");
        etNueve.setText("");
    }

    private boolean CeldaVacia(View vista){
        //cast de vista a text view
        TextView vistaCasteada = (TextView) vista;
        String texto = vistaCasteada.getText().toString();
        //Devuelve true si está vacío  y false si no
        return texto.isEmpty();
    }

    public void EscribirLetraJugador(View vista){
        if(!TrikyLleno()){
            //Si la celda está vacía y es mi turno
            if(CeldaVacia(vista) && meToca){
                //Hay que calcular donde se le dio clic y sincronizar matriz con etiquetas
                String idTexto = getResources().getResourceEntryName(vista.getId());
                switch (idTexto){
                    case "etUno":
                        matrizTriky[0][0] = "O";
                        etUno.setText("O");
                        break;
                    case "etDos":
                        matrizTriky[0][1] = "O";
                        etDos.setText("O");
                        break;
                    case "etTres":
                        matrizTriky[0][2] = "O";
                        etTres.setText("O");
                        break;
                    case "etCuatro":
                        matrizTriky[1][2] = "O";
                        etCuatro.setText("O");
                        break;
                    case "etCinco":
                        matrizTriky[1][1] = "O";
                        etCinco.setText("O");
                        break;
                    case "etSeis":
                        matrizTriky[1][0] = "O";
                        etSeis.setText("O");
                        break;
                    case "etSiete":
                        matrizTriky[2][0] = "O";
                        etSiete.setText("O");
                        break;
                    case "etOcho":
                        matrizTriky[2][1] = "O";
                        etOcho.setText("O");
                        break;
                    case "etNueve":
                        matrizTriky[2][2] = "O";
                        etNueve.setText("O");
                        break;
                    default:
                        break;
                }
                //Acomodar turno
                meToca = false;
                //Le toca a la máquina
                TurnoMaquina();
            }
        }else{
            Toast.makeText(this, "Inicia un nuevo juego!", Toast.LENGTH_SHORT).show();
        }
    }


    private void EscribirLetraMaquina(int[] vector){
        int filaObtenida = vector[0];
        int columnaObtenida = vector[1];
        //Asigna el valor en matriz antes de asignarlo en las etuqietas para que estén sincronizadas
        matrizTriky[filaObtenida][columnaObtenida] = "X";

        if(filaObtenida == 0 && columnaObtenida == 0){
            etUno.setText("X");
        }
        if(filaObtenida == 0 && columnaObtenida == 1){
            etDos.setText("X");
        }
        if(filaObtenida == 0 && columnaObtenida == 2){
            etTres.setText("X");
        }
        if(filaObtenida == 1 && columnaObtenida == 0){
            etSeis.setText("X");
        }
        if(filaObtenida == 1 && columnaObtenida == 1){
            etCinco.setText("X");
        }
        if(filaObtenida == 1 && columnaObtenida == 2){
            etCuatro.setText("X");
        }
        if(filaObtenida == 2 && columnaObtenida == 0){
            etSiete.setText("X");
        }
        if(filaObtenida == 2 && columnaObtenida == 1){
            etOcho.setText("X");
        }
        if(filaObtenida == 2 && columnaObtenida == 2){
            etNueve.setText("X");
        }
        //Acomodamos el turno
        meToca = true;
    }
}