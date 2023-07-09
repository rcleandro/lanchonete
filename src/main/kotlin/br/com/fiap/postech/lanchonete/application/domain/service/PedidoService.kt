package br.com.fiap.postech.lanchonete.application.domain.service

import br.com.fiap.postech.lanchonete.adapter.outbound.repository.PedidoRepository
import br.com.fiap.postech.lanchonete.application.domain.dto.AtualizacaoPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.NovoPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.PedidoView
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import br.com.fiap.postech.lanchonete.application.domain.mapper.PedidoFormMapper
import br.com.fiap.postech.lanchonete.application.domain.mapper.PedidoViewMapper
import br.com.fiap.postech.lanchonete.application.domain.model.Pedido
import br.com.fiap.postech.lanchonete.application.domain.model.Progresso
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class PedidoService(
    private val repository: PedidoRepository,
    private val pedidoViewMapper: PedidoViewMapper,
    private val pedidoFormMapper: PedidoFormMapper,
    private val produtoService: ProdutoService
) {

    private val notFoundMessage: String = "Pedido não encontrado"

    fun listar(pageable: Pageable): Page<PedidoView> {
        return repository.findAll(pageable).map { p -> pedidoViewMapper.map(p) }
    }

    fun buscarPorId(id: Long): PedidoView {
        val pedido = repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
        return pedidoViewMapper.map(pedido)
    }

    fun buscarPorProgresso(progresso: Progresso, pageable: Pageable): Page<PedidoView> {
        val pedido = repository.findByProgresso(progresso, pageable)
        return pedido.map { p -> pedidoViewMapper.map(p) }
    }

    fun cadastrar(form: NovoPedidoForm): PedidoView {
        val pedido: Pedido = pedidoFormMapper.map(form)
        repository.save(pedido)
        return pedidoViewMapper.map(pedido)
    }

    fun atualizar(form: AtualizacaoPedidoForm): PedidoView {
        val pedido = repository.findById(form.id).orElseThrow{ NotFoundException(notFoundMessage) }
        pedido.lanche = form.lanche?.let { produtoService.buscarProdutoPorId(it) } ?: pedido.lanche
        pedido.acompanhamento = form.acompanhamento?.let { produtoService.buscarProdutoPorId(it) } ?: pedido.acompanhamento
        pedido.bebida = form.bebida?.let { produtoService.buscarProdutoPorId(it) } ?: pedido.bebida
        pedido.progresso = Progresso.values()[form.progresso]
        return pedidoViewMapper.map(pedido)
    }

    fun deletar(id: Long) = repository.deleteById(id)
}