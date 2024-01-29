package br.com.fiap.postech.lanchonete.mapper

import br.com.fiap.postech.lanchonete.dto.ProdutoView
import br.com.fiap.postech.lanchonete.model.Produto
import org.springframework.stereotype.Component

@Component
class ProdutoViewMapper: Mapper<Produto, ProdutoView> {
    override fun map(p: Produto): ProdutoView {
        return ProdutoView(
            id = p.id,
            nome = p.nome,
            descricao = p.descricao,
            preco = p.preco
        )
    }
}