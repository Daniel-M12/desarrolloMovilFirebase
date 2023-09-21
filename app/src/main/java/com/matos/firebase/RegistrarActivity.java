package com.matos.firebase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.matos.firebase.model.Persona;

import java.util.UUID;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtCorreo;
    Button btnRegistrar;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inicializarFirebase();
        asignarReferencias();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    private void asignarReferencias() {
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(view -> {
            registrar();
        });
    }

    private void registrar() {
        String nombres = txtNombres.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String correo = txtCorreo.getText().toString();

        Persona persona = new Persona();

        persona.setId(UUID.randomUUID().toString());
        persona.setNombres(nombres);
        persona.setApellidos(apellidos);
        persona.setCorreo(correo);

        reference.child("Persona")
                .child(persona.getId())
                .setValue(persona);
        mostrarMensaje("Persona registrada");
    }

    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje inormativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialogInterface, i) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }
}