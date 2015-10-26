package com.example.rodi.notas;

import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Rodi on 26/10/2015.
 */
public class Nota
{
    private String titulo;
    private String texto;

    public Nota(String titulo, String texto)
    {
        this.texto = texto;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean guardarNota()
    {
        return true;
    }

}
