package br.com.fiap.postech.lanchonete.application.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Cliente(
    @Id
    var cpf: String,
    var nome: String,
    var email: String
)
