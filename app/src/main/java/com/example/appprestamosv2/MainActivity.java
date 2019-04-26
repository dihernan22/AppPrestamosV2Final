package com.example.appprestamosv2;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvLogs;

    static int NuevoCliente = 4545, NuevoPrestamo = 4646;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogs = findViewById(R.id.tvLogs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnNuevoPrestamo:
                Intent intent = new Intent(MainActivity.this, RegistroPrestamo.class);
                startActivityForResult(intent, NuevoCliente);
                break;
            case R.id.mnNuevoCliente:
                Intent intent2 = new Intent(MainActivity.this, RegistroCliente.class);
                startActivityForResult(intent2, NuevoPrestamo);
                break;
            case R.id.mnVerClientes:
                Intent intent3 = new Intent(MainActivity.this, VerClientes.class);
                startActivity(intent3);
                //startActivityForResult(intent,2545);
                break;
            case R.id.mnVerPrestamos:
                Intent intent4 = new Intent(MainActivity.this, VerPrestamos.class);
                startActivity(intent4);
                break;
            case R.id.mnAcerca:
                Toast.makeText(this, "Electiva Android", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menucontextual,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnCopiar:
                ClipboardManager manager;
                manager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Historial", tvLogs.getText().toString());
                manager.setPrimaryClip(clipData);
                break;
            case R.id.mnBorrar:
                tvLogs.setText("");
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 4545)
        {
            String res;
            if (resultCode == RESULT_CANCELED)
            {
                res = data.getExtras().getString("ResCancelado");
                tvLogs.append("\n");
                tvLogs.append(res);
            }
            else
            {
                res = data.getExtras().getString("ResGuardado");
                tvLogs.append("\n");
                tvLogs.append(res);
            }
        }
        else
        {
            String res;
            if (resultCode == RESULT_CANCELED)
            {
                res = data.getExtras().getString("ResCancelado");
                tvLogs.append("\n");
                tvLogs.append(res);
            }
            else
            {
                res = data.getExtras().getString("ResGuardado");
                tvLogs.append("\n");
                tvLogs.append(res);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
