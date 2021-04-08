package main.classes.`class`

class Armazem(val bd: BdDados) {

    val setado = mutableSetOf<BdDados>()

    fun adiciona(){
        setado.add(bd)
    }

    fun remove(){
        setado.remove(bd)
    }

    fun retorno() = setado.forEach { println(it) }
}