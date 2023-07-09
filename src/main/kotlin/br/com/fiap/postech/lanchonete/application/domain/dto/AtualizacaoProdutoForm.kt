package br.com.fiap.postech.lanchonete.application.domain.dto

import br.com.fiap.postech.lanchonete.application.domain.model.Categoria
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class AtualizacaoProdutoForm(
    @field:NotNull(message = "Id do produto deve ser informado") val id: Long,
    @field:NotEmpty(message = "Nome do produto deve ser informado") val nome: String,
    @field:NotEmpty(message = "Descrição do produto deve ser informada") val descricao: String,
    @field:Positive(message = "Preço do produto inválido") val preco: Double,
    @field:NotNull(message = "Categoria do produto deve ser informada") val categoria: Categoria
)