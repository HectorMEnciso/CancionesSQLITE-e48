package com.example.dam2015.p33ejer6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by dam2015 on 11/12/2014.
 */
public class addActivity extends Activity {

    private EditText Titulo;
    private EditText Autor;
    private EditText Duracion;
    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_canciones);
        Titulo = (EditText) findViewById(R.id.entradaTitulo);
        Autor = (EditText) findViewById(R.id.entradaAutor);
        Duracion = (EditText) findViewById(R.id.entradaDuracion);
    }
    public void callHomeActivity(View view) {
        Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(objIntent);
    }
    public void onClick(View v){
        HashMap<String, String> queryValues =  new  HashMap<String, String>();
        queryValues.put("titulo", Titulo.getText().toString());
        queryValues.put("autor", Autor.getText().toString());
        queryValues.put("duracion", Duracion.getText().toString());
        controller.insertCancion(queryValues);
        this.callHomeActivity(v);
    }
}
