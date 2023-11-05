package br.com.fiap.postech.lanchonete.application.domain.mapper

import br.com.fiap.postech.lanchonete.application.domain.dto.StatusPagamentoView
import br.com.fiap.postech.lanchonete.application.domain.model.Pedido
import org.springframework.stereotype.Component

@Component
class StatusPagamentoViewMapper: Mapper<Pedido, StatusPagamentoView> {
    override fun map(p: Pedido): StatusPagamentoView {
        return StatusPagamentoView(
            id = p.id,
            statusPagamento = p.statusPagamento
        )
    }
}