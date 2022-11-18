package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.databinding.ActivityMain2Binding
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.logica.Empleado

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        mostrar()
        //Enlazar datos a listview
        //La activación del itemclick puede solamente hacerse en oncreate ya que se enlaza con la lista
        binding.listaEmpleado.setOnItemClickListener { adapterView, view, i, l ->
            //Obtener el id de la lista directamente
            var listaIdSeleccionago = Empleado(this).listaID().get(i)
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿Qué desea hacer con el Empleado seleccionado?")
                .setPositiveButton("ELIMINAR"){d,i->
                    eliminar(listaIdSeleccionago)
                }
                .setNeutralButton("ACTUALIZAR"){d,i->
                    actualizar(listaIdSeleccionago)

                }
                .setNegativeButton("NADA"){d,i->}
                .show()
        }
        binding.btnInsertar.setOnClickListener {
            //Insertar
            val resInsert = Empleado(this).insertar(binding.nombreEmpleado,binding.puestoEmpleado,binding.antiguedadEmpleado)
            //Si insertado
            if(resInsert){
                Toast.makeText(this,"Se insertó con éxito",Toast.LENGTH_LONG).show()
                binding.nombreEmpleado.setText("");binding.puestoEmpleado.setText("");binding.antiguedadEmpleado.setText("")
                mostrar()
            }else{
                AlertDialog.Builder(this).setMessage("No se pudo insertar").show()
            }
        }//clicklis
    }//OnCreate

    fun mostrar(){
        var datos = Empleado(this).mostrarTodos()
        //var datos = Empleado(this).buscarAntiMenor(20)
        //Cargar datos
        binding.listaEmpleado.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos)
    }//mostrar

    fun eliminar(id:String){
        //Eliminar
        val resDel = Empleado(this).eliminarPorID(id)
        //Si insertado
        if(resDel){
            Toast.makeText(this,"Se eliminó con éxito",Toast.LENGTH_LONG).show()
            //Actualizar data
            mostrar()
        }else{
            AlertDialog.Builder(this).setMessage("No se pudo eliminar").show()
        }
    }//eliminar
    fun actualizar(id:String){
        var actAct = Intent(this,MainActivity4::class.java)
        //Mandar id a otra ventana
        actAct.putExtra("ID",id)
        startActivity(actAct)
    }//actualizar

    //Actualizar al recargar
    override fun onRestart() {
        super.onRestart()
        mostrar()
    }

}//MainActivity2