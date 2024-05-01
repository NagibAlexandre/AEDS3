# Trabalho Prático 2 de AEDS3

- Para o TP2 implementamos o código da lista invertida (visto em sala) e o alteramos para incluir os termos dos títulos dos livros
- Integrantes:  João Vítor de Freitas Scarlatelli, Luisa Clara de Paula Lara Silva, Nagib Alexandre Verly Borjaili, Yasmin Cassemiro Viegas

# Métodos e Classes Criadas

**Menu na Main:** Menu na main que opera pelo switch case no console dando as opções de: inserir novo registro, buscar uma chave, deletar um registro e atualizar um registro para o usuário. 

**Classe ArquivoLivro:** O ArquivoLivro tem a função de reunir as operações de manipulação do objeto livro, nele existem operações de crud principais de inclusão, exclusão, pesquisa e alteração. A parte principal do arquivo livro é que, ele se utilizando também das funções  já estabelecidas em ListaInvertida.java, permite com que seja possível a manipulação de uma sequência de palavras ao invés de somente 1 por operação.

**Método Insert na classe ArquivoLivro:** O método Insert de forma concisa, obtém as palavras/chaves informadas pelo usuário juntamente com o dado, trata a estrutura dessas palavras, colocando em minúsculo e retirando acentos, em seguida divide essas chaves em elementos de um array de String por meio do método split() sendo o fator para separação o espaço em branco. Em seguida para cada palavra do array, ele adiciona ela no seu campo específico e adiciona a chave informada.

**Método Alter na classe ArquivoLivro:** O método Alter pode ser descrito como uma junção de Insert e Delete, a priori ele remove o dado da(s) chave(s) requisitadas e insere o(s) novo(s).

**Método Delete na classe ArquivoLivro:** O método Delete de forma concisa, obtém as palavras/chaves informadas pelo usuário juntamente com o dado, trata a estrutura dessas palavras, colocando em minúsculo e retirando acentos, em seguida divide essas chaves em elementos de um array de String por meio do método split() sendo o fator para separação o espaço em branco. Em seguida para cada palavra do array, ele remove o dado de cada uma das palavras/chaves que foram requisitadas.

**Método Search na classe ArquivoLivro:** O método Search para cada chave inserida realiza o processo de encontrar os dados de intercessão resultantes, ele consegue fazer isso pois se beneficia das propriedades das HashSet’s de não repetição de elementos e do método retainall() que já retorna uma sequência de valores da intercessão.

**Método getStopwords na classe ArquivoLivros:** Encontramos um .txt no github que tem diversas stopwords em portugês (https://gist.github.com/alopes/5358189), e no método getStopwords pegamos todo esse .txt e os transformamos em um arraylist global


# Perguntas

**-   A inclusão de um livro acrescenta os termos do seu título à lista invertida?**
Sim, quando incluímos um livro se cria um índice novo na lista invertida para cada termo do título do livro. 

**-   A alteração de um livro modifica a lista invertida removendo ou acrescentando termos do título?**
Sim, em nossa função Alter remove os índices do título antigo e adiciona os novos dados do livro alterado, independentemente se o livro foi alterado ou não.

**-   A remoção de um livro gera a remoção dos termos do seu título na lista invertida?**
Sim, quando se remove um livro, é também removido todos os termos do título salvos na lista invertida.

**-   Há uma busca por palavras que retorna os livros que possuam essas palavras?**
Sim, no método Search podemos fazer uma busca com a String digitada (se for mais de uma palavra as separamos utilizando o split), depois pesquisamos a palavra na lista invertida e assim retornamos um conjunto com os livros que atendem a/as palavra(s) digitada(s).

**-   Essa busca pode ser feita com mais de uma palavra?**
Sim, o usuário pode buscar livros com mais de uma palavra. O método Search tem como o objetivo encontrar os livros que são comuns aos títulos pesquisados, podemos traduzir isso como encontrar a intersecção de conjuntos de id's de livros. O método busca cria um HashSet chamado intercessão, e, para cada palavra que está sendo pesquisada, em seguida converte o array de int obtido com a pesquisa em um HashSet e armazena nele os valores. Ao final de cada palavra da busca, o programa confere se o loop está na primeira palavra através da linha (' if (intersecao == null) '). Se for a primeira vez armazena os dígitos obtidos no HashSet intercessão. Caso não seja a primeira palavra inclui os id's obtidos no HashSet intercessão através do método retainall() que filtra apenas os dígitos em comum. O loop se repete enquanto houverem palavras para serem pesquisadas e ao final retorna a intercessão.

**-   As _stop words_ foram removidas de todo o processo?**
Sim, encontramos um .txt no github que tem diversas stopwords em portugês (https://gist.github.com/alopes/5358189), e para não contar as stopwors, temos a função getStopWords que lê o stopwords.txt armazenar as palavras em um arraylist global e depois nas funções Insert e Exclude vemos se elas são uma das palavras digitadas e as pulamos.

**-   Que modificação, se alguma, você fez para além dos requisitos mínimos desta tarefa?**
Não fizemos grandes modificações além dos requisitos pedidos na tarefa.

**-   O trabalho está funcionando corretamente?**
Sim, todo o trabalho foi testado e ele está funcionando corretamente.

**-   O trabalho está completo?**
Sim, todos os requisitos solicitados pela tarefa foram atendidos.

**-   O trabalho é original e não a cópia de um trabalho de um colega?**
Sim, todo o TP2 foi feito por nós, com base nos códigos fornecidos nas aulas.
