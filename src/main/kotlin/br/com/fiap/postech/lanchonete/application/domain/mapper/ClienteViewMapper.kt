package br.com.fiap.postech.lanchonete.application.domain.mapper

import br.com.fiap.postech.lanchonete.application.domain.dto.ClienteView
import br.com.fiap.postech.lanchonete.application.domain.model.Cliente
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