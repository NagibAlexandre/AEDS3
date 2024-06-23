# Trabalho Prático 4 de AEDS3

- Para o TP4 implementamos o a classe Cripto que implementa um sistema de criptografia e descriptografia usando duas técnicas combinadas: a Cifra de César e a Cifra Rail Fence.
- Integrantes:  João Vítor de Freitas Scarlatelli, Luisa Clara de Paula Lara Silva, Nagib Alexandre Verly Borjaili, Yasmin Cassemiro Viegas

# Métodos e Classes Criadas

**Classe Main:** demonstra a criação, cifragem (serialização) e decifragem (desserialização) de objetos das classes Livro e Categoria. Ela solicita dados do usuário para criar objetos, converte esses objetos em arrays de bytes, e depois reconstrói os objetos a partir desses bytes para verificar a integridade dos dados. Se os dados originais e decifrados forem iguais, o teste é considerado bem-sucedido.

**Classe Cripto:** A classe Cripto é responsável por cifrar e decifrar dados. Ela possui os métodos cod, decod, printB, extractShift, extractNumRails, caesarCod, caesarDecod, railFenceCod e railFenceDecod.

**Classe Livro:** Representa a entidade Livro que possui os atributos: id, isbn, títilo e preço.

**Classe Categoria:** Representa a entidade categoria que possui os atributos: id, nome e descrição.

**Método toByteArray nas classes Livroe e Categoria :** O método toByteArray converte os atributos do livro em um array de bytes e os cifra.

**Método fromByteArray nas classes Livroe e Categoria :** O método fromByteArray decifra um array de bytes e reconstrói o objeto Livro a partir desses dados.

**Método cod na classe Cripto:** Este método codifica os dados usando uma combinação das cifras de César e Rail Fence, conforme especificado pela chave fornecida. Primeiro, extrai o deslocamento e o número de trilhos da chave. Em seguida, aplica a cifra de César para realizar um deslocamento nos bytes dos dados e depois a cifra Rail Fence para reorganizar os dados em trilhos. O método printB é usado para exibir os dados antes e depois da codificação.

**Método decod na classe Cripto:** Este método decodifica os dados que foram previamente codificados usando a mesma combinação de cifras de César e Rail Fence, conforme a chave fornecida. Primeiro, extrai o deslocamento e o número de trilhos da chave. Inverte o processo de codificação, decodificando primeiro usando Rail Fence e depois usando César. O método printB é usado para exibir os dados antes e depois da decodificação.

**Método printB na classe Cripto:** Este método imprime os dados na forma de bytes, exibindo cada byte do array dado em formato legível, entre colchetes e separados por espaços.

**Método extractShift na classe Cripto:** Este método calcula um deslocamento para a Cifra de César com base na soma dos valores dos caracteres da chave fornecida. O resultado é obtido aplicando o módulo 256 ao resultado da soma, garantindo que o deslocamento esteja dentro do intervalo válido para caracteres de 8 bits.

**Método extractNumRails na classe Cripto:** Calcula o número de trilhos para a Cifra Rail Fence baseado na soma dos valores dos caracteres da chave. O resultado é ajustado para garantir que o número de trilhos esteja entre 2 e 10, utilizando o módulo 9 e adicionando 2.

**Método caesarCod na classe Cripto:** Codifica os dados utilizando a Cifra de César com o deslocamento especificado. Cada byte do array de dados é deslocado para a direita pelo valor do deslocamento (shift), garantindo que os valores resultantes estejam dentro do intervalo de 0 a 255.

**Método caesarDecod na classe Cripto:** Decodifica os dados que foram codificados com a Cifra de César. Realiza o deslocamento inverso para restaurar os valores originais antes da codificação.

**Método railFenceCod na classe Cripto:** Codifica os dados utilizando a Cifra Rail Fence com o número de trilhos especificado. Os dados são distribuídos entre os trilhos de forma intercalada, seguindo um padrão de "subida" e "descida" ao longo dos trilhos.

**Método railFenceDecod na classe Cripto:** Decodifica os dados que foram codificados com a Cifra Rail Fence. Reverte o processo de codificação distribuindo os dados de volta para os trilhos na mesma sequência utilizada durante a codificação.

# Perguntas

**-  Há uma função de cifragem em todas as classes de entidades, envolvendo pelo menos duas operações diferentes e usando uma chave criptográfica?**
Sim, a classe Cripto possiu os métodos cod (cidificar) e decod (decodificar) que envolvem duas operações de cifragem diferentes e utilizam uma chave criptográfica declarada previamente no código (classe Livroe classe Categoria).

**-  Uma das operações de cifragem é baseada na substituição e a outra na transposição?** 
Sim, na classe Cripto, uma das operações de cifragem é baseada na substituição e a outra na transposição. A de substituição é a Cifra de César: Esta operação de cifragem substitui cada byte dos dados por outro byte, deslocado por um valor específico calculado a partir da chave. Cada byte é alterado de acordo com uma lógica de deslocamento, representando uma forma de substituição simples. A de transposição é a Cifra Rail Fence: Esta operação reorganiza os bytes dos dados em um padrão específico, distribuindo-os em várias "trilhas" ou linhas e, em seguida, lendo os bytes em uma nova ordem. Esse método não altera os bytes em si, mas muda sua posição, caracterizando uma técnica de transposição.

**-   O trabalho está funcionando corretamente?**
Sim, todo o trabalho foi testado e ele está funcionando corretamente.

**-   O trabalho está completo?**
Sim, todos os requisitos solicitados pela tarefa foram atendidos.

**-   O trabalho é original e não a cópia de um trabalho de um colega?**
Sim, todo o TP3 foi feito por nós, com base nos códigos e teorias fornecidos nas aulas.
