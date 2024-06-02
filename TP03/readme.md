# Trabalho Prático 3 de AEDS3

- Para o TP3 implementamos o código do algoritmo LZW para a criação de um backup compactado dos arquivos de dados
- Integrantes:  João Vítor de Freitas Scarlatelli, Luisa Clara de Paula Lara Silva, Nagib Alexandre Verly Borjaili, Yasmin Cassemiro Viegas

# Métodos e Classes Criadas

**Menu na Main:** Menu na main que opera pelo switch case no console dando as opções de: Fazer backup e codificar, Decodificar e sair. Se a pessoa escolher a opção de Fazer backup e codificar aparece um segundo menu que dá as opções de fazer backup e codificar todos os arquivos ou somente um deles, já se a pessoa escolher fazer a Decodificação aparece um menu que as opções são de decodificar todos os arquivos ou apenas um deles.

**Classe BackupCompactado:** O código possui rotinas de backup diferentes dependendo da escolha do usuário. É possível codificar todos os arquivos na pasta "dados" ou somente um deles. É possível descompactar todas as versões de um momento de um arquivo, (Ex:. descompactar todos os backups do dia 01/06/2024 do arquivo "Arquivo.db" ). E é possível descompactar também somente uma versão de um backup. 

**Método criarBackup na classe BackupCompactado:** Cria um backup de todos os arquivos de um diretório especificado, organizando-os em pastas baseadas na data e hora atual, e comprime os dados usando o algoritmo LZW antes de salvá-los. Ele também gera a estrutura de diretórios necessária e retorna o caminho do diretório de backup criado.

**Método criarBackupEspecifico na classe BackupCompactado:** Cria um backup de um arquivo específico de um diretório, organizando-o em uma pasta baseada na data e hora atual, e comprime os dados usando o algoritmo LZW antes de salvá-los com a extensão ".lzw". Ele verifica a existência do arquivo, cria a estrutura de diretórios necessária e retorna o caminho do diretório de backup criado.

**Método listFilesInDirectory na classe BackupCompactado:** Lista todos os arquivos regulares em um diretório especificado, adicionando seus caminhos completos a uma lista de strings. Ele utiliza a classe Files para iterar sobre os itens do diretório e filtra apenas os arquivos regulares.

**Método listarPastasDeBackup na classe BackupCompactado:** Lista todas as pastas dentro do diretório "backup", adicionando seus nomes a uma lista e exibindo-os com um índice. Ele retorna essa lista de nomes de pastas como um array de strings.

**Método listarArquivos na classe BackupCompactado:** Lista todos os arquivos regulares em um diretório especificado, adicionando seus nomes de arquivo a uma lista. Ele retorna essa lista de nomes de arquivos como um array de strings.

**Método listarPastasDeArquivos na classe BackupCompactado:** Lista todas as pastas dentro de um diretório de backup específico (baseado na data escolhida), adicionando seus nomes a uma lista e exibindo-os com um índice. Ele retorna essa lista de nomes de pastas como um array de strings.

**Método listarArquivosBackup na classe BackupCompactado:** Lista todos os arquivos dentro de uma pasta de backup específica (baseada na data e na pasta escolhidas), adicionando seus nomes a uma lista e exibindo-os com um índice. Ele retorna essa lista de nomes de arquivos como um array de strings.

**Método recuperarBackups na classe BackupCompactado:** Recupera backups de arquivos de uma pasta de backup específica, restaurando os arquivos compactados usando o algoritmo LZW e salvando os arquivos recuperados em uma pasta especificada. Ele também lida com a recuperação de todos os arquivos ou de um arquivo específico, com base no nome do arquivo fornecido.

**Método recuperarBackupEspecifico na classe BackupCompactado:** Recupera um backup específico de um arquivo de uma pasta de backup, decodifica os dados compactados usando o algoritmo LZW e salva o arquivo recuperado em uma pasta de destino. Ele lida com o arquivo especificado, cria a estrutura de diretórios necessária e exibe uma mensagem indicando o sucesso da recuperação.

**Classe BitSequence:** Armazena uma sequência de números em um vetor de bits. Cada número tem "bitsPorNumero" bits

**Classe LZW:** Implementa o algoritmo LZW para codificação e decodificação de dados. Ele comprime dados em bytes usando um dicionário e retorna um array de bytes comprimidos. Ele também descomprime dados codificados anteriormente, restaurando os dados originais a partir do array de bytes codificados.

# Perguntas

**-   Há uma rotina de compactação usando o algoritmo LZW para fazer backup dos arquivos?**
Sim. Isso é feito nas funções criarBackup e criarBackupEspecifico. Ambas utilizam o método LZW.codifica(byte[] input) para compactar os dados dos arquivos antes de salvá-los na pasta de backup. A única diferença entre essas duas funções seria só a finalidade, enquanto a primeira faz backup de tudo possível na pasta dados, a segunda faz somente de um arquivo especifico desejado.

**-   Há uma rotina de descompactação usando o algoritmo LZW para recuperação dos arquivos?**
Sim. Isso é feito nas funções criarBackup e criarBackupEspecifico. Ambas utilizam o método LZW.codifica(byte[] input) para compactar os dados dos arquivos antes de salvá-los na pasta de backup. A única diferença entre essas duas funções seria só a finalidade, enquanto a primeira faz backup de tudo possível na pasta dados, a segunda faz somente de um arquivo especifico desejado.

**-   O usuário pode escolher a versão a recuperar?**
Sim. Durante o processo de decodificação (recuperação), o usuário é solicitado a escolher a data do backup e, se desejado, um arquivo específico dentre as versões disponíveis para aquela data.

**-   Qual foi a taxa de compressão alcançada por esse backup? (Compare o tamanho dos arquivos compactados com os arquivos originais)** 
Utilizando o filmes.db:

Tamanho original  : 405kb

Tamanho compactado: 379kb

Porcentagem de compressão = 6.42%

**-   O trabalho está funcionando corretamente?**
Sim, todo o trabalho foi testado e ele está funcionando corretamente.

**-   O trabalho está completo?**
Sim, todos os requisitos solicitados pela tarefa foram atendidos.

**-   O trabalho é original e não a cópia de um trabalho de um colega?**
Sim, todo o TP3 foi feito por nós, com base nos códigos e teorias fornecidos nas aulas.
