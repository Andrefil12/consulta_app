package com.example.consulta.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.consulta.model.BdDados

class HelperDB(
        context: Context?
) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL) {

    companion object{
        private val NOME_BANCO = "consulta.db"
        private val VERSAO_ATUAL = 1
    }

    val TABLE_NAME = "consulta"
    val COLUMNS_NOME = "nome"
    val COLUMNS_CPF = "cpf"
    val COLUMNS_RG = "rg"
    val COLUMNS_TELEFONE = "telefone"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME(" +
            "$COLUMNS_CPF TEXT NOT NULL," +
            "$COLUMNS_NOME TEXT NOT NULL," +
            "$COLUMNS_RG TEXT NOT NULL," +
            "$COLUMNS_TELEFONE TEXT NOT NULL," +
            "PRIMARY KEY($COLUMNS_CPF)" +
            ")"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(DROP_TABLE)
        }
        onCreate(db)
    }

    fun buscarConsulta(busca: String) : List<BdDados>{
        //Coloca-se o elvis operator para garantir que não vai retornar null, mas não é necessário
        val db = readableDatabase ?: return mutableListOf()
        var lista = mutableListOf<BdDados>()
        //val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMNS_CPF LIKE '%$busca%'"
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMNS_CPF LIKE ?"
        var buscaComPercentual = "%$busca%"
        var cursor = db.rawQuery(sql, arrayOf(buscaComPercentual))
        if(cursor == null){
            db.close()
            return mutableListOf()
        }
        while(cursor.moveToNext()){
            var consulta = BdDados(
                    cursor.getString(cursor.getColumnIndex(COLUMNS_CPF)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_NOME)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_RG)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_TELEFONE))
            )
            lista.add(consulta)
        }
        db.close()
        return lista
    }

    fun salvarConsulta(consulta: BdDados){
        val db = writableDatabase ?: return
        /*
        Existe essa maneira de se fazer a inserção, mas tem outra
        val sql = "INSERT INTO $TABLE_NAME VALUES (?,?,?,?)"
        val array = arrayOf(consulta.nome, consulta.cpf, consulta.rg, consulta.cell)*\
         */
        var content = ContentValues()
        content.put(COLUMNS_NOME,consulta.nome)
        content.put(COLUMNS_CPF,consulta.cpf)
        content.put(COLUMNS_RG,consulta.rg)
        content.put(COLUMNS_TELEFONE,consulta.cell)
        db.insert(TABLE_NAME,null, content)
        //db.execSQL(sql,array)
        db.close()
    }

    fun deletarConsulta(cpf: String){
        val db = writableDatabase ?: return
        val sql = "DELETE FROM $TABLE_NAME WHERE $COLUMNS_CPF = ?"
        val arg = arrayOf("$cpf")
        db.execSQL(sql, arg)
        /*
        Uma das formas possíveis de implementar o delete
        val where = "cpf = ?"
        val arg = arrayOf("$cpf")
        db.delete(TABLE_NAME, where, arg)

         */
        db.close()
    }

    fun updateConsulta(consulta: BdDados){
        val db = writableDatabase ?: return
        val sql = "UPDATE $TABLE_NAME SET $COLUMNS_NOME = ?, $COLUMNS_CPF = ?" +
                "$COLUMNS_RG = ? , $COLUMNS_TELEFONE = ? WHERE $COLUMNS_CPF = ?"
        val arg = arrayOf(consulta.nome, consulta.cpf, consulta.rg, consulta.cell, consulta.cpf)
        db.execSQL(sql, arg)
        /*
        Uma das possibilidades de update
        val content = ContentValues()
        content.put(COLUMNS_NOME, consulta.nome)
        content.put(COLUMNS_CPF, consulta.cpf)
        content.put(COLUMNS_RG, consulta.rg)
        content.put(COLUMNS_TELEFONE, consulta.cell)
        val where = "id = ?"
        val arg = arrayOf("${consulta.cpf}")
        db.update(TABLE_NAME, content, where, arg)
         */
        db.close()
    }
}