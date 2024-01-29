package br.com.fiap.postech.lanchonete.repository

import br.com.fiap.postech.lanchonete.model.Categoria
import br.com.fiap.postech.lanchonete.model.Produto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.random.Random

class ProdutoRepositoryTest {

    @Mock
    private lateinit var produtoRepository: ProdutoRepository
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

        `when`(produtoRepository.save(any(Produto::class.java))).thenReturn(produto)

        // Act
        val produtoRegistrado = produtoRepository.save(produto)

        // Assert
        assertThat(produtoRegistrado)
            .isInstanceOf(Produto::class.java)
            .isNotNull
            .isEqualTo(produto)

        verify(produtoRepository, times(1))
            .save(any(Produto::class.java))
    }

    @Test
    fun devePermitirBuscarProdutoPorId() {
        // Arrange
        val produto = gerarProduto()

        `when`(produtoRepository.findById(produto.id!!)).thenReturn(Optional.of(produto))

        // Act
        val produtoRecebidoOpcional = produtoRepository.findById(produto.id!!)

        // Assert
        assertThat(produtoRecebidoOpcional).isPresent

        produtoRecebidoOpcional.ifPresent { produtoRecebido ->
            assertThat(produtoRecebido)
                .isInstanceOf(Produto::class.java)
                .isEqualTo(produto)
        }

        verify(produtoRepository, times(1))
            .findById(produto.id!!)
    }

    @Test
    fun devePermitirBuscarProdutoPorCategoria() {
        // Arrange
        val produto = gerarProduto()

        `when`(produtoRepository.save(Mockito.any(Produto::class.java))).thenReturn(produto)
        val produtoRegistrado = produtoRepository.save(produto)

        val page = PageImpl(listOf(produto))
        val pageable: PageRequest = PageRequest.of(0, 50)
        `when`(produtoRepository.findByCategoria(produto.categoria, pageable)).thenReturn(page)

        // Act
        val result: Page<Produto?> = produtoRepository.findByCategoria(produto.categoria, pageable)
        val produtoRecebidoOpcional = result.content.firstOrNull()

        // Assert
        assertThat(produtoRegistrado)
            .isInstanceOf(Produto::class.java)
            .isNotNull
            .isEqualTo(produto)

        verify(produtoRepository, times(1))
            .save(any(Produto::class.java))

        assertThat(produtoRecebidoOpcional)
            .isInstanceOf(Produto::class.java)
            .isEqualTo(produto)

        verify(produtoRepository, times(1))
            .findByCategoria(produto.categoria, pageable)
    }

    @Test
    fun devePermitirListarProduto() {
        // Arrange
        val produto1 = gerarProduto()
        val produto2 = gerarProduto()
        val listaProdutos = listOf(produto1, produto2)

        `when`(produtoRepository.findAll()).thenReturn(listaProdutos)

        // Act
        val produtosRecebidoOpcional = produtoRepository.findAll()

        // Assert
        assertThat(produtosRecebidoOpcional)
            .hasSize(2)
            .containsExactlyInAnyOrder(produto1, produto2)

        verify(produtoRepository, times(1))
            .findAll()
    }

    @Test
    fun devePermitirAtualizarProduto() {
        // Arrange
        val produto = gerarProduto()

        val novoNome = "Graraná 1L"
        produto.nome = novoNome

        `when`(produtoRepository.findById(produto.id!!)).thenReturn(Optional.of(produto))

        // Act
        val produtoRecebidoOpcional = produtoRepository.findById(produto.id!!)

        // Assert
        assertThat(produtoRecebidoOpcional).isPresent

        produtoRecebidoOpcional.ifPresent { produtoRecebido ->
            assertThat(produtoRecebido)
                .isInstanceOf(Produto::class.java)
                .isEqualTo(produto)

            assertThat(produtoRecebido.nome)
                .isEqualTo(novoNome)
        }

        verify(produtoRepository, times(1))
            .findById(produto.id!!)
    }

    @Test
    fun devePermitirDeletarProduto() {
        // Arrange
        val produto = gerarProduto()

        // Act
        produtoRepository.delete(produto)
        val produtoRecebidoOpcional = produtoRepository.findById(produto.id!!)

        // Assert
        assertThat(produtoRecebidoOpcional)
            .isEmpty

        verify(produtoRepository, times(1))
            .delete(produto)
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
}