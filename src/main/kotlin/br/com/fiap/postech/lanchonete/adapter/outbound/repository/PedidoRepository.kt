package br.com.fiap.postech.lanchonete.adapter.outbound.repository

import br.com.fiap.postech.lanchonete.application.domain.model.Pedido
import br.com.fiap.postech.lanchonete.application.domain.model.Progresso
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PedidoRepository: JpaRepository<Pedido, Long> {
    fun findByProgresso(progresso: Progresso, pageable: Pageable): Page<Pedido>
}