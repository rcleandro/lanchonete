package br.com.fiap.postech.lanchonete.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class NovoProdutoForm(
    @field:NotEmpty(message = "Nome do produto deve ser informado") val nome: String,
    @field:NotEmpty(message = "Descrição do produto deve ser informada") val descricao: String,
    @field:Positive(message = "Preço do produto inválido") val preco: Double,
    @field:NotNull(message = "Categoria do produto deve ser informada") val categoria: Int,
)
