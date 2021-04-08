package com.example.consulta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import main.classes.`class`.Armazem
import main.classes.`class`.BdDados

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputData()
    }

    private fun inputData(){
        botao.setOnClickListener {
            callClass(
                inpNome.text.toString(),
                inpCpf.text.toString(),
                inpRg.text.toString(),
                inpCell.text.toString()
            )
        }

    }

    private fun callClass(nome: String, cpf: String, rg: String, cell: String){
        val bd = BdDados(nome,cpf,rg,cell)
        val armazena = Armazem(bd)

    }
}