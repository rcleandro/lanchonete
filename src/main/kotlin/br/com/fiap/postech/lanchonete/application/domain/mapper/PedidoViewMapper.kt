package br.com.fiap.postech.lanchonete.application.domain.mapper

import br.com.fiap.postech.lanchonete.application.domain.dto.PedidoView
import br.com.fiap.postech.lanchonete.application.domain.model.Pedido
import org.springframework.stereotype.Component

@Component
class PedidoViewMapper: Mapper<Pedido, PedidoView> {
    override fun map(p: Pedido): PedidoView {
        return PedidoView(
            id = p.id,
            lanche = p.lanche,
            acompanhamento = p.acompanhamento,
            bebida = p.bebida,
            progresso = p.progresso,
            data = p.data
        )
    }
}