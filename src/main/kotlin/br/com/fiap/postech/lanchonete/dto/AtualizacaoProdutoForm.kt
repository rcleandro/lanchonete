package br.com.fiap.postech.lanchonete.dto

import br.com.fiap.postech.lanchonete.model.Categoria
import jakarta.validation.constraints.*

data class AtualizacaoProdutoForm(
    @field:NotNull(message = "Id do produto deve ser informado")
    val id: Long,

    @field:NotBlank(message = "Nome do produto deve ser informado")
    @field:Size(message = "Nome do produto não deve ultrapassar a quantidade máxima de 50 caracteres", max = 50)
    val nome: String,

    @field:NotBlank(message = "Descrição do produto deve ser informada")
    @field:Size(message = "Descrição do produto não deve ultrapassar a quantidade máxima de 100 caracteres", max = 100)
    val descricao: String,

    @NotNull(message = "Preço do produto deve ser informado")
    @field:Positive(message = "Preço do produto inválido")
    val preco: Double,

    @field:NotBlank(message = "Categoria do produto deve ser informada")
    val categoria: Categoria
)