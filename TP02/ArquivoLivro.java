/*********
 * ARQUIVO LIVRO
 * 
 * Os nomes dos métodos foram mantidos em inglês
 * apenas para manter a coerência com o resto da
 * disciplina:
 * 
 * 
 * - ArrayList<String> getStopWords()
 * - String formatWord(String oldWord)
 * - void Insert(String chave, int dado)
 * - void Alter(String chaveA, int dadoA, String chaveB, int dadoB)
 * - void Exclude(String chave, int dado)
 * - void Search(String chave)
 *
 * Implementado pelos integrantes (João Vítor, Luisa Silva, Nagib Alexandre, Yasmin Cassemiro)
 * 
 * Com base nós códigos fornecidos pelo Prof. Marcos Kutova
 * 
 * 2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import aed3.ListaInvertida;

public class ArquivoLivro {

    // inicialização da lista invertida e da lista de stop words
    ListaInvertida lista;
    ArrayList<String> StopWords = new ArrayList<>();

    public ArquivoLivro() throws Exception {
        lista = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");
        StopWords = getStopWords();
    }

    // pegar stopwords via arquivo .txt e transformar em um arraylist global
    ArrayList<String> getStopWords() throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader("stopwords.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] StopWordsArray = linha.split("\n");

                for (String word : StopWordsArray) {
                    StopWords.add(word);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return StopWords;
    }

    public static String formatWord(String oldWord) {
        
        // Como o scanner tem uma limitação a não ler a entrada do terminal no formato UTF-8
        // arranjamos cada uma das letras acentuadas em Set's e por meio da função .contains que tem complexidade O(1) trocamos as letras com acentos para as normais

        Set<Integer> Intervalo_A = new HashSet<>(Arrays.asList(160, 198, 402, 8222, 8224, 8230, 181, 182, 183, 199, 381));
        Set<Integer> Intervalo_E = new HashSet<>(Arrays.asList(352, 710, 8218, 8240, 210, 211, 212));
        Set<Integer> Intervalo_I = new HashSet<>(Arrays.asList(161, 338, 8249, 214, 215, 216, 222, 65533));
        Set<Integer> Intervalo_O = new HashSet<>(Arrays.asList(162, 228, 8220, 8221, 8226, 224, 226, 227, 229, 8482));
        Set<Integer> Intervalo_U = new HashSet<>(Arrays.asList(163, 8211, 8212, 65533, 233, 234, 235, 353));
        
        StringBuilder newWord = new StringBuilder();
        for (int i = 0; i < oldWord.length(); i++) {
            int uni_pos = oldWord.charAt(i);
            if (Intervalo_A.contains(uni_pos)) {
                newWord.append('a');
                continue;
            }
            if (Intervalo_E.contains(uni_pos)) {
                newWord.append('e');
                continue;
            }
            if (Intervalo_I.contains(uni_pos)) {
                newWord.append('i');
                continue;
            }
            if (Intervalo_O.contains(uni_pos)) {
                newWord.append('o');
                continue;
            }
            if (Intervalo_U.contains(uni_pos)) {
                newWord.append('u');
                continue;
            }
            if (uni_pos == 164 || uni_pos == 165) {
                newWord.append("n");
                continue;
            }
            if (uni_pos == 8225) {
                newWord.append('c');
                continue;
            }
            newWord.append(oldWord.charAt(i));
        }
        
        // Remove caracteres especiais e converte para minúsculas
        String formatted_word = Normalizer.normalize(newWord.toString(), Normalizer.Form.NFD)
        .replaceAll("[^a-zA-Z\\s]", "")
                .toLowerCase();

        // System.out.println(formatted_word);
        return formatted_word;
    }

    // inserir
    public void Insert(String chave, int dado) {
        // separa cada palavra inserida em um array de strings e para cada palavra
        // insere a mesma no db, caso não exista é criado um campo
        try {
            String newChave = formatWord(chave);

            String[] arrayWords = newChave.split(" ");

            for (String word : arrayWords) {
                if (!StopWords.contains(word)) {
                    lista.create(word, dado);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // alterar

    public void Alter(String chaveA, int dadoA, String chaveB, int dadoB) {
        // remove a chave solicitada e adiciona a nova versão
        try {
            Exclude(chaveA, dadoA);
            Insert(chaveB, dadoB);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // excluir

    public void Exclude(String chave, int dado) {
        // separa cada palavra inserida em um array de strings e para cada palavra
        // retira a mesma no db
        try {
            String newChave = formatWord(chave);

            String[] arrayWords = newChave.split(" ");

            for (String word : arrayWords) {
                if (!StopWords.contains(word)) {
                    lista.delete(word, dado);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // procurar
    public void Search(String chave) {
        // considerando a propriedade de conjuntos interseção, para cada palavra separa
        // seus dados e realiza a intercessão com as palavras pesquisadas
        // por fim retorna apenas os dados frequentes em todas
        String newChave = formatWord(chave);
        String[] arrayWords = newChave.split(" ");

        Set<Integer> intersecao = null; // Interseção dos conjuntos

        for (String word : arrayWords) {
            try {
                int[] tmp_indice = lista.read(word);
                // Converte o array de int para um conjunto de int
                Set<Integer> conjuntoAtual = new HashSet<>();
                for (int i : tmp_indice) {
                    conjuntoAtual.add(i);
                }

                if (intersecao == null) {
                    // Se for o primeiro conjunto, define a interseção como o conjunto atual
                    intersecao = new HashSet<>(conjuntoAtual);
                } else {
                    // Caso contrário, mantém apenas os elementos que estão presentes na interseção
                    // e no conjunto atual
                    intersecao.retainAll(conjuntoAtual);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (intersecao.isEmpty()) {
            System.out.println("\nOs livros que contém as palavras inseridas { "+newChave+" } não possuem chaves em comum.");
        }else{
            System.out.println("\nOs livros que contém as palavras inseridas { "+newChave+" } possuem o(s) dado(s): " + intersecao);
        }
    }

}
