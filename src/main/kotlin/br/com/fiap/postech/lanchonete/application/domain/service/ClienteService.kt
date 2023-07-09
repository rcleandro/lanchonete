package br.com.fiap.postech.lanchonete.application.domain.service

import br.com.fiap.postech.lanchonete.adapter.outbound.repository.ClienteRepository
import br.com.fiap.postech.lanchonete.application.domain.dto.AtualizacaoClienteForm
import br.com.fiap.postech.lanchonete.application.domain.dto.ClienteView
import br.com.fiap.postech.lanchonete.application.domain.dto.NovoClienteForm
import br.com.fiap.postech.lanchonete.application.domain.mapper.ClienteFormMapper
import br.com.fiap.postech.lanchonete.application.domain.mapper.ClienteViewMapper
import br.com.fiap.postech.lanchonete.application.domain.model.Cliente
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ClienteService(
    private val repository: ClienteRepository,
    private val clienteViewMapper: ClienteViewMapper,
    private val clienteFormMapper: ClienteFormMapper,
) {

    private val notFoundMessage: String = "Cliente não encontrado"

    fun listar(
        nomeCliente: String?,
        pageable: Pageable
    ): Page<ClienteView> {
        val clientes = if (nomeCliente == null) repository.findAll(pageable)
        else repository.findByNome(nomeCliente, pageable)
        return clientes.map { p -> clienteViewMapper.map(p) }
    }

    fun buscarPorCpf(cpf: String): ClienteView {
        val cliente = repository.findById(cpf).orElseThrow{ NotFoundException(notFoundMessage) }
        return clienteViewMapper.map(cliente)
    }

    fun cadastrar(form: NovoClienteForm): ClienteView {
        val cliente: Cliente = clienteFormMapper.map(form)
        repository.save(cliente)
        return clienteViewMapper.map(cliente)
    }

    fun atualizar(form: AtualizacaoClienteForm): ClienteView {
        val cliente = repository.findById(form.cpf).orElseThrow{ NotFoundException(notFoundMessage) }
        cliente.cpf = form.cpf
        cliente.nome = form.nome
        cliente.email = form.email
        return clienteViewMapper.map(cliente)
    }

    fun deletar(cpf: String) {
        repository.deleteById(cpf)
    }
}