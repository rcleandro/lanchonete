package br.com.fiap.postech.lanchonete.application.domain.service

import br.com.fiap.postech.lanchonete.adapter.outbound.repository.PedidoRepository
import br.com.fiap.postech.lanchonete.application.domain.dto.PedidoView
import br.com.fiap.postech.lanchonete.application.domain.dto.WebhookPagamentoForm
import br.com.fiap.postech.lanchonete.application.domain.mapper.PedidoViewMapper
import br.com.fiap.postech.lanchonete.application.domain.model.StatusPagamento
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class WebhookService(
    private val repository: PedidoRepository,
    private val pedidoViewMapper: PedidoViewMapper
) {

    private val notFoundMessage: String = "Pedido não encontrado"

    fun aprovado(form: WebhookPagamentoForm): PedidoView {
        val pedido = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        pedido.statusPagamento = StatusPagamento.APROVADO
        return pedidoViewMapper.map(pedido)
    }

    fun naoAprovado(form: WebhookPagamentoForm): PedidoView {
        val pedido = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        pedido.statusPagamento = StatusPagamento.NAO_APROVADO
        return pedidoViewMapper.map(pedido)
    }
}