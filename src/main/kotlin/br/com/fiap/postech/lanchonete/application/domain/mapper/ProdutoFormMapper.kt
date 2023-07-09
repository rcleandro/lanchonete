package br.com.fiap.postech.lanchonete.application.domain.mapper

import br.com.fiap.postech.lanchonete.application.domain.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.application.domain.model.Categoria
import br.com.fiap.postech.lanchonete.application.domain.model.Produto
import org.springframework.stereotype.Component

@Component
class ProdutoFormMapper: Mapper<NovoProdutoForm, Produto> {
    override fun map(p: NovoProdutoForm): Produto {
        return Produto(
            nome = p.nome,
            descricao = p.descricao,
            preco = p.preco,
            categoria = Categoria.values()[p.categoria]
        )
    }
}
