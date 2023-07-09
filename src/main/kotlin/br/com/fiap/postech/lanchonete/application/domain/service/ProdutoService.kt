package br.com.fiap.postech.lanchonete.application.domain.service

import br.com.fiap.postech.lanchonete.adapter.outbound.repository.ProdutoRepository
import br.com.fiap.postech.lanchonete.application.domain.dto.AtualizacaoProdutoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.ProdutoView
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import br.com.fiap.postech.lanchonete.application.domain.mapper.ProdutoFormMapper
import br.com.fiap.postech.lanchonete.application.domain.mapper.ProdutoViewMapper
import br.com.fiap.postech.lanchonete.application.domain.model.Categoria
import br.com.fiap.postech.lanchonete.application.domain.model.Produto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ProdutoService(
    private val repository: ProdutoRepository,
    private val produtoViewMapper: ProdutoViewMapper,
    private val produtoFormMapper: ProdutoFormMapper
) {

    private val notFoundMessage: String = "Produto não encontrado"

    fun listar(pageable: Pageable): Page<ProdutoView> {
        return repository.findAll(pageable).map { p -> produtoViewMapper.map(p) }
    }

    fun buscarPorId(id: Long): ProdutoView {
        val produto = repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
        return produtoViewMapper.map(produto)
    }

    fun buscarProdutoPorId(id: Long): Produto {
        return repository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
    }

    fun buscarPorCategoria(
        categoria: Categoria,
        pageable: Pageable
    ): Page<ProdutoView> {
        val produto = repository.findByCategoria(categoria, pageable)
        return produto.map { p -> produtoViewMapper.map(p) }
    }

    fun cadastrar(form: NovoProdutoForm): ProdutoView {
        val produto: Produto = produtoFormMapper.map(form)
        repository.save(produto)
        return produtoViewMapper.map(produto)
    }

    fun atualizar(form: AtualizacaoProdutoForm): ProdutoView {
        val produto =  repository.findById(form.id).orElseThrow{ NotFoundException(notFoundMessage) }
        produto.categoria = form.categoria
        produto.nome = form.nome
        produto.preco = form.preco
        produto.descricao = form.descricao
        return produtoViewMapper.map(produto)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}