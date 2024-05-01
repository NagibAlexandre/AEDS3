import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
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
        // Remove caracteres especiais e converte para minúsculas
        String formatted_word = Normalizer.normalize(oldWord, Normalizer.Form.NFD)
                .replaceAll("[^a-zA-Z\\s]", "")
                .toLowerCase();

        // arrumar para ler e corrigir acentos

        System.out.println(formatted_word);
        return formatted_word;
    }

    // inserir
    public void Insert(String chave, int dado) {
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
        try {
            Exclude(chaveA, dadoA);
            Insert(chaveB, dadoB);


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // excluir

    public void Exclude(String chave, int dado) {
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

        System.out.println("Interseção: " + intersecao);
    }
}
