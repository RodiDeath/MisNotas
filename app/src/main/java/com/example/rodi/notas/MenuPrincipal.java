package com.example.rodi.notas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuPrincipal extends Activity {

    ListView lvNotas;
    ArrayList<String> listaNotas=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listaNotas);

        lvNotas = (ListView)findViewById(R.id.listViewNotas);
        lvNotas.setAdapter(adapter);

        lvNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentVerNota = new Intent (getBaseContext(), VerNotaActivity.class);
                intentVerNota.putExtra("TituloNota", adapter.getItem(position).toString());
                startActivity(intentVerNota);
            }
        });

        recuperarNotas();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            Toast.makeText(getBaseContext(), "Nota Guardada", Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getBaseContext(), "Nota NO Guardada", Toast.LENGTH_SHORT).show();
            }
            else
                {
                    Toast.makeText(getBaseContext(), "ERROR Guardando Nota", Toast.LENGTH_SHORT).show();
                }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.CrearNuevaNota)
        {
            Intent intentNuevaNota = new Intent(getBaseContext(), NuevaNotaActivity.class);
            startActivityForResult(intentNuevaNota, 0);
        }

        return super.onOptionsItemSelected(item);


    }

    private void recuperarNotas()
    {
        listaNotas.add("Nota 1");
        listaNotas.add("Nota 2");
        listaNotas.add("Nota 3");
        listaNotas.add("Nota 4");
        adapter.notifyDataSetChanged();
    }
}
