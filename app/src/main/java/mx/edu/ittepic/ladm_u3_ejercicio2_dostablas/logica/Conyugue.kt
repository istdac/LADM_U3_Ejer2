package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.logica

import android.content.ContentValues
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.BaseDatos

data class Conyugue(val p:AppCompatActivity) {
    //Este folder fue creado añadiendo un nuevo package a la aplicacion dentro del folder principal
    //Convertir columnas en variables
    private var id_con = 0
    private var nombre= ""
    private var tipo = ""
    private var edad = 0
    private var ID = 0
    private var baseDatos = BaseDatos(p,"Ejemplo2_2",null,1)

    fun insertar(nombre:EditText,tipo:Spinner,edad:EditText,id_foraneo:String):Boolean{
        var data = ContentValues()
        var tipoC = tipo.selectedItem.toString()
        data.put("NOMBRE",nombre.text.toString())
        data.put("TIPO",tipoC)
        data.put("EDAD",edad.text.toString().toInt())
        data.put("ID",id_foraneo.toInt())

        var resu = baseDatos.writableDatabase.insert("CONYUGUE","ID_CON",data)
        if (resu==-1L){return false}
        return true

    }//insertar
}//conyugue