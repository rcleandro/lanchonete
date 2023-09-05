package br.com.fiap.postech.lanchonete.application.domain.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class WebhookPagamentoForm(
    @field:NotNull(message = "Id do produto deve ser informado") val id: Long,
    @field:NotEmpty(message = "Status do pagamento deve ser informado") val status: String
)
