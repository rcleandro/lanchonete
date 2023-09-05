package br.com.fiap.postech.lanchonete.application.domain.service

import br.com.fiap.postech.lanchonete.adapter.outbound.repository.PedidoRepository
import br.com.fiap.postech.lanchonete.application.domain.dto.CheckoutPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.PedidoView
import br.com.fiap.postech.lanchonete.application.domain.mapper.PedidoViewMapper
import br.com.fiap.postech.lanchonete.application.domain.model.Progresso
import br.com.fiap.postech.lanchonete.application.domain.model.StatusPagamento
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class CheckoutService(
    private val repository: PedidoRepository,
    private val pedidoViewMapper: PedidoViewMapper
) {

    private val notFoundMessage: String = "Pedido não encontrado"

    fun atualizar(form: CheckoutPedidoForm): PedidoView {
        val pedido = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        pedido.progresso = Progresso.RECEBIDO
        pedido.statusPagamento = StatusPagamento.EM_PROCESSAMENTO
        return pedidoViewMapper.map(pedido)
    }
}