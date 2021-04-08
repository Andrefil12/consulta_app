package main.classes.`class`

class BdDados(val nome: String, val cpf: String, val rg: String, val cell: String) {

    override fun toString() =
        """
            Nome: $nome
            Cpf: $cpf
            Rg: $rg
            Telefone: $cell
        """.trimIndent()

}