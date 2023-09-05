package br.com.fiap.postech.lanchonete.application.domain.service

import br.com.fiap.postech.lanchonete.adapter.outbound.repository.PedidoRepository
import br.com.fiap.postech.lanchonete.application.domain.dto.StatusPagamentoView
import br.com.fiap.postech.lanchonete.application.domain.mapper.StatusPagamentoViewMapper
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class StatusPagamentoService (
    private val repository: PedidoRepository,
    private val statusPagamentoViewMapper: StatusPagamentoViewMapper
) {

    private val notFoundMessage: String = "Pedido não encontrado"

    fun buscarPorId(id: Long): StatusPagamentoView {
        val pedido = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return statusPagamentoViewMapper.map(pedido)
    }
}