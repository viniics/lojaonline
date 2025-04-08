# lojaonline
Loja Online

Desenvolvido por: 
- Vinícius Soares Nóbrega (122110262)
- Bruno Machado de Assis (119110401)

Repositório criado para a solução do "Lab10" da disciplina de Programação Concorrente!

Para rodar o programa, basta executar o arquivo "_lojaonline\src\test\java\com\lojaonline\lojaonline\LojaonlineApplicationTests.java_"

Ao fazer isso, o servidor sera criado e sera possivel enviar as requisicoes web.

*OBTER RELATÓRIO DE VENDAS E VER INFORMACOES DOS PRODUTOS (UM ESPECÍFICO OU VÁRIOS)*
---
http://localhost:8080/products -> Mostra todos os produtos cadastrados no sistema

http://localhost:8080/products/id -> Mostra informações de um produto especifico cadastrado no sistema, _id_ deve ser substituido por um inteiro que esteja cadastrado como id de um produto
(ex: http://localhost:8080/products/1)

http://localhost:8080/sales/report -> Mostra o relatorio de vendas conforme especificado

(Acima são os metodos Get, que podem ser acessados diretamente do navegador, mas tambem podem ser acessados via Postman sem necessidade de parametros adicionais)
---


*ADICIONAR PRODUTO*
-----------------------------------------
Para adicionar um produto ao estoque, caso nao esteja disponivel o Postman, eh possivel fazer via terminal usando a seguinte linha de comando:

curl -X POST http://localhost:8080/products \
     -H "Content-Type: application/json" \
     -d '{
           "id": X,
           "nome": "Y",
           "price": Z,
           "quantity": W
         }'

Acima, devem ser substituidas:

X -> Um numero inteiro

"Y" -> O nome do produto, entre aspas

Z -> um preço, que pode estar no formato U.V, onde U e V sao numeros

W -> Um numero inteiro

Caso seja optado por usar o postman, a requisicao "post" deve conter o seguinte body Json:

 {"id": X,
           "nome": "Y",
           "price": Z,
           "quantity": W
 }

 Onde as variaveis devem ser substituidas conforme especificado acima.
---
*ATUALIZAR ESTOQUE*
---
Para atualizar a quantidade em estoque de um produto, é possível utilizar o terminal, com a seguinte linha:

curl -X PUT http://localhost:8080/products/X/stock -H "Content-Type: application/json" -d '{"quantity": Y}'

No comando acima, X deve ser substituído pelo ID do produto cujo estoque será atualizado.

Y eh nova quantidade que o produto deve ter no sistema.

Ambos devem ser números inteiros.

Se for usar Postman, enviamos um PUT http://localhost:8080/products/X/stock, onde X eh o ID do produto.

Preenchendo a parte "Body" no formato "JSON" com o seguinte conteúdo:

{ "quantity": Y }

onde Y eh um int, a nova quantidade de estoque do produto.
---
*REALIZAR COMPRA*
---
Por fim, temos a funcionalidade de realizar uma _compra_!

Para isso, o endpoint eh http://localhost:8080/purchase

Via terminal:

curl -X POST http://localhost:8080/purchase -H "Content-Type: application/json" -d '{"id": X, "quantity": Y}'

X eh o ID do produto e Y eh a quantidade desse produto que se deseja comprar.

Ambos devem ser inteiros!

Via Postman:

Enviar requisicao tipo "POST" com o seguinte "Body":

{ "id": X, "quantity": Y }

Substituindo X e Y conforme especificado acima
---
*Gerador de Requisicoes*
---
Para testar o programa conforme especificado pelos professores, vamos ate o arquivo
_lojaonline\src\test\java\com\lojaonline\lojaonline\LojaonlineApplicationTests.java_

Basta rodar o main.
O programa começa a imprimir na tela o resultado das operações.

Vale ressaltar que com o gerador de requisicoes, sao criados mais 10 itens alem dos que ja estao no estoque original
(que eh criado no arquivo _lojaonline\src\main\java\com\lojaonline\lojaonline\util\InventoryMaker.java_)

O gerador permite verificar todas as funcionalidades do programa e simular casos de acesso concorrente.

Nisso, eh possivel verificar que o controle de concorrencia funciona perfeitamente dada a implementacao do ReentrantLock.

Eh possivel verificar que podemos fazer diversas leituras simultaneas, mas nao eh possivel fazer escrita enquanto outra operacao (seja de leitura, seja de escrita) ocorre

*Codigo base criado no _Spring Initializr_*

Link do repositorio: https://github.com/viniics/lojaonline