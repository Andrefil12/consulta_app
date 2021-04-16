package com.example.consulta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.consulta.application.ConsultaApplication
import kotlinx.android.synthetic.main.activity_main.*
import com.example.consulta.model.BdDados

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickOnButtom()
    }

    //Chamada o seOndata() depois do click no Botão
    private fun clickOnButtom(){
        botao.setOnClickListener {
            setOnData()
        }
    }

    // Criação do objeto e passando para ser salvo no BD
    private fun setOnData(){
        var nome = inpNome?.text.toString()
        var cpf = inpCpf?.text.toString()
        var rg = inpRg?.text.toString()
        var cell = inpCell?.text.toString()
        Thread(Runnable{
            val consulta = BdDados(
                    nome,cpf,rg,cell
            )
            ConsultaApplication.instance.helperDB?.salvarConsulta(consulta)
            runOnUiThread {
                setOnChange()
            }
        }).start()
    }

    //Chama a Activity Consult
    private fun setOnChange(){
        Thread(Runnable {
            runOnUiThread {
                val intent = Intent(this, ConsultActivity::class.java)
                startActivity(intent)
            }
        }).start()
    }
}