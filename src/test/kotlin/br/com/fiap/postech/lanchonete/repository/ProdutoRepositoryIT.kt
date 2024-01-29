package br.com.fiap.postech.lanchonete.repository

import br.com.fiap.postech.lanchonete.model.Categoria
import br.com.fiap.postech.lanchonete.model.Produto
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import kotlin.random.Random

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ProdutoRepositoryIT {

    @Autowired
    private lateinit var produtoRepository: ProdutoRepository

    @Test
    fun devePermitirCriarTabela() {
        val totalDeRegistros = produtoRepository.count()
        Assertions.assertThat(totalDeRegistros).isNotNegative()
    }

    @Test
    fun devePermitirBuscarProdutoPorCategoria() {
        // Arrange
        val produto = gerarProduto()
        val produtoRegistrado = produtoRepository.save(produto)

        // Act
        val pageable: PageRequest = PageRequest.of(0, 50)
        val result: Page<Produto?> = produtoRepository.findByCategoria(produto.categoria, pageable)
        val produtoRecebidoOpcional = result.content.firstOrNull()

        // Assert
        Assertions.assertThat(produtoRegistrado)
            .isInstanceOf(Produto::class.java)
            .isNotNull
            .isEqualTo(produto)

        Assertions.assertThat(produtoRecebidoOpcional)
            .isInstanceOf(Produto::class.java)
            .isEqualTo(produto)
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