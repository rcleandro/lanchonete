package br.com.fiap.postech.lanchonete.dto

data class ProdutoView(
    val id: Long?,
    val nome: String,
    val descricao: String,
    val preco: Double
)