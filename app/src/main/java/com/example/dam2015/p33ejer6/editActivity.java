package com.example.dam2015.p33ejer6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by Hector on 15/12/2014.
 */
public class editActivity extends Activity {

    private EditText Titulo;
    private EditText Autor;
    private EditText Duracion;
    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_canciones);

        Titulo = (EditText) findViewById(R.id.entradaTitulo);
        Autor = (EditText) findViewById(R.id.entradaAutor);
        Duracion = (EditText) findViewById(R.id.entradaDuracion);

        Intent objIntent = getIntent();
        String id = objIntent.getStringExtra("id");
        HashMap<String, String> CancionList = controller.getCancionInfo(id);

            Titulo.setText(CancionList.get("titulo"));
            Autor.setText(CancionList.get("autor"));
            Duracion.setText(CancionList.get("duracion"));

    }
    public void onClick(final View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("¿Está seguro que desea modificar la canción? ");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Sí",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HashMap<String, String> queryValues =  new  HashMap<String, String>();
                        Titulo = (EditText) findViewById(R.id.entradaTitulo);
                        Autor = (EditText) findViewById(R.id.entradaAutor);
                        Duracion = (EditText) findViewById(R.id.entradaDuracion);
                        Intent objIntent = getIntent();
                        String CancionId = objIntent.getStringExtra("id");
                        queryValues.put("id", CancionId);
                        queryValues.put("titulo", Titulo.getText().toString());
                        queryValues.put("autor", Autor.getText().toString());
                        queryValues.put("duracion", Duracion.getText().toString());

                        controller.updateCancion(queryValues);
                        Intent objIntent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(objIntent1);
                    }
                });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
