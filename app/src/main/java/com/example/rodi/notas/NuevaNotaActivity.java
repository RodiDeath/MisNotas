package com.example.rodi.notas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NuevaNotaActivity extends Activity {

    int RESULT_ERROR = 17;
    EditText etTitulo;
    EditText etTexto;
    String tituloNota;
    String textoNota;
    String accion;
    int idNota;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        etTitulo = (EditText)findViewById(R.id.editTextTitulo);
        etTexto = (EditText)findViewById(R.id.editTextTexto);

        accion = getIntent().getStringExtra("accion");

        if (accion.equals("modificar"))
        {
            tituloNota = getIntent().getStringExtra("titulo");
            textoNota = getIntent().getStringExtra("texto");
            idNota = getIntent().getIntExtra("id", 0);
            etTitulo.setText(tituloNota);
            etTexto.setText(textoNota);
            this.setTitle("Editar Nota");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.GuardarNota)
        {
            String titulo = etTitulo.getText().toString();
            String texto = etTexto.getText().toString();


            if (accion.equals("nueva")) {

                // Guardar Nota
                //EditText etTitulo = (EditText)findViewById(R.id.editTextTitulo);
                //EditText etTexto = (EditText)findViewById(R.id.editTextTexto);

                Nota nota = new Nota(titulo, texto);

                if (nota.guardarNota(getApplicationContext())) {
                    setResult(RESULT_OK);
                } else {
                    setResult(RESULT_ERROR);
                }
                /*****************/
                finish();
            }
            else if (accion.equals("modificar"))
            {
                BDNotas bdNotas = new BDNotas(getApplicationContext());
                bdNotas.modificarNota(idNota, titulo, texto);

                Intent intentMenuPrincipal = new Intent(getBaseContext(), MenuPrincipal.class);
                intentMenuPrincipal.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(getApplicationContext(), "Nota Modificada", Toast.LENGTH_SHORT).show();
                startActivity(intentMenuPrincipal);
                //setResult(RESULT_OK);
                //finish();
            }
        }
        else if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
