package com.example.rodi.notas;

import android.content.Context;

import java.io.Serializable;


/**
 * Created by Rodi on 26/10/2015.
 */
public class Nota
{
    private String titulo;
    private String texto;
    private int id;

    public Nota(){}

    public Nota(int id, String titulo, String texto)
    {
        this.id = id;
        this.texto = texto;
        this.titulo = titulo;
    }
    public Nota(String titulo, String texto)
    {
        this.texto = texto;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean guardarNota(Context context)
    {
        BDNotas bdNotas = new BDNotas(context);

        bdNotas.agregarNota(titulo, texto);
        bdNotas.close();
        return true;
    }

    public void borrarNota(int id, Context context)
    {
        BDNotas bdNotas = new BDNotas(context);
        bdNotas.eliminarNota(id);
    }
}
