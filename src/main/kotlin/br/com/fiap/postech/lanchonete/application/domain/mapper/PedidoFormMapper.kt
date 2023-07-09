package br.com.fiap.postech.lanchonete.application.domain.mapper

import br.com.fiap.postech.lanchonete.application.domain.dto.NovoPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.model.Pedido
import br.com.fiap.postech.lanchonete.application.domain.service.ProdutoService
import org.springframework.stereotype.Component

@Component
class PedidoFormMapper(
    private val produtoService: ProdutoService
): Mapper<NovoPedidoForm, Pedido> {
    override fun map(p: NovoPedidoForm): Pedido {
        return Pedido(
            cliente = p.cliente,
            lanche = p.lanche?.let { produtoService.buscarProdutoPorId(it) },
            acompanhamento = p.acompanhamento?.let { produtoService.buscarProdutoPorId(it) },
            bebida = p.bebida?.let { produtoService.buscarProdutoPorId(it) }
        )
    }
}
