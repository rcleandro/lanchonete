package br.com.fiap.postech.lanchonete.mapper

import br.com.fiap.postech.lanchonete.dto.NovoProdutoForm
import br.com.fiap.postech.lanchonete.model.Categoria
import br.com.fiap.postech.lanchonete.model.Produto
import org.springframework.stereotype.Component

@Component
class ProdutoFormMapper: Mapper<NovoProdutoForm, Produto> {
    override fun map(p: NovoProdutoForm): Produto {
        return Produto(
            nome = p.nome,
            descricao = p.descricao,
            preco = p.preco,
            categoria = Categoria.entries[p.categoria]
        )
    }
}
