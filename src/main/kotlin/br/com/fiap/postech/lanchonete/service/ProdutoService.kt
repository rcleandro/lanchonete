package br.com.fiap.postech.lanchonete.service

import br.com.fiap.postech.lanchonete.dto.AtualizacaoProdutoForm
import br.com.fiap.postech.lanchonete.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.dto.ProdutoView
import br.com.fiap.postech.lanchonete.model.Categoria
import br.com.fiap.postech.lanchonete.model.Produto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProdutoService {

    fun cadastrar(form: NovoProdutoForm): ProdutoView

    fun buscarPorId(id: Long): ProdutoView

    fun buscarProdutoPorId(id: Long): Produto

    fun buscarPorCategoria(categoria: Categoria, pageable: Pageable): Page<ProdutoView>

    fun listar(pageable: Pageable): Page<ProdutoView>

    fun atualizar(form: AtualizacaoProdutoForm): ProdutoView

    fun deletar(id: Long)
}