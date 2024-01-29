# lanchoneteAPI 🍔🥤🍟🍗🌭🥪

API de gerenciamento de pedidos de uma lanchonete, desenvolvida para avaliação da fase 4 da pós-graduação em Arquitetura de Software - FIAP.


## Desenvolvimento

API desenvolvida em Kotlin utilizando Springboot e MySQL.

A escolha de migrar para o MySQL como banco de dados é justificada pelo seu amplo suporte, desempenho robusto, capacidade de escalabilidade e integração com a maioria das aplicações web, tornando-o uma opção confiável para atender às necessidades do nosso modelo de modelagem de dados.


## Build

Para o build do projeto deverá instalar o Docker no seu computador, faça o clone do repositório e execute o Dockerfile com os seguintes comandos no Terminal/Prompt:
- docker build -t lanchonete .
- docker run -p 8080:8080 --name lanchoneteAPI lanchonete:latest


## Uso da API

Após o build do projeto a API estará disponível no endereço http://localhost:8080/ com os seguintes comandos:


### Produtos:

- POST /produtos (Cadastro de um novo produto)

![Captura de Tela 2023-07-09 às 21 02 45](https://github.com/rcleandro/lanchonete/assets/60019021/533861cc-d7a4-453d-ace4-9c5996109b03)


- GET /produtos (Lista todos os produto)

![Captura de Tela 2023-07-09 às 21 03 12](https://github.com/rcleandro/lanchonete/assets/60019021/667bd636-7571-4725-b759-7b4febb6fe08)


- GET /pedido/id/{id} (Busca pedido por id)

![Captura de Tela 2023-07-09 às 21 03 37](https://github.com/rcleandro/lanchonete/assets/60019021/a8bec5a1-417a-48bd-b72e-0957705d76a2)


- GET /produtos/categoria/{categoria} (Lista os produtos por categoria)

![Captura de Tela 2023-07-09 às 21 04 04](https://github.com/rcleandro/lanchonete/assets/60019021/287a5f5d-1d35-429a-9f4c-312158d4dbc2)


- PUT /produtos/{id} (Edita o produto por id) 

![Captura de Tela 2023-07-09 às 21 04 36](https://github.com/rcleandro/lanchonete/assets/60019021/4ff10261-b2ad-4889-bf3f-a88702e31384)


- DELETE /produtos/{id} (Exclui produto por id)

![Captura de Tela 2023-07-09 às 21 04 59](https://github.com/rcleandro/lanchonete/assets/60019021/930f9f45-7988-454c-89ec-ae7249a8742f)



### Clientes:

- POST /clientes (Cadastro de um novo cliente)

![Captura de Tela 2023-07-09 às 21 05 45](https://github.com/rcleandro/lanchonete/assets/60019021/38d7799d-b33e-4d7f-8d6f-059f2ebb8d49)


- GET /clientes (Lista todos os clientes)

![Captura de Tela 2023-07-09 às 21 06 37](https://github.com/rcleandro/lanchonete/assets/60019021/89021ef9-518b-4d47-a2d8-a51045754cd8)


- GET /clientes/cpf/{cpf} (Busca cliente por cpf)

![Captura de Tela 2023-07-09 às 21 06 53](https://github.com/rcleandro/lanchonete/assets/60019021/bf36dc04-b104-41ee-b83d-6db7d2e025d2)


- GET /clientes?nomeCliente={nome} (Busca cliente por nome)

![Captura de Tela 2023-07-09 às 21 07 13](https://github.com/rcleandro/lanchonete/assets/60019021/46c32e94-ba00-43d1-aa76-d8b2c53d45ef)


- PUT /clientes/{cpf} (Edita o cliente por cpf)

![Captura de Tela 2023-07-09 às 21 07 38](https://github.com/rcleandro/lanchonete/assets/60019021/cfbae1dc-5735-4155-89eb-4eb215471f75)


- DELETE /clientes/{cpf} (Exclui cliente por cpf)

![Captura de Tela 2023-07-09 às 21 08 00](https://github.com/rcleandro/lanchonete/assets/60019021/2d17f54e-cbe7-43e3-a36f-10a02da2cb2c)



### Pedidos:

- POST /pedidos (Cadastro de um novo pedido)

![Captura de Tela 2023-07-09 às 21 10 10](https://github.com/rcleandro/lanchonete/assets/60019021/82434e61-62c1-472a-bea9-2d7513b718f6)


- GET /pedidos (Lista todos os pedidos)

![Captura de Tela 2023-07-09 às 21 10 33](https://github.com/rcleandro/lanchonete/assets/60019021/04759652-500d-4366-861a-9c5284061dae)


- GET /pedido/id/{id} (Busca pedido por id)

![Captura de Tela 2023-07-09 às 21 10 59](https://github.com/rcleandro/lanchonete/assets/60019021/86e6af34-0829-4bb0-802a-61ba5fc8710d)


- PUT /pedidos (Edita o pedido por id)

![Captura de Tela 2023-07-09 às 21 11 22](https://github.com/rcleandro/lanchonete/assets/60019021/ed2dac44-b5cd-4fcc-b855-8078126fd52f)


- DELETE /pedidos/{id} (Exclui pedido por id)

![Captura de Tela 2023-07-09 às 21 11 39](https://github.com/rcleandro/lanchonete/assets/60019021/d99ce853-af81-453c-b000-8871354792a9)



### Checkout:


- PUT /checkout (Faz o checkout do pedido)



### Status do pagamento:


- PUT /statusPagamento/{id} (Consulta o status do pagamento por id do pedido)



### Signup:


- GET /signup/{cpf} (Direciona para o site para fazer o cadastro de Usuário e senha)



### Login:


- GET /login/{cpf} (Direciona para o site para fazer o Login de Usuário)


