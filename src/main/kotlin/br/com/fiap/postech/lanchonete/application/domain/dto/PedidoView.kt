package br.com.fiap.postech.lanchonete.application.domain.dto

import br.com.fiap.postech.lanchonete.application.domain.model.Produto
import br.com.fiap.postech.lanchonete.application.domain.model.Progresso
import java.time.LocalDateTime

data class PedidoView(
    val id: Long?,
    var lanche: Produto?,
    var acompanhamento: Produto?,
    var bebida: Produto?,
    val progresso: Progresso,
    val data: LocalDateTime
)