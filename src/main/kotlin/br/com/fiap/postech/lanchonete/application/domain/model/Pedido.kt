package br.com.fiap.postech.lanchonete.application.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Pedido(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne
    val cliente: Cliente?,
    @ManyToOne
    var lanche: Produto?,
    @ManyToOne
    var acompanhamento: Produto?,
    @ManyToOne
    var bebida: Produto?,
    @Enumerated(value = EnumType.STRING)
    var progresso: Progresso = Progresso.RECEBIDO,
    val data: LocalDateTime = LocalDateTime.now()
)
