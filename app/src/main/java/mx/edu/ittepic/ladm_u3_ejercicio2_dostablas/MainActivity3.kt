package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.databinding.ActivityMain3Binding
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.logica.Conyugue
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.logica.Empleado

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    var listaIDsEmpleados = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Obtener ids
        var datos = Empleado(this).listaNombres()
        listaIDsEmpleados = Empleado(this).listaID()

        binding.comboEmpleados.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos)
        binding.insertar.setOnClickListener {
            //val res = Conyugue(this).insertar()
        }
    }//onCreate
}