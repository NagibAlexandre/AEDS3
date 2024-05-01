// arrumar a leitura de caracteres com ascento
// arrumar a mensagem de erro caso não tenha o que ser manipulado
// comentar os códigos
// fazer o readme

import java.util.Scanner;
import java.io.File;
import aed3.ListaInvertida;

public class Main {

  // Método principal apenas para testes
  public static void main(String[] args) {

    ListaInvertida lista;
    Scanner console = new Scanner(System.in);

    try {

      ArquivoLivro arqLivro = new ArquivoLivro();

      File d = new File("dados");
      if (!d.exists())
        d.mkdir();
      lista = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");

      int opcao;
      do {
        System.out.println("\n\n+-----------------------------+");
        System.out.println("|             MENU            |");
        System.out.println("+-----------------------------+");
        System.out.println("| 1 - Inserir                 |");
        System.out.println("| 2 - Buscar                  |");
        System.out.println("| 3 - Excluir                 |");
        System.out.println("| 4 - Exibir                  |");
        System.out.println("| 5 - Atualizar               |");
        System.out.println("+-----------------------------+");
        System.out.println("| 0 - Sair                    |");
        System.out.println("+-----------------------------+");
        try {
          opcao = Integer.valueOf(console.nextLine());
        } catch (NumberFormatException e) {
          opcao = -1;
        }

        switch (opcao) {
          case 1: {
            System.out.println("\nINCLUSÃO");
            System.out.print("Chave: ");
            String chave = console.nextLine();
            System.out.print("Dado: ");
            int dado = Integer.valueOf(console.nextLine());

            arqLivro.Insert(chave, dado);
            lista.print();

          }
            break;
          case 2: {
            System.out.println("\nBUSCA");
            System.out.print("Chave: ");
            String chave = console.nextLine();

            arqLivro.Search(chave);
            lista.print();
          }
            break;
          case 3: {
            System.out.println("\nEXCLUSÃO");
            System.out.print("Chave: ");
            String chave = console.nextLine();
            System.out.print("Dado: ");
            int dado = Integer.valueOf(console.nextLine());

            arqLivro.Exclude(chave, dado);
            lista.print();
          }
            break;
          case 4: {
            lista.print();
          }
            break;
          case 5: {
            System.out.println("\nALTERAÇÃO");
            System.out.print("Chave que deseja alterar: ");
            String chaveA = console.nextLine();
            System.out.print("Dado que deseja alterar: ");
            int dadoA = Integer.valueOf(console.nextLine());

            System.out.print("\nChave nova: ");
            String chaveB = console.nextLine();
            System.out.print("Dado novo: ");
            int dadoB = Integer.valueOf(console.nextLine());

            arqLivro.Alter(chaveA, dadoA, chaveB, dadoB);
            lista.print();
          }
          case 0:
            break;
          default:
            System.out.println("Opção inválida");
        }
      } while (opcao != 0);

    } catch (Exception e) {
      e.printStackTrace();
    }
    console.close();
  }
}