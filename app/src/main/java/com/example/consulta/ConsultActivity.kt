package com.example.consulta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.consulta.application.ConsultaApplication
import com.example.consulta.model.BdDados
import kotlinx.android.synthetic.main.activity_consult.*

class ConsultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult)
        getOnData()
    }

    private fun getOnData(){
        buttonConsult.setOnClickListener {
            getOnResult()
            //setOnText(list)
        }
    }

    private fun getOnResult() {
        Thread(Runnable {
            val busca = inputConsult?.text.toString()
            runOnUiThread{
                exitConsult?.text = "${ConsultaApplication.instance.helperDB?.buscarConsulta(busca)}"
            }
        }).start()

    }

}