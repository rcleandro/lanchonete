package br.com.fiap.postech.lanchonete.application.domain.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class NovoClienteForm(
    @field:NotEmpty(message = "CPF deve ser informado") @field:Size(message = "CPF inválido", min = 11, max = 11) val cpf: String,
    @field:NotEmpty(message = "Nome deve ser informado") val nome: String,
    @field:NotEmpty(message = "Email deve ser informado") @field:Email(message = "Email inválido") val email: String
)
