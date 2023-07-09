# lanchoneteAPI 🍔🥤🍟🍗🌭🥪

API de gerenciamento de pedidos de uma lanchonete, desenvolvida para avaliação da fase 1 da pós-graduação em Arquitetura de Software - FIAP.

A API contém as seguintes funcionalidades:
- Cadastro de clientes
- Identificação do cliente (Nome, CPF e E-mail)
- Gerenciamento de produtos (Criar, editar, remover)
- Busca de produtos por categoria (Lanche, acompanhamento, bebida e sobremesa)
- Fake checkout, apenas enviar os produtos escolhidos para a fila
- Lista de pedidos


## Desenvolvimento

API desenvolvida em Kotlin utilizando Springboot e H2 database.


## Build

Para o build do projeto deverá instalar o Docker no seu computador, faça o clone do repositório e execute o Dockerfile com os seguintes comandos no Terminal/Prompt:
- docker build -t lanchonete .
- docker run -p 8080:8080 --name lanchoneteAPI lanchonete:latest


## Uso da API

Após o build do projeto a API estará disponível no endereço http://localhost:8080/ com os seguintes comandos:


### Produtos:

- POST /produtos (Cadastro de um novo produto)

![Captura de Tela 2023-07-09 às 19.18.08.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_iuRYSk%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.18.08.png)


- GET /produtos (Lista todos os produto)

![Captura de Tela 2023-07-09 às 19.25.23.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_2kbrZs%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.25.23.png)


- GET /pedido/id/{id} (Busca pedido por id)

![Captura de Tela 2023-07-09 às 20.05.07.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_mpscYi%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.05.07.png)


- GET /produtos/categoria/{categoria} (Lista os produtos por categoria)

![Captura de Tela 2023-07-09 às 19.25.48.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_Ow39TX%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.25.48.png)


- PUT /produtos/{id} (Edita o produto por id) 

![Captura de Tela 2023-07-09 às 19.32.19.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_lP40Oy%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.32.19.png)


- DELETE /produtos/{id} (Exclui produto por id)

![Captura de Tela 2023-07-09 às 19.58.52.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_HQojcr%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.58.52.png)


### Pedidos:

- POST /pedidos (Cadastro de um novo pedido)

![Captura de Tela 2023-07-09 às 19.35.09.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_ux1xHH%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.35.09.png)


- GET /pedidos (Lista todos os pedidos)

![Captura de Tela 2023-07-09 às 19.36.17.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_Ow2yWr%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.36.17.png)


- GET /pedido/id/{id} (Busca pedido por id)

![Captura de Tela 2023-07-09 às 20.05.47.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_khxwAl%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.05.47.png)


- PUT /pedidos/{id} (Edita o pedido por id)

![Captura de Tela 2023-07-09 às 19.37.47.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_B3SbCP%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2019.37.47.png)


- DELETE /pedidos/{id} (Exclui pedido por id)

![Captura de Tela 2023-07-09 às 20.07.56.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_l2VHnk%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.07.56.png)


### Clientes:

- POST /clientes (Cadastro de um novo cliente)

![Captura de Tela 2023-07-09 às 20.09.47.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_w9KDt8%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.09.47.png)


- GET /clientes (Lista todos os clientes)

![Captura de Tela 2023-07-09 às 20.12.40.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_yycKVI%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.12.40.png)


- GET /clientes/cpf/{cpf} (Busca cliente por cpf)

![Captura de Tela 2023-07-09 às 20.13.23.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_4Bs7VJ%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.13.23.png)


- GET /clientes?nomeCliente={nome} (Busca cliente por nome)

![Captura de Tela 2023-07-09 às 20.12.09.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_QDQKnp%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.12.09.png)


- PUT /clientes/{cpf} (Edita o cliente por cpf)

![Captura de Tela 2023-07-09 às 20.20.18.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_On2e2X%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.20.18.png)


- DELETE /clientes/{cpf} (Exclui cliente por cpf)

![Captura de Tela 2023-07-09 às 20.14.12.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fcl%2Fhgy6mrv55znf9wyfgny3j5s00000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_KTfycE%2FCaptura%20de%20Tela%202023-07-09%20%C3%A0s%2020.14.12.png)


