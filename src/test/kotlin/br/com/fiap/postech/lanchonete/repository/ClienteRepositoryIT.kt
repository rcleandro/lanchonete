package br.com.fiap.postech.lanchonete.repository

import br.com.fiap.postech.lanchonete.model.Cliente
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
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
class ClienteRepositoryIT {

    @Autowired
    private lateinit var clienteRepository: ClienteRepository

    @Test
    fun devePermitirCriarTabela() {
        val totalDeRegistros = clienteRepository.count()
        assertThat(totalDeRegistros).isNotNegative()
    }

    @Test
    fun devePermitirCadastrarCliente() {
        // Arrange
        val cliente = gerarCliente()

        // Act
        val clienteRegistrado = clienteRepository.save(cliente)

        // Assert
        assertThat(clienteRegistrado)
            .isInstanceOf(Cliente::class.java)
            .isNotNull
            .isEqualTo(cliente)
    }

    @Test
    fun devePermitirBuscarClientePorNome() {
        // Arrange
        val cliente = gerarCliente()
        val clienteRegistrado = clienteRepository.save(cliente)

        // Act
        val pageable: PageRequest = PageRequest.of(0, 50)
        val result: Page<Cliente> = clienteRepository.findByNome(cliente.nome, pageable)
        val clienteRecebidoOpcional = result.content.firstOrNull()

        // Assert
        assertThat(clienteRegistrado)
            .isInstanceOf(Cliente::class.java)
            .isNotNull
            .isEqualTo(cliente)

        assertThat(clienteRecebidoOpcional)
            .isInstanceOf(Cliente::class.java)
            .isEqualTo(cliente)
    }

    @Test
    fun devePermitirBuscarClientesPorCpf() {
        // Arrange
        val cliente = gerarCliente()
        clienteRepository.save(cliente)

        // Act
        val clienteRecebidoOpcional = clienteRepository.findById(cliente.cpf)

        // Assert
        assertThat(clienteRecebidoOpcional).isPresent

        clienteRecebidoOpcional.ifPresent { clienteRecebido ->
            assertThat(clienteRecebido)
                .isInstanceOf(Cliente::class.java)
                .isEqualTo(cliente)
        }
    }

    @Test
    fun devePermitirListarClientes() {
        // Arrange
        val cliente1 = gerarCliente()
        clienteRepository.save(cliente1)

        val cliente2 = gerarCliente()
        clienteRepository.save(cliente2)

        // Act
        val clientesRecebidoOpcional = clienteRepository.findAll()

        // Assert
        assertThat(clientesRecebidoOpcional)
            .hasSize(2)
            .containsExactlyInAnyOrder(cliente1, cliente2)
    }

    @Test
    fun devePermitirAtualizarCliente() {
        // Arrange
        val cliente = gerarCliente()
        clienteRepository.save(cliente)

        val novoNome = "Maria"
        cliente.nome = novoNome
        clienteRepository.save(cliente)

        // Act
        val clienteRecebidoOpcional = clienteRepository.findById(cliente.cpf)

        // Assert
        assertThat(clienteRecebidoOpcional).isPresent

        clienteRecebidoOpcional.ifPresent { clienteRecebido ->
            assertThat(clienteRecebido)
                .isInstanceOf(Cliente::class.java)
                .isEqualTo(cliente)

            assertThat(clienteRecebido.nome)
                .isEqualTo(novoNome)
        }
    }

    @Test
    fun devePermitirDeletarCliente() {
        // Arrange
        val cliente = gerarCliente()
        clienteRepository.save(cliente)

        // Act
        clienteRepository.delete(cliente)
        val clienteOpcional = clienteRepository.findById(cliente.cpf)

        // Assert
        assertThat(clienteOpcional)
            .isEmpty
    }

    private fun gerarCliente(): Cliente {
        return Cliente(
            cpf = gerarCpf(),
            nome = "Jose",
            email = "jose@email.com"
        )
    }

    private fun gerarCpf(): String {
        val random = Random
        val cpfSemDigito = (1..9).map { random.nextInt(10) }.joinToString("")
        val primeiroDigito = calcularDigito(cpfSemDigito)
        val segundoDigito = calcularDigito(cpfSemDigito + primeiroDigito)

        return cpfSemDigito + primeiroDigito + segundoDigito
    }

    private fun calcularDigito(cpfParcial: String): Int {
        val digitos = cpfParcial.map { it.toString().toInt() }.toMutableList()
        var peso = digitos.size + 1
        val soma = digitos.sumOf { it * peso-- }
        val resto = soma % 11
        val digito = if (resto < 2) 0 else 11 - resto

        return digito
    }
}