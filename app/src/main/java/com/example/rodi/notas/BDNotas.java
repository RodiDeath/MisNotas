package com.example.rodi.notas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Struct;

/**
 * Created by Rodi on 27/10/2015.
 */
public class BDNotas extends SQLiteOpenHelper {
    // Constantes Base de Datos
    public static final String NOMBRE_BD = "BDNotas.db";
    public static final int VERSION_BD = 1;

    // Constantes Tabla NOTAS
    public static final String TABLA_NOTAS = "Notas";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_TITULO = "titulo";
    public static final String COLUMNA_TEXTO = "texto";

    // Constante SQL para crear base de datos
    public static final String SQL_CREAR_DB = "create table "
            + TABLA_NOTAS + "(" +
            COLUMNA_ID + " integer primary key autoincrement, " +
            COLUMNA_TITULO + " text null, " +
            COLUMNA_TEXTO + " text null" + ");";


    public BDNotas(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR_DB); // Se crea la base de datos con la sentencia SQL_CREAR_BD
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarNota(String titulo, String texto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMNA_TITULO, titulo);
        values.put(COLUMNA_TEXTO, texto);

        db.insert(TABLA_NOTAS, null, values);
        //db.close();
    }

    public Cursor obtenerNotas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMNA_ID, COLUMNA_TITULO, COLUMNA_TEXTO};

        Cursor cursor = db.rawQuery("select * from Notas;", null);
        //db.close();
        return cursor;
    }

    public void eliminarNota(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLA_NOTAS, COLUMNA_ID + "=" + id, null);
        Log.e("ERROR", "Id: " + id);
        //db.close();
    }

    public void modificarNota(int id, String titulo, String texto)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("titulo",titulo);
        cv.put("texto",texto);

        db.update(TABLA_NOTAS ,cv ,"_id" + "=" + id, null);
    }
}
