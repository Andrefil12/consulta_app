package com.example.consulta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputData()
    }

    private fun inputData(){

        inpNome?.doAfterTextChanged {text ->
            val nome = text.toString()
        }

        inpCpf?.doAfterTextChanged { text ->
            val cpf = text.toString()
        }

        inpRg?.doAfterTextChanged { text ->
            val rg = text.toString()
        }

        inpCell?.doAfterTextChanged { text ->
            val cell = text.toString()
        }


    }

    private fun callClass(nome: String, cpf: String, rg: String, cell: String){

    }
}