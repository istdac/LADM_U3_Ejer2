package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    //BaseDatos
    //Si se corrige el oncreate, ir a empleado y cambiar version de db
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("CREATE TABLE EMPLEADO (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(200), PUESTO VARCHAR(200), ANTIGUEDAD INTEGER)")
        p0!!.execSQL("CREATE TABLE CONYUGUE (ID_CON INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRECONYUGUE VARCHAR(200), TIPO VARCHAR(50), EDAD INTEGER, ID INTEGER, FOREIGN KEY (ID) REFERENCES EMPLEADO(ID))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}