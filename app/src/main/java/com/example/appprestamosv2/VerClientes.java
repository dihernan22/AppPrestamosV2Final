package com.example.appprestamosv2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerClientes extends AppCompatActivity {

    int Inicio = 0, actual = 0;

    Button btnBack, btnNext;
    TextView tvNombte, tvApellido, tvSexo, tvTelefono, tvCedula, tvOcupacion, tvDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        tvNombte = findViewById(R.id.etNombre);
        tvApellido = findViewById(R.id.etApellido);
        tvSexo = findViewById(R.id.spSexo);
        tvTelefono = findViewById(R.id.etTelefono);
        tvCedula = findViewById(R.id.etCedula);
        tvOcupacion = findViewById(R.id.etOcupacion);
        tvDireccion = findViewById(R.id.etDireccion);

        CargarDatos();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual == Inicio){
                    Toast.makeText(VerClientes.this, "Esta en la primera posicion!!!", Toast.LENGTH_SHORT).show();
                }else{
                    actual = actual - 1;
                    CargarDatos();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual == Datos.clientes.size() - 1){
                    Toast.makeText(VerClientes.this, "Esta en la ultima posicion!!!", Toast.LENGTH_SHORT).show();
                }else{
                    actual = actual + 1;
                    CargarDatos();
                }
            }
        });
    }

    public void CargarDatos()
    {
        tvNombte.setText(Datos.clientes.get(actual).getNombre());
        tvApellido.setText(Datos.clientes.get(actual).getApellido());
        tvSexo.setText(Datos.clientes.get(actual).getSexo());
        tvTelefono.setText(Datos.clientes.get(actual).getTelefono());
        tvCedula.setText(Datos.clientes.get(actual).getCedula());
        tvOcupacion.setText(Datos.clientes.get(actual).getOcupacion());
        tvDireccion.setText(Datos.clientes.get(actual).getDireccion());
    }

    public void NuevoPrestamoClick(View view)
    {
        Intent intent = new Intent(VerClientes.this, RegistroPrestamo.class);
        startActivity(intent);
    }
}
