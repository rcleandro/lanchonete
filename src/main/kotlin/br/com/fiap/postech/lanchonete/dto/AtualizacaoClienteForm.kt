package br.com.fiap.postech.lanchonete.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class AtualizacaoClienteForm(
    @field:NotBlank(message = "CPF deve ser informado")
    @field:Size(message = "CPF inválido", min = 11, max = 11)
    val cpf: String,

    @field:NotBlank(message = "Nome deve ser informado")
    @field:Size(message = "Nome não deve ultrapassar a quantidade máxima de 50 caracteres", max = 50)
    val nome: String,

    @field:NotBlank(message = "Email deve ser informado")
    @field:Email(message = "Email inválido")
    val email: String
)