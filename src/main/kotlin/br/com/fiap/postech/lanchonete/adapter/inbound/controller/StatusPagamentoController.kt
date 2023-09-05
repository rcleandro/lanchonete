package br.com.fiap.postech.lanchonete.adapter.inbound.controller

import br.com.fiap.postech.lanchonete.application.domain.dto.StatusPagamentoView
import br.com.fiap.postech.lanchonete.application.domain.service.StatusPagamentoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/statusPagamento")
class StatusPagamentoController(private val service: StatusPagamentoService) {

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): StatusPagamentoView {
        return service.buscarPorId(id)
    }
}