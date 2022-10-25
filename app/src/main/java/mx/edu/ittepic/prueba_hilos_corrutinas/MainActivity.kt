package mx.edu.ittepic.prueba_hilos_corrutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
    var contadorC = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hilo.setOnClickListener {
            hilo()
        }//btn_hilo


        btn_corru.setOnClickListener {
            corrutinaP()

        }
    }

    fun corrutinaP() = runBlocking {
        val tiempo = measureTimeMillis {
            val coroutines = List(20_000) {
                launch {
                    delay(1000)
                }
            }
            coroutines.forEach {
                it.join()
            }
        }

        msjC("Time: $tiempo")
        println("Tiempo: $tiempo")
    }

    fun hilo(){
        val tiempo = measureTimeMillis {
            val threads = List(20_000){
                thread {
                    Thread.sleep(1000)
                }
            }
            threads.forEach{
                it.join()
            }
        }
        msjH("Tiempo: $tiempo")
        println("Tiempo: $tiempo")
    }

    fun msjH(message: String) {
        et_tiempoH.setText("[${Thread.currentThread().name}] : $message")
    }


    fun msjC(message: String) {
        et_tiempoC.setText("[${Thread.currentThread().name}] : $message")
    }

}
