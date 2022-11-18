package mx.edu.ittepic.ladm_u3_ejercicio2_dostablas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u3_ejercicio2_dostablas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.menuPrincipal.setOnItemClickListener { adapterView, view, i, l ->
            when(i){
                0->{
                    startActivity(Intent(this,MainActivity2::class.java))
                }
                1->{
                    startActivity(Intent(this,MainActivity3::class.java))
                }

                2->{
                    AlertDialog.Builder(this).setMessage("(C) Diego Cadena 2022")
                }
                3->{
                    finish()
                }
            }//when
        }//clickListen
    }//onCreate
}//Class