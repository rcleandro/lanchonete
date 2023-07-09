package br.com.fiap.postech.lanchonete.adapter.inbound.controller

import br.com.fiap.postech.lanchonete.application.domain.dto.AtualizacaoProdutoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.ProdutoView
import br.com.fiap.postech.lanchonete.application.domain.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.application.domain.model.Categoria
import br.com.fiap.postech.lanchonete.application.domain.service.ProdutoService
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
@RequestMapping("/produtos")
class ProdutoController(private val service: ProdutoService) {

    @GetMapping
    fun listar(@PageableDefault(size = 50, sort = ["nome"]) pageable: Pageable): Page<ProdutoView> {
        return service.listar(pageable)
    }

    @GetMapping("/id/{id}")
    fun buscarPorId(@PathVariable id: Long): ProdutoView {
        return service.buscarPorId(id)
    }

    @GetMapping("/categoria/{categoria}")
    fun buscarPorCategoria(
        @PathVariable categoria: Int,
        @PageableDefault(size = 50, sort = ["nome"]) pageable: Pageable
    ): Page<ProdutoView> {
        return service.buscarPorCategoria(Categoria.values()[categoria], pageable)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoProdutoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ProdutoView> {
        val produtoView = service.cadastrar(form)
        val uri = uriBuilder.path("/produto/${produtoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(produtoView)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: AtualizacaoProdutoForm): ResponseEntity<ProdutoView> {
        val produtoView = service.atualizar(form)
        return ResponseEntity.ok(produtoView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}