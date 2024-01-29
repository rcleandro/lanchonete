package br.com.fiap.postech.lanchonete.mapper

interface Mapper<P, U> {
    fun map(p: P): U
}
