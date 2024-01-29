package br.com.fiap.postech.lanchonete.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
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
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @NotBlank
    @Size(max = 50)
    var nome: String,

    @NotBlank
    @Size(max = 100)
    var descricao: String,

    @NotNull
    @Positive
    var preco: Double,

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    var categoria: Categoria
)
