package br.com.fiap.postech.lanchonete.application.domain.dto

import jakarta.validation.constraints.NotNull

data class CheckoutPedidoForm(
    @field:NotNull(message = "Id do produto deve ser informado") val id: Long
)
