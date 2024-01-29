package br.com.fiap.postech.lanchonete.mapper

import br.com.fiap.postech.lanchonete.dto.ClienteView
import br.com.fiap.postech.lanchonete.model.Cliente
import org.springframework.stereotype.Component

@Component
class ClienteViewMapper: Mapper<Cliente, ClienteView> {
    override fun map(p: Cliente): ClienteView {
        return ClienteView(
            cpf = p.cpf,
            nome = p.nome,
            email = p.email
        )
    }
}