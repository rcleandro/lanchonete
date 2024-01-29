package br.com.fiap.postech.lanchonete.service

import br.com.fiap.postech.lanchonete.repository.ProdutoRepository
import br.com.fiap.postech.lanchonete.dto.AtualizacaoProdutoForm
import br.com.fiap.postech.lanchonete.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.dto.ProdutoView
import br.com.fiap.postech.lanchonete.mapper.ProdutoFormMapper
import br.com.fiap.postech.lanchonete.mapper.ProdutoViewMapper
import br.com.fiap.postech.lanchonete.model.Categoria
import br.com.fiap.postech.lanchonete.model.Produto
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ProdutoServiceImpl(
    private val repository: ProdutoRepository,
    private val produtoViewMapper: ProdutoViewMapper,
    private val produtoFormMapper: ProdutoFormMapper
) : ProdutoService {

    private val notFoundMessage: String = "Produto não encontrado"

    override fun listar(pageable: Pageable): Page<ProdutoView> {
        return repository.findAll(pageable).map { p -> produtoViewMapper.map(p) }
    }

    override fun buscarPorId(id: Long): ProdutoView {
        val produto = repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return produtoViewMapper.map(produto)
    }

    override fun buscarProdutoPorId(id: Long): Produto {
        return repository.findById(id).orElseThrow { NotFoundException(notFoundMessage) }
    }

    override fun buscarPorCategoria(
        categoria: Categoria,
        pageable: Pageable
    ): Page<ProdutoView> {
        val produto = repository.findByCategoria(categoria, pageable)
        return produto.map { p -> p?.let { produtoViewMapper.map(it) } }
    }

    override fun cadastrar(form: NovoProdutoForm): ProdutoView {
        val produto: Produto = produtoFormMapper.map(form)
        repository.save(produto)
        return produtoViewMapper.map(produto)
    }

    override fun atualizar(form: AtualizacaoProdutoForm): ProdutoView {
        val produto = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        produto.categoria = form.categoria
        produto.nome = form.nome
        produto.preco = form.preco
        produto.descricao = form.descricao
        return produtoViewMapper.map(produto)
    }

    override fun deletar(id: Long) {
        repository.deleteById(id)
    }
}