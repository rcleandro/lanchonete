package br.com.fiap.postech.lanchonete.repository

import br.com.fiap.postech.lanchonete.model.Cliente
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository: JpaRepository<Cliente, String> {
    fun findByNome(nomeCliente: String, pageable: Pageable): Page<Cliente>
}