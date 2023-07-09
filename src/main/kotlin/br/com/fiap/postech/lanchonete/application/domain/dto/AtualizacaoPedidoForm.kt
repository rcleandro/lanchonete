package br.com.fiap.postech.lanchonete.application.domain.dto

import jakarta.validation.constraints.NotNull

data class AtualizacaoPedidoForm(
    @field:NotNull(message = "Id deve ser informado") val id: Long,
    val lanche: Long?,
    val acompanhamento: Long?,
    val bebida: Long?,
    @field:NotNull(message = "Progresso deve ser informado") val progresso: Int
)