package com.example.rodi.notas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class VerNotaActivity extends Activity
{
    int idNota;
    BDNotas dbNota;
    String tituloNota;
    String textoNota;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        dbNota = new BDNotas(getApplicationContext());

        tituloNota = getIntent().getStringExtra("TituloNota");
        textoNota = getIntent().getStringExtra("TextoNota");
        idNota = getIntent().getIntExtra("IdNota", 0);

        TextView tvTituloNota = (TextView) findViewById(R.id.textViewTitulo);
        TextView tvTextoNota = (TextView) findViewById(R.id.textViewTexto);

        tvTituloNota.setText(tituloNota);
        tvTextoNota.setText(textoNota);
        //Toast.makeText(getBaseContext(), tituloNota, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_nota, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), "RESULT", Toast.LENGTH_SHORT).show();
        //finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.EditarNota)
        {
            // EDITAR NOTA
            Intent intentModificarNota = new Intent(getBaseContext(), NuevaNotaActivity.class);
            intentModificarNota.putExtra("accion", "modificar");
            intentModificarNota.putExtra("titulo", tituloNota);
            intentModificarNota.putExtra("texto", textoNota);
            intentModificarNota.putExtra("id", idNota);
            startActivityForResult(intentModificarNota, RESULT_OK);
            return true;
        }
        else if (id == R.id.BorrarNota)
        {
            // BORRAR NOTA
            AlertDialog.Builder adb = new AlertDialog.Builder(this);

            adb.setTitle("¿Borrar nota '"+ tituloNota + "'?");

            adb.setIcon(R.drawable.ic_delete_black_48dp);

            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                    dbNota.eliminarNota(idNota);
                    Toast.makeText(getApplicationContext(), "Nota '" + tituloNota + "' Borrada", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

            adb.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(getApplicationContext(), "Nota NO Borrada", Toast.LENGTH_SHORT).show();
                }
            });
            adb.show();

            return true;
        }
        else if (id == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
