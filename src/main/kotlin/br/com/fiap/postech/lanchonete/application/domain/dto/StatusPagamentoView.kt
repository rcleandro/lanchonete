package br.com.fiap.postech.lanchonete.application.domain.dto

import br.com.fiap.postech.lanchonete.application.domain.model.StatusPagamento

data class StatusPagamentoView(
    val id: Long?,
    val statusPagamento: StatusPagamento?
)