package br.com.fiap.postech.lanchonete.mapper

import br.com.fiap.postech.lanchonete.dto.NovoClienteForm
import br.com.fiap.postech.lanchonete.model.Cliente
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
