package br.com.fiap.postech.lanchonete.application.domain.mapper

import br.com.fiap.postech.lanchonete.application.domain.dto.NovoClienteForm
import br.com.fiap.postech.lanchonete.application.domain.model.Cliente
import org.springframework.stereotype.Component

@Component
class ClienteFormMapper: Mapper<NovoClienteForm, Cliente> {
    override fun map(p: NovoClienteForm): Cliente {
        return Cliente(
            cpf = p.cpf,
            nome = p.nome,
            email = p.email
        )
    }
}
