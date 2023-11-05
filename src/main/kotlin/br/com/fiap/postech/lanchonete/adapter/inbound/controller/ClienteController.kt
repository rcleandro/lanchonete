package br.com.fiap.postech.lanchonete.adapter.inbound.controller

import br.com.fiap.postech.lanchonete.application.domain.dto.AtualizacaoClienteForm
import br.com.fiap.postech.lanchonete.application.domain.dto.ClienteView
import br.com.fiap.postech.lanchonete.application.domain.dto.NovoClienteForm
import br.com.fiap.postech.lanchonete.application.domain.service.ClienteService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/clientes")
class ClienteController(private val service: ClienteService) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeCliente: String?,
        @PageableDefault(size = 50, sort = ["nome"]) pageable: Pageable
    ): Page<ClienteView> {
        return service.listar(nomeCliente, pageable)
    }

    @GetMapping("/cpf/{cpf}")
    fun buscarPorCpf(@PathVariable cpf: String): ClienteView {
        return service.buscarPorCpf(cpf)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoClienteForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ClienteView> {
        val clienteView = service.cadastrar(form)
        val uri = uriBuilder.path("/cliente/${clienteView.cpf}").build().toUri()
        return ResponseEntity.created(uri).body(clienteView)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizacaoClienteForm): ResponseEntity<ClienteView> {
        val clienteView = service.atualizar(form)
        return ResponseEntity.ok(clienteView)
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable cpf: String) {
        service.deletar(cpf)
    }

    @GetMapping("/signup/{cpf}")
    fun signupPorCpf(@PathVariable cpf: String): String {
        return service.signupPorCpf(cpf)
    }

    @GetMapping("/login/{cpf}")
    fun loginPorCpf(@PathVariable cpf: String): String {
        return service.loginPorCpf(cpf)
    }
}