package br.com.fiap.postech.lanchonete.adapter.inbound.controller

import br.com.fiap.postech.lanchonete.application.domain.dto.WebhookPagamentoForm
import br.com.fiap.postech.lanchonete.application.domain.service.WebhookService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class WebhookController(private val service: WebhookService) {

    @PostMapping("/webhook")
    fun handleWebhook(@RequestBody @Valid form: WebhookPagamentoForm): ResponseEntity<String> {
        when (form.status) {
            "approved" -> service.aprovado(form)
            "rejected" -> service.naoAprovado(form)
        }
        return ResponseEntity.ok("Received")
    }
}
