package com.matos.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.matos.firebase.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAgregar;
    ListView lstPersonas;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<Persona> listaPersonas = new ArrayList<>();
    ArrayAdapter<Persona> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarReferencias();
        inicializarFirebase();
        listarDatos();
    }

    private void asignarReferencias() {
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistrarActivity.class);
            startActivity(intent);
        });
        lstPersonas = findViewById(R.id.lstPersonas);
        lstPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,
                        ""+listaPersonas.get(i).getNombres(),
                        Toast.LENGTH_LONG);
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    private void listarDatos() {
        reference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPersonas.clear();
                for (DataSnapshot item: snapshot.getChildren()){
                    Persona persona = item.getValue(Persona.class);
                    listaPersonas.add(persona);
                }
                adaptador = new ArrayAdapter<Persona>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        listaPersonas);
                lstPersonas.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}