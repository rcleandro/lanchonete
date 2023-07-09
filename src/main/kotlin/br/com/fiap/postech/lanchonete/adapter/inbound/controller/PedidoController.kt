package br.com.fiap.postech.lanchonete.adapter.inbound.controller

import br.com.fiap.postech.lanchonete.application.domain.dto.AtualizacaoPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.NovoPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.PedidoView
import br.com.fiap.postech.lanchonete.application.domain.model.Progresso
import br.com.fiap.postech.lanchonete.application.domain.service.PedidoService
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
@RequestMapping("/pedidos")
class PedidoController(private val service: PedidoService) {

    @GetMapping
    fun listar(@PageableDefault(size = 50, sort = ["data"]) pageable: Pageable): Page<PedidoView> {
        return service.listar(pageable)
    }

    @GetMapping("/id/{id}")
    fun buscarPorId(@PathVariable id: Long): PedidoView {
        return service.buscarPorId(id)
    }

    @GetMapping("/progresso/{progresso}")
    fun buscarPorProgresso(
        @PathVariable progresso: Int,
        @PageableDefault(size = 50, sort = ["data"]) pageable: Pageable
    ): Page<PedidoView> {
        return service.buscarPorProgresso(Progresso.values()[progresso], pageable)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoPedidoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PedidoView> {
        val pedidoView = service.cadastrar(form)
        val uri = uriBuilder.path("/pedido/${pedidoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(pedidoView)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizacaoPedidoForm): ResponseEntity<PedidoView> {
        val pedidoView = service.atualizar(form)
        return ResponseEntity.ok(pedidoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}