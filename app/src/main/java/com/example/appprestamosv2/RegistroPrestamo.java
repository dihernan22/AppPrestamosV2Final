package com.example.appprestamosv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegistroPrestamo extends AppCompatActivity {

    final List<String> nombre = new ArrayList<>();
    ArrayAdapter<String> adapter;

    int interes, plazo = 1;
    Double total, cuota, monto = 0.0;

    EditText etMonto, etPlazo, etFecha, etFechaFinal;

    TextView tvMontoPagar, tvMontoCuota;

    Spinner spNombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_prestamo);

        spNombres = findViewById(R.id.spinner);

        for (int i = 0; i < Datos.clientes.size(); i++)
        {
            nombre.add(Datos.clientes.get(i).getNombre() + " " + Datos.clientes.get(i).getApellido());
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombre);
        spNombres.setAdapter(adapter);

        etMonto = findViewById(R.id.etMontoCredito);
        etPlazo = findViewById(R.id.etPlazo);

        tvMontoPagar = findViewById(R.id.txtMontoPagar);
        tvMontoCuota = findViewById(R.id.txtMontoCuota);

        etFecha = findViewById(R.id.etFecha);

        //Fecha Inicio
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        etFecha.setText(formateador.format(new Date()));

        Spinner spInteres = findViewById(R.id.spInteres);
        spInteres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                interes = Integer.parseInt(parent.getSelectedItem().toString());

                ActualizarDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                {
                    if (etMonto.getText().hashCode() == s.hashCode()) {
                        monto = Double.parseDouble(s.toString());
                    }
                    else if (etPlazo.getText().hashCode() == s.hashCode())
                    {
                        plazo = Integer.parseInt(s.toString());
                    }

                    ActualizarDatos();
                }
                else
                {
                    if (etMonto.getText().hashCode() == s.hashCode()) {
                        monto = 0.0;
                    }

                    if (etPlazo.getText().hashCode() == s.hashCode()) {
                        plazo = 1;
                    }

                    ActualizarDatos();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        etMonto.addTextChangedListener(textWatcher);
        etPlazo.addTextChangedListener(textWatcher);
    }

    public void ActualizarDatos()
    {
        etFechaFinal = findViewById(R.id.etFechaFinal);

        total = (double) monto + ((monto * interes / 100) * plazo);
        cuota = total / plazo;

        SimpleDateFormat fechaEnd = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,plazo);
        etFechaFinal.setText(fechaEnd.format(calendar.getTime()));

        tvMontoPagar.setText(String.valueOf(total));
        tvMontoCuota.setText(String.valueOf(cuota));
    }

    public void onClick(View v) {
        Prestamos p = new Prestamos();

        p.setNombres(spNombres.getSelectedItem().toString());
        p.setMontoCred(monto);
        p.setInteres(interes);
        p.setPlazo(plazo);
        p.setFechaIni(etFecha.getText().toString());
        p.setFechaFin(etFechaFinal.getText().toString());
        p.setMontoPagar(Double.parseDouble(tvMontoPagar.getText().toString()));
        p.setMontoCuota(Double.parseDouble(tvMontoCuota.getText().toString()));

        Datos.prestamos.add(p);

        Intent i = getIntent();
        i.putExtra("ResGuardado", "Ingreso de nuevo Prestamo");
        setResult(RESULT_OK, i);

        finish();
    }

    public void CancelarPClick(View view)
    {
        Intent i = getIntent();
        i.putExtra("ResCancelado", "Cancelo ingreso nuevo Prestamo");
        setResult(RESULT_CANCELED, i);

        finish();
    }
}
