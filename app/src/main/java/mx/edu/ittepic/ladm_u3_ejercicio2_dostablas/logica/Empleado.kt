package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.logica

import android.content.ContentValues
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.BaseDatos

//Hacer la clase transaccional con data class y para usar p hacerlo val
data class Empleado( val p:AppCompatActivity) {
    //Se crea la clase para separar el modelo de la vista
    private var id=0
    private var nombre = ""
    private var puesto = ""
    private var antiguedad = 0

    private var baseDatos = BaseDatos(p,"Ejemplo2_2",null,1)

    fun insertar(nom:EditText,pue:EditText,ant:EditText):Boolean{
        var data = ContentValues()
        data.put("NOMBRE",nom.text.toString())
        data.put("PUESTO",pue.text.toString())
        data.put("ANTIGUEDAD",ant.text.toString().toInt())

        var resu = baseDatos.writableDatabase.insert("EMPLEADO","ID",data)
        if (resu==-1L){return false}
        return true
    }//insertar

    fun mostrarTodos():ArrayList<String>{
        var resu = ArrayList<String>()
        var cursor = baseDatos.readableDatabase.rawQuery("SELECT * FROM EMPLEADO",null)
        //Al hacer este moveToFirst se tiene el primer registro ya
        if (!cursor.moveToFirst()){
            resu.add("NO HAY REGISTROS PARA MOSTRAR")
            return resu
        }
        do {
            var cad = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getInt(3)
            resu.add(cad)
        }while(cursor.moveToNext())
        return resu

    }//mostrarTodos
    //TAREA HACER ELIMINAR Y ACTUALIZAR
    fun eliminarPorID(idEmpleado: String): Boolean{
        var res = baseDatos.writableDatabase
            .delete("Empleado","ID=?", arrayOf(idEmpleado))
        //Checar cantidad alterada
        if(res==0)return false
        return true
    }//eliminar

    fun actualizar(idEmpleado: String,nom:EditText,pue:EditText,ant:EditText): Boolean{
        var data = ContentValues()
        data.put("NOMBRE",nom.text.toString())
        data.put("PUESTO",pue.text.toString())
        data.put("ANTIGUEDAD",ant.text.toString().toInt())
        var res = baseDatos.writableDatabase
            .update("EMPLEADO",data,"ID=?", arrayOf(idEmpleado))
        if(res==0)return false
        return true
    }//actualizar

    //Tarea, obtener id
    fun getID(nom:EditText,pue:EditText,ant:EditText): Int{
        var data = ContentValues()
        data.put("NOMBRE",nom.text.toString())
        data.put("PUESTO",pue.text.toString())
        data.put("ANTIGUEDAD",ant.text.toString().toInt())

        var cursor = baseDatos.readableDatabase.query("EMPLEADO", arrayOf("ID"),"WHERE NOMBRE=? and PUESTO = ? and ANTIGUEDAD = ?",arrayOf(nom.text.toString(),pue.text.toString(),ant.text.toString()),null,null,null)
        if(cursor.moveToFirst()){
            return cursor.getInt(0)
        }else{
            return -1
        }
    }
    fun listaID():ArrayList<String>{
        var resu = ArrayList<String>()
        var cursor = baseDatos.readableDatabase.rawQuery("SELECT * FROM EMPLEADO",null)
        //Al hacer este moveToFirst se tiene el primer registro ya
        if (!cursor.moveToFirst()){
            return resu
        }
        do {
            //Esto obtiene todos gracias al dowhile
            resu.add(cursor.getInt(0).toString())
        }while(cursor.moveToNext())
        return resu
    }//listaID
    //Poner ? al tipo de regreso porque puede ser un null
    fun getById(id:String): Empleado?{
        var cursor = baseDatos.writableDatabase.rawQuery("SELECT * FROM EMPLEADO WHERE ID=?",
            arrayOf(id))
        if(!cursor.moveToFirst()){
            return null
        }
        val resu = Empleado(p)
        resu.id=id.toInt()
        resu.nombre=cursor.getString(1)
        resu.puesto=cursor.getString(2)
        resu.antiguedad=cursor.getInt(3)
        return resu

    }//getbyid
    fun getNombre():String{return nombre}
    fun getPuesto():String{return puesto}
    fun getAntiguedad():Int{return antiguedad}

    //3 buscar
    /*Buscar por {antiguedad <=,antiguedad >=, puesto, nombre usar like }*/
    fun buscarAntiMenor(a:Int):ArrayList<String>{
        var cursor = baseDatos.readableDatabase.rawQuery("SELECT * FROM EMPLEADO WHERE ANTIGUEDAD<=?",
            arrayOf(a.toString()))
        var emps=ArrayList<String>()

        if(!cursor.moveToFirst()){
            emps.add("No hay registros para mostrar")
            return emps

        }
        do{
            var resu = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getInt(3)
            emps.add(resu)
        }while(cursor.moveToNext())
        return emps
    }//buscarAntiMenor
    fun buscarAntiMayor(a:Int):ArrayList<String>{
        var cursor = baseDatos.readableDatabase.rawQuery("SELECT * FROM EMPLEADO WHERE ANTIGUEDAD>=?",arrayOf(a.toString()))
        var emps=ArrayList<String>()

        if(!cursor.moveToFirst()){
            emps.add("No hay registros para mostrar")
            return emps
        }
        do{
            var resu = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getInt(3)
            emps.add(resu)
        }while(cursor.moveToNext())
        return emps
    }//buscarAntiMenor
    fun buscarNombre(n:String):ArrayList<String>{
        var cursor = baseDatos.readableDatabase.rawQuery("Select * from Empleado where Nombre like ?", arrayOf(n+'%'))
        var emps=ArrayList<String>()

        if(!cursor.moveToFirst()){
            emps.add("No hay registros para mostrar")
            return emps
        }
        do{
            var resu = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getInt(3)
            emps.add(resu)
        }while(cursor.moveToNext())
        return emps
    }//nombre
    fun buscarPuesto(pu:String):ArrayList<String>{
        var cursor = baseDatos.readableDatabase.rawQuery("Select * from Empleado where Puesto like '%?%'",
            arrayOf(pu))
        var emps=ArrayList<String>()
        if(!cursor.moveToFirst()){
            emps.add("No hay registros para mostrar")
            return emps
        }
        do{
            var resu = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getInt(3)
            emps.add(resu)
        }while(cursor.moveToNext())
        return emps
    }//nombre
    fun listaNombres():ArrayList<String>{
        var resu = ArrayList<String>()
        var cursor = baseDatos.readableDatabase.rawQuery("SELECT * FROM EMPLEADO",null)
        //Al hacer este moveToFirst se tiene el primer registro ya
        if (!cursor.moveToFirst()){
            return resu
        }
        do {
            //Esto obtiene todos gracias al dowhile
            resu.add(cursor.getString(1))
        }while(cursor.moveToNext())
        return resu
    }//listaID
}//Empleado