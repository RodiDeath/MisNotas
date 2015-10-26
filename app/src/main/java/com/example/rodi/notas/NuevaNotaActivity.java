package com.example.rodi.notas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NuevaNotaActivity extends Activity {

    int RESULT_ERROR = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_nota);
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
            // Guardar Nota
            EditText etTitulo = (EditText)findViewById(R.id.editTextTitulo);
            EditText etTexto = (EditText)findViewById(R.id.editTextTexto);

            String titulo = etTitulo.getText().toString();
            String texto = etTexto.getText().toString();

            Nota nota = new Nota(titulo, texto);

            if (nota.guardarNota()) {
                setResult(RESULT_OK);
            }
            else
            {
                setResult(RESULT_ERROR);
            }
            /*****************/

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
