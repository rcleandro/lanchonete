package br.com.fiap.postech.lanchonete.repository

import br.com.fiap.postech.lanchonete.model.Cliente
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.random.Random

class ClienteRepositoryTest {

    @Mock
    private lateinit var clienteRepository: ClienteRepository
    private lateinit var openMock: AutoCloseable

    @BeforeEach
    fun setup() {
        openMock = MockitoAnnotations.openMocks(this)
    }

    @AfterEach
    fun tearDown() = openMock.close()

    @Test
    fun devePermitirCadastrarCliente() {
        // Arrange
        val cliente = gerarCliente()

        `when`(clienteRepository.save(any(Cliente::class.java))).thenReturn(cliente)

        // Act
        val clienteRegistrado = clienteRepository.save(cliente)

        // Assert
        assertThat(clienteRegistrado)
            .isInstanceOf(Cliente::class.java)
            .isNotNull
            .isEqualTo(cliente)

        verify(clienteRepository, times(1))
            .save(any(Cliente::class.java))
    }

    @Test
    fun devePermitirBuscarClientePorNome() {
        // Arrange
        val cliente = gerarCliente()

        val page = PageImpl(listOf(cliente))
        val pageable: PageRequest = PageRequest.of(0, 50)
        `when`(clienteRepository.findByNome(cliente.nome, pageable)).thenReturn(page)

        // Act
        val result: Page<Cliente> = clienteRepository.findByNome(cliente.nome, pageable)
        val clienteRecebidoOpcional = result.content.firstOrNull()

        // Assert
        assertThat(clienteRecebidoOpcional)
            .isInstanceOf(Cliente::class.java)
            .isEqualTo(cliente)

        verify(clienteRepository, times(1))
            .findByNome(cliente.nome, pageable)
    }

    @Test
    fun devePermitirBuscarClientesPorCpf() {
        // Arrange
        val cliente = gerarCliente()

        `when`(clienteRepository.findById(cliente.cpf)).thenReturn(Optional.of(cliente))

        // Act
        val clienteRecebidoOpcional = clienteRepository.findById(cliente.cpf)

        // Assert
        assertThat(clienteRecebidoOpcional).isPresent

        clienteRecebidoOpcional.ifPresent { clienteRecebido ->
            assertThat(clienteRecebido)
                .isInstanceOf(Cliente::class.java)
                .isEqualTo(cliente)
        }

        verify(clienteRepository, times(1))
            .findById(cliente.cpf)
    }

    @Test
    fun devePermitirListarClientes() {
        // Arrange
        val cliente1 = gerarCliente()
        val cliente2 = gerarCliente()
        val listaClientes = listOf(cliente1, cliente2)

        `when`(clienteRepository.findAll()).thenReturn(listaClientes)

        // Act
        val clientesRecebidoOpcional = clienteRepository.findAll()

        // Assert
        assertThat(clientesRecebidoOpcional)
            .hasSize(2)
            .containsExactlyInAnyOrder(cliente1, cliente2)

        verify(clienteRepository, times(1))
            .findAll()
    }

    @Test
    fun devePermitirAtualizarCliente() {
        // Arrange
        val cliente = gerarCliente()

        val novoNome = "Maria"
        cliente.nome = novoNome

        `when`(clienteRepository.findById(cliente.cpf)).thenReturn(Optional.of(cliente))

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

        verify(clienteRepository, times(1))
            .findById(cliente.cpf)
    }

    @Test
    fun devePermitirDeletarCliente() {
        // Arrange
        val cliente = gerarCliente()

        // Act
        clienteRepository.delete(cliente)
        val clienteRecebidoOpcional = clienteRepository.findById(cliente.cpf)

        // Assert
        assertThat(clienteRecebidoOpcional)
            .isEmpty

        verify(clienteRepository, times(1))
            .delete(cliente)
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

