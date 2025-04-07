# lojaonline
Loja Online

Desenvolvido por: 
- Vinícius Soares Nóbrega (122110262)
- Bruno Machado de Assis (1191104401)

Repositório criado para a solução do "Lab10" da disciplina de Programação Concorrente!

Para rodar o programa, basta executar o arquivo "_lojaonline\src\test\java\com\lojaonline\lojaonline\LojaonlineApplicationTests.java_"

Ao fazer isso, o servidor sera criado e sera possivel enviar as requisicoes web.

*OBTER RELATÓRIO DE VENDAS E VER INFORMACOES DOS PRODUTOS (UM ESPECÍFICO OU VÁRIOS)*
---
http://localhost:8080/products -> Mostra todos os produtos cadastrados no sistema

http://localhost:8080/products/id -> Mostra informações de um produto especifico cadastrado no sistema, _id_ deve ser substituido por um inteiro que esteja cadastrado como id de um produto (ex: http://localhost:8080/products/1)

http://localhost:8080/sales/report -> Mostra o relatorio de vendas conforme especificado

(Acima são os metodos Get, que podem ser acessados diretamente do navegador, mas tambem podem ser acessados via Postman sem necessidade de parametros adicionais)
---


*ADICIONAR PRODUTO*
Para adicionar um produto ao estoque, caso nao esteja disponivel o Postman, eh possivel fazer via terminal usando a seguinte linha de comando:
-----------------------------------------
curl -X POST http://localhost:8080/products \
     -H "Content-Type: application/json" \
     -d '{
           "id": X,
           "nome": "Y",
           "price": Z,
           "quantity": W
         }'
-----------------------------------------
Acima, devem ser substituidas:
X -> Um numero inteiro
"Y" -> O nome do produto, entre aspas
Z -> um preço, que pode estar no formato U.V, onde U e V sao numeros
W -> Um numero inteiro

Caso seja optado por usar o postman, a requisicao put deve conter o seguinte body Json:
 {"id": X,
           "nome": "Y",
           "price": Z,
           "quantity": W
 }
 Onde as variaveis devem ser substituidas conforme especificado acima.


Para checar 