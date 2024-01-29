package br.com.fiap.postech.lanchonete.service

import br.com.fiap.postech.lanchonete.repository.ProdutoRepository
import br.com.fiap.postech.lanchonete.dto.AtualizacaoProdutoForm
import br.com.fiap.postech.lanchonete.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.dto.ProdutoView
import br.com.fiap.postech.lanchonete.mapper.ProdutoFormMapper
import br.com.fiap.postech.lanchonete.mapper.ProdutoViewMapper
import br.com.fiap.postech.lanchonete.model.Categoria
import br.com.fiap.postech.lanchonete.model.Produto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.random.Random

class ProdutoServiceTest {

    @InjectMocks
    private lateinit var produtoService: ProdutoServiceImpl

    @Mock
    private lateinit var produtoRepository: ProdutoRepository

    @Mock
    private lateinit var produtoViewMapper: ProdutoViewMapper

    @Mock
    private lateinit var produtoFormMapper: ProdutoFormMapper

    private lateinit var openMock: AutoCloseable

    @BeforeEach
    fun setup() {
        openMock = MockitoAnnotations.openMocks(this)
    }

    @AfterEach
    fun tearDown() = openMock.close()

    @Test
    fun devePermitirCadastrarProduto() {
        // Arrange
        val produto = gerarProduto()
        val produtoForm = gerarProdutoForm(produto)
        val produtoView = gerarProdutoView(produto)

        `when`(produtoRepository.save(any(Produto::class.java)))
            .thenAnswer { i -> i.getArgument(0) }
        `when`(produtoFormMapper.map(produtoForm)).thenReturn(produto)
        `when`(produtoViewMapper.map(produto)).thenReturn(produtoView)

        // Act
        val produtoRegistrado = produtoService.cadastrar(produtoForm)

        // Assert
        assertThat(produtoRegistrado)
            .isInstanceOf(ProdutoView::class.java)
            .isNotNull
            .isEqualTo(produtoView)

        assertThat(produto.id)
            .isNotNull()

        verify(produtoRepository, times(1))
            .save(any(Produto::class.java))
        verify(produtoFormMapper).map(produtoForm)
        verify(produtoViewMapper).map(produto)
    }

    @Test
    fun devePermitirBuscarProdutoPorId() {
        // Arrange
        val produto = gerarProduto()

        `when`(produtoRepository.findById(produto.id!!)).thenReturn(Optional.of(produto))

        // Act
        val produtoRecebidoOpcional = produtoService.buscarProdutoPorId(produto.id!!)

        // Assert
        assertThat(produtoRecebidoOpcional)
            .isInstanceOf(Produto::class.java)
            .isNotNull
            .isEqualTo(produto)

        verify(produtoRepository, times(1))
            .findById(produto.id!!)
    }

    @Test
    fun devePermitirBuscarProdutoPorCategoria() {
        // Arrange
        val produto = gerarProduto()
        val produtoView = gerarProdutoView(produto)

        val page = PageImpl(listOf(produto))
        val pageable: PageRequest = PageRequest.of(0, 50)

        `when`(produtoRepository.findByCategoria(produto.categoria, pageable)).thenReturn(page)
        `when`(produtoViewMapper.map(produto)).thenReturn(produtoView)

        // Act
        val produtoRecebidoOpcional = produtoService.buscarPorCategoria(produto.categoria, pageable)

        // Assert
        assertThat(produtoRecebidoOpcional)
            .isInstanceOf(Page::class.java)
            .isNotNull
            .containsExactlyInAnyOrder(produtoView)

        verify(produtoRepository, times(1))
            .findByCategoria(produto.categoria, pageable)
        verify(produtoViewMapper).map(produto)
    }

    @Test
    fun devePermitirListarProduto() {
        // Arrange
        val produto1 = gerarProduto()
        val produto2 = gerarProduto()

        val produtoView1 = gerarProdutoView(produto1)
        val produtoView2 = gerarProdutoView(produto2)

        val listaProdutos = listOf(produto1, produto2)

        val page = PageImpl(listaProdutos)
        val pageable: PageRequest = PageRequest.of(0, 50)
        `when`(produtoRepository.findAll(pageable)).thenReturn(page)
        `when`(produtoViewMapper.map(produto1)).thenReturn(produtoView1)
        `when`(produtoViewMapper.map(produto2)).thenReturn(produtoView2)

        // Act
        val produtoRecebidoOpcional = produtoService.listar(pageable)

        // Assert
        assertThat(produtoRecebidoOpcional)
            .isInstanceOf(Page::class.java)
            .isNotNull
            .hasSize(2)
            .containsExactlyInAnyOrder(produtoView1, produtoView2)

        verify(produtoRepository, times(1))
            .findAll(pageable)
        verify(produtoViewMapper).map(produto1)
        verify(produtoViewMapper).map(produto2)
    }

    @Test
    fun devePermitirAtualizarProduto() {
        // Arrange
        val produto = gerarProduto()

        val novoNome = "Guarani 1L"
        produto.nome = novoNome

        val produtoView = gerarProdutoView(produto)
        val produtoFormAtualizado = gerarProdutoFormAtualizado(produto)

        `when`(produtoRepository.findById(produto.id!!)).thenReturn(Optional.of(produto))
        `when`(produtoViewMapper.map(produto)).thenReturn(produtoView)

        // Act
        val produtoRecebidoOpcional = produtoService.atualizar(produtoFormAtualizado)

        // Assert
        assertThat(produtoRecebidoOpcional)
            .isInstanceOf(ProdutoView::class.java)
            .isNotNull
            .isEqualTo(produtoView)

        verify(produtoRepository, times(1))
            .findById(produto.id!!)
    }

    @Test
    fun devePermitirDeletarProduto() {
        // Arrange
        val produto = gerarProduto()

        // Act
        produtoService.deletar(produto.id!!)
        val produtoRecebidoOpcional = produtoRepository.findById(produto.id!!)

        // Assert
        assertThat(produtoRecebidoOpcional)
            .isEmpty
    }

    private fun gerarProduto(): Produto {
        return Produto(
            id = Random.nextLong(),
            nome = "Coca-Cola 600ml",
            descricao = "Coca-Cola 600ml",
            preco = 7.99,
            categoria = Categoria.BEBIDA
        )
    }

    private fun gerarProdutoView(produto: Produto): ProdutoView {
        return ProdutoView(
            id = produto.id,
            nome = produto.nome,
            descricao = produto.descricao,
            preco = produto.preco,
        )
    }

    private fun gerarProdutoForm(produto: Produto): NovoProdutoForm {
        return NovoProdutoForm(
            nome = produto.nome,
            descricao = produto.descricao,
            preco = produto.preco,
            categoria = produto.categoria.ordinal
        )
    }

    private fun gerarProdutoFormAtualizado(produto: Produto): AtualizacaoProdutoForm {
        return AtualizacaoProdutoForm(
            id = produto.id ?: Random.nextLong(),
            nome = produto.nome,
            descricao = produto.descricao,
            preco = produto.preco,
            categoria = produto.categoria
        )
    }
}