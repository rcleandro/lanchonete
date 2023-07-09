package br.com.fiap.postech.lanchonete.application.domain.mapper

interface Mapper<P, U> {
    fun map(p: P): U
}
