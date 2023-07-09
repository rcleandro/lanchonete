package br.com.fiap.postech.lanchonete.application.domain.model

import jakarta.persistence.*

@Entity
data class Produto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var nome: String,
    var descricao: String,
    var preco: Double,
    @Enumerated(value = EnumType.STRING)
    var categoria: Categoria
)
