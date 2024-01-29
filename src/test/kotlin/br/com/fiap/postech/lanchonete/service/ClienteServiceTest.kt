package br.com.fiap.postech.lanchonete.service

import br.com.fiap.postech.lanchonete.repository.ClienteRepository
import br.com.fiap.postech.lanchonete.dto.AtualizacaoClienteForm
import br.com.fiap.postech.lanchonete.dto.ClienteView
import br.com.fiap.postech.lanchonete.dto.NovoClienteForm
import br.com.fiap.postech.lanchonete.mapper.ClienteFormMapper
import br.com.fiap.postech.lanchonete.mapper.ClienteViewMapper
import br.com.fiap.postech.lanchonete.model.Cliente
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.random.Random

class ClienteServiceTest {

    @Mock
    private lateinit var clienteRepository: ClienteRepository

    @Mock
    private lateinit var clienteViewMapper: ClienteViewMapper

    @Mock
    private lateinit var clienteFormMapper: ClienteFormMapper

    @InjectMocks
    private lateinit var clienteService: ClienteServiceImpl

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
        val clienteForm = gerarClienteForm(cliente)
        val clienteView = gerarClienteView(cliente)

        `when`(clienteRepository.save(any(Cliente::class.java)))
            .thenAnswer { i -> i.getArgument(0) }
        `when`(clienteFormMapper.map(clienteForm)).thenReturn(cliente)
        `when`(clienteViewMapper.map(cliente)).thenReturn(clienteView)

        // Act
        val clienteRegistrado = clienteService.cadastrar(clienteForm)

        // Assert
        assertThat(clienteRegistrado)
            .isInstanceOf(ClienteView::class.java)
            .isNotNull
            .isEqualTo(clienteView)

        assertThat(cliente.cpf)
            .isNotNull()
            .isNotEmpty()
            .isNotBlank()

        verify(clienteRepository, times(1))
            .save(any(Cliente::class.java))
        verify(clienteFormMapper).map(clienteForm)
        verify(clienteViewMapper).map(cliente)
    }

    @Test
    fun devePermitirBuscarClientesPorCpf() {
        // Arrange
        val cliente = gerarCliente()
        val clienteView = gerarClienteView(cliente)

        `when`(clienteRepository.findById(cliente.cpf)).thenReturn(Optional.of(cliente))
        `when`(clienteViewMapper.map(cliente)).thenReturn(clienteView)

        // Act
        val clienteRecebidoOpcional = clienteService.buscarPorCpf(cliente.cpf)

        // Assert
        assertThat(clienteRecebidoOpcional)
            .isInstanceOf(ClienteView::class.java)
            .isNotNull
            .isEqualTo(clienteView)

        verify(clienteRepository, times(1))
            .findById(cliente.cpf)
        verify(clienteViewMapper).map(cliente)
    }

    @Test
    fun devePermitirListarClientes() {
        // Arrange
        val cliente1 = gerarCliente()
        val cliente2 = gerarCliente()

        val clienteView1 = gerarClienteView(cliente1)
        val clienteView2 = gerarClienteView(cliente2)

        val listaClientes = listOf(cliente1, cliente2)

        val page = PageImpl(listaClientes)
        val pageable: PageRequest = PageRequest.of(0, 50)
        `when`(clienteRepository.findAll(pageable)).thenReturn(page)
        `when`(clienteViewMapper.map(cliente1)).thenReturn(clienteView1)
        `when`(clienteViewMapper.map(cliente2)).thenReturn(clienteView2)

        // Act
        val clienteRecebidoOpcional = clienteService.listar(null, pageable)

        // Assert
        assertThat(clienteRecebidoOpcional)
            .isInstanceOf(Page::class.java)
            .isNotNull
            .hasSize(2)
            .containsExactlyInAnyOrder(clienteView1, clienteView2)

        verify(clienteRepository, times(1))
            .findAll(pageable)
        verify(clienteViewMapper).map(cliente1)
        verify(clienteViewMapper).map(cliente2)
    }

    @Test
    fun devePermitirAtualizarCliente() {
        // Arrange
        val cliente = gerarCliente()

        val novoNome = "Maria"
        cliente.nome = novoNome

        val clienteView = gerarClienteView(cliente)
        val clienteFormAtualizado = gerarClienteFormAtualizado(cliente)

        `when`(clienteRepository.findById(cliente.cpf)).thenReturn(Optional.of(cliente))
        `when`(clienteViewMapper.map(cliente)).thenReturn(clienteView)

        // Act
        val clienteRecebidoOpcional = clienteService.atualizar(clienteFormAtualizado)

        // Assert
        assertThat(clienteRecebidoOpcional)
            .isInstanceOf(ClienteView::class.java)
            .isNotNull
            .isEqualTo(clienteView)

        verify(clienteRepository, times(1))
            .findById(cliente.cpf)
        verify(clienteViewMapper).map(cliente)
    }

    @Test
    fun devePermitirDeletarCliente() {
        // Arrange
        val cliente = gerarCliente()

        // Act
        clienteService.deletar(cliente.cpf)
        val clienteRecebidoOpcional = clienteRepository.findById(cliente.cpf)

        // Assert
        assertThat(clienteRecebidoOpcional)
            .isEmpty
    }

    private fun gerarCliente(): Cliente {
        return Cliente(
            cpf = gerarCpf(),
            nome = "Jose",
            email = "jose@email.com"
        )
    }

    private fun gerarClienteForm(cliente: Cliente): NovoClienteForm {
        return NovoClienteForm(
            cpf = cliente.cpf,
            nome = cliente.nome,
            email = cliente.email
        )
    }

    private fun gerarClienteView(cliente: Cliente): ClienteView {
        return ClienteView(
            cpf = cliente.cpf,
            nome = cliente.nome,
            email = cliente.email
        )
    }

    private fun gerarClienteFormAtualizado(cliente: Cliente): AtualizacaoClienteForm {
        return AtualizacaoClienteForm(
            cpf = cliente.cpf,
            nome = cliente.nome,
            email = cliente.email
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