package br.com.fiap.postech.lanchonete.adapter.inbound.controller

import br.com.fiap.postech.lanchonete.application.domain.dto.CheckoutPedidoForm
import br.com.fiap.postech.lanchonete.application.domain.dto.PedidoView
import br.com.fiap.postech.lanchonete.application.domain.service.CheckoutService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/checkout")
class CheckoutController(private val service: CheckoutService) {

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid form: CheckoutPedidoForm): ResponseEntity<PedidoView> {
        val produtoView = service.atualizar(form)
        return ResponseEntity.ok(produtoView)
    }
}