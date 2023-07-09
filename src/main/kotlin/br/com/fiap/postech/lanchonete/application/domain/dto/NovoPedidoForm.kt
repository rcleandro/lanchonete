package br.com.fiap.postech.lanchonete.application.domain.dto

import br.com.fiap.postech.lanchonete.application.domain.model.Cliente

data class NovoPedidoForm(
    val cliente: Cliente?,
    var lanche: Long?,
    var acompanhamento: Long?,
    var bebida: Long?,
)
