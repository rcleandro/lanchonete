package br.com.fiap.postech.lanchonete.adapter.outbound.repository

import br.com.fiap.postech.lanchonete.application.domain.model.Categoria
import br.com.fiap.postech.lanchonete.application.domain.model.Produto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepository:JpaRepository<Produto, Long> {
    fun findByCategoria(categoria: Categoria, pageable: Pageable): Page<Produto>
}