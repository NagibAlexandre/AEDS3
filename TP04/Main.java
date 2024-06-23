import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Entrada de dados para o Livro
            System.out.println("------Cadastro do Livro------");
            System.out.print("Digite o ID do Livro: ");
            int idLivro = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Digite o ISBN do Livro: ");
            String isbnLivro = scanner.nextLine();
            System.out.print("Digite o Título do Livro: ");
            String tituloLivro = scanner.nextLine();
            System.out.print("Digite o Preço do Livro: ");
            float precoLivro = scanner.nextFloat();

            Livro livroOriginal = new Livro(idLivro, isbnLivro, tituloLivro, precoLivro);

            // Converte o livro para um array de bytes, assim os cifrando
            byte[] dadosCifradosLivro = livroOriginal.toByteArray();

            // Cria um novo livro e lê os dados do array de bytes, assim os decifrando
            Livro livroDecifrado = new Livro(0, "", "", 0);
            livroDecifrado.fromByteArray(dadosCifradosLivro);

            // Compara os dados do livroOriginal e do livroDecifrado
            boolean idLivroIgual = livroOriginal.getID() == livroDecifrado.getID();
            boolean isbnLivroIgual = livroOriginal.getIsbn().equals(livroDecifrado.getIsbn());
            boolean tituloLivroIgual = livroOriginal.getTitulo().equals(livroDecifrado.getTitulo());
            boolean precoLivroIgual = livroOriginal.getPreco() == livroDecifrado.getPreco();

            System.out.println("------Cifragem do livro------");
            System.out.println("Livro original: " + livroOriginal.toString());
            System.out.println("Livro decifrado: " + livroDecifrado.toString());

            // se tudo for igual o teste passou, e o livro foi cifrado e decifrado
            // corretamente
            if (idLivroIgual && isbnLivroIgual && tituloLivroIgual && precoLivroIgual) {
                System.out.println("Teste Livro passou!");
            } else {
                System.out.println("Teste Livro falhou!");
            }

            // Entrada de dados para a Categoria
            System.out.println("\n\n------Cadastro da Categoria------");
            System.out.print("Digite o ID da Categoria: ");
            int idCategoria = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            System.out.print("Digite o Nome da Categoria: ");
            String nomeCategoria = scanner.nextLine();
            System.out.print("Digite a Descrição da Categoria: ");
            String descricaoCategoria = scanner.nextLine();

            Categoria categoriaOriginal = new Categoria(idCategoria, nomeCategoria, descricaoCategoria);

            // Converte a categoria para um array de bytes
            byte[] dadosCifradosCategoria = categoriaOriginal.toByteArray();

            // Cria um novo perfil e lê os dados do array de bytes
            Categoria categoriaDecifrada = new Categoria();
            categoriaDecifrada.fromByteArray(dadosCifradosCategoria);

            // Compara os dados da categoria original e da categoria decifrada
            boolean idCategoriaIgual = categoriaOriginal.getID() == categoriaDecifrada.getID();
            boolean nomeCategoriaIgual = categoriaOriginal.getNome().equals(categoriaDecifrada.getNome());
            boolean descricaoCategoriaIgual = categoriaOriginal.getDescricao()
                    .equals(categoriaDecifrada.getDescricao());

            System.out.println("\n\n------Cifragem da categoria------");
            System.out.println("Categoria original: " + categoriaOriginal.toString());
            System.out.println("Categoria decifrada: " + categoriaDecifrada.toString());

            // se tudo for igual o teste passou, e a categoria foi cifrada e decifrada
            // corretamente
            if (idCategoriaIgual && nomeCategoriaIgual && descricaoCategoriaIgual) {
                System.out.println("Teste Categoria passou!");
            } else {
                System.out.println("Teste Categoria falhou!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
