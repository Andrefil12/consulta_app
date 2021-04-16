package com.example.consulta.application

import android.app.Application
import com.example.consulta.helpers.HelperDB

class ConsultaApplication : Application() {

    var helperDB: HelperDB? = null
        private set

    companion object{
        lateinit var instance: ConsultaApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this 
        helperDB = HelperDB(this)
    }
}