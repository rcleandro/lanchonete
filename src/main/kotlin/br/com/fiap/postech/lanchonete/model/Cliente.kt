package br.com.fiap.postech.lanchonete.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
data class Cliente(
    @Id
    @NotBlank
    @Size(min = 11, max = 11)
    var cpf: String,

    @NotBlank
    @Size(max = 50)
    var nome: String,

    @NotBlank
    @Email
    var email: String
)
