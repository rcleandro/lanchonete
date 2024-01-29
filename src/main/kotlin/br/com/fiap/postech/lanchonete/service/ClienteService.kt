package br.com.fiap.postech.lanchonete.service

import br.com.fiap.postech.lanchonete.dto.AtualizacaoClienteForm
import br.com.fiap.postech.lanchonete.dto.ClienteView
import br.com.fiap.postech.lanchonete.dto.NovoClienteForm
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ClienteService {

    fun cadastrar(form: NovoClienteForm): ClienteView

    fun buscarPorCpf(cpf: String): ClienteView

    fun listar(nomeCliente: String?, pageable: Pageable): Page<ClienteView>

    fun atualizar(form: AtualizacaoClienteForm): ClienteView

    fun deletar(cpf: String)

    fun signupPorCpf(username: String): String

    fun loginPorCpf(username: String): String
}