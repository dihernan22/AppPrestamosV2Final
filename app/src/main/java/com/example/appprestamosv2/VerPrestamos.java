package com.example.appprestamosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerPrestamos extends AppCompatActivity {

    int Inicio = 0, actual = 0;

    Button btnBack, btnNext;

    TextView tvNombres, tvMontoCredito, tvInteres, tvPlazo, tvFechaIn, tvFechaFin, tvMontoPagar, tvMontoCuota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_prestamos);

        tvNombres = findViewById(R.id.etNombre);
        tvMontoCredito = findViewById(R.id.etMontoCredito);
        tvInteres = findViewById(R.id.spInteres);
        tvPlazo = findViewById(R.id.etPlazo);
        tvFechaIn = findViewById(R.id.etFecha);
        tvFechaFin = findViewById(R.id.etFechaFinal);
        tvMontoPagar = findViewById(R.id.txtMontoPagar);
        tvMontoCuota = findViewById(R.id.txtMontoCuota);

        btnBack = findViewById(R.id.btnAnteriorP);
        btnNext = findViewById(R.id.btnSiguienteP);

        CargarDatos();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual == Inicio){
                    Toast.makeText(VerPrestamos.this, "Esta en la primera posicion!!!", Toast.LENGTH_SHORT).show();
                }else{
                    actual = actual - 1;
                    CargarDatos();
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actual == Datos.prestamos.size() - 1){
                    Toast.makeText(VerPrestamos.this, "Esta en la ultima posicion!!!", Toast.LENGTH_SHORT).show();
                }else{
                    actual = actual + 1;
                    CargarDatos();
                }
            }
        });
    }

    public void CargarDatos()
    {
        tvNombres.setText(Datos.prestamos.get(actual).getNombres());
        tvMontoCuota.setText(String.valueOf(Datos.prestamos.get(actual).getMontoCred()));
        tvInteres.setText(String.valueOf(Datos.prestamos.get(actual).getInteres()));
        tvPlazo.setText(String.valueOf(Datos.prestamos.get(actual).getPlazo()));
        tvFechaIn.setText(Datos.prestamos.get(actual).getFechaIni());
        tvFechaFin.setText(Datos.prestamos.get(actual).getFechaFin());
        tvMontoPagar.setText(String.valueOf(Datos.prestamos.get(actual).getMontoPagar()));
        tvMontoCuota.setText(String.valueOf(Datos.prestamos.get(actual).getMontoCuota()));
    }
}
