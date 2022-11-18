package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.databinding.ActivityMain4Binding
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.logica.Empleado

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    var id= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Obtener id desde ventana previa
        id=intent.extras!!.getString("ID")!!
        var data = Empleado(this).getById(id)
        //Poner datos
        binding.nombreEmpleado.setText(data!!.getNombre())
        binding.puestoEmpleado.setText(data!!.getPuesto())
        binding.antiguedadEmpleado.setText(data!!.getAntiguedad().toString())

        binding.btnActualizar.setOnClickListener {
            var res = Empleado(this).actualizar(id, binding.nombreEmpleado,binding.puestoEmpleado,binding.antiguedadEmpleado)
            //Si se actualizo
            if(res){
                Toast.makeText(this,"Se actualizó correctamente",Toast.LENGTH_LONG).show()
                binding.nombreEmpleado.isEnabled=false
                binding.puestoEmpleado.isEnabled=false
                binding.antiguedadEmpleado.isEnabled=false
                binding.btnActualizar.isEnabled=false
            }//if
            else{
                AlertDialog.Builder(this)
                    .setMessage("No se actualizó")
                    .show()
            }
        }//clicklist

        //Botón para regresar a la interfaz previa
        binding.btnRegresar.setOnClickListener { finish() }

    }//onCreate
}