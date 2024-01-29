package br.com.fiap.postech.lanchonete.service

import br.com.fiap.postech.lanchonete.repository.ClienteRepository
import br.com.fiap.postech.lanchonete.dto.AtualizacaoClienteForm
import br.com.fiap.postech.lanchonete.dto.ClienteView
import br.com.fiap.postech.lanchonete.dto.NovoClienteForm
import br.com.fiap.postech.lanchonete.mapper.ClienteFormMapper
import br.com.fiap.postech.lanchonete.mapper.ClienteViewMapper
import br.com.fiap.postech.lanchonete.model.Cliente
import br.com.fiap.postech.lanchonete.exception.NotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ClienteServiceImpl(
    private val repository: ClienteRepository,
    private val clienteViewMapper: ClienteViewMapper,
    private val clienteFormMapper: ClienteFormMapper,
) : ClienteService {

    private val notFoundMessage: String = "Cliente não encontrado"
    private val url: String = "https://usuarioslanchonete.auth.us-east-1.amazoncognito.com"
    private val responseType: String = "code"
    private val scope: String = "email+openid+phone"
    private val clientId: String = "43nb6qqpo24q5k5cv3u0j6k7sh"
    private val redirectUri: String = "https%3A%2F%2Fexemplo.com"

    override fun cadastrar(form: NovoClienteForm): ClienteView {
        val cliente: Cliente = clienteFormMapper.map(form)
        repository.save(cliente)
        return clienteViewMapper.map(cliente)
    }

    override fun buscarPorCpf(cpf: String): ClienteView {
        val cliente = repository.findById(cpf)
            .orElseThrow { NotFoundException(notFoundMessage) }
        return clienteViewMapper.map(cliente)
    }

    override fun listar(
        nomeCliente: String?, pageable: Pageable
    ): Page<ClienteView> {
        val clientes = if (nomeCliente == null) repository.findAll(pageable)
        else repository.findByNome(nomeCliente, pageable)
        return clientes.map { p -> clienteViewMapper.map(p) }
    }

    override fun atualizar(form: AtualizacaoClienteForm): ClienteView {
        val cliente = repository.findById(form.cpf).orElseThrow { NotFoundException(notFoundMessage) }
        cliente.cpf = form.cpf
        cliente.nome = form.nome
        cliente.email = form.email
        return clienteViewMapper.map(cliente)
    }

    override fun deletar(cpf: String) {
        repository.deleteById(cpf)
    }

    override fun signupPorCpf(username: String): String {
        return "$url/signup?client_id=$clientId&response_type=$responseType&scope=$scope&redirect_uri=$redirectUri&username=$username"
    }

    override fun loginPorCpf(username: String): String {
        return "$url/login?client_id=$clientId&response_type=$responseType&scope=$scope&redirect_uri=$redirectUri&username=$username"
    }
}