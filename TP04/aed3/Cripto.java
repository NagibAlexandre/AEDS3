package aed3;

public class Cripto {

    // Calcula o deslocamento para a Cifra de César com base na chave fornecida
    private static int extractShift(String chave) {
        return chave.chars().sum() % 256;
    }

    // Calcula o número de trilhos para a Cifra Rail Fence com base na chave
    // fornecida
    private static int extractNumRails(String chave) {
        return (chave.chars().sum() % 9) + 2; // To ensure numRails is between 2 and 10
    }

    // SUBSTITUIÇÃO - CIFRA DE CÉSAR

    // Codifica os dados usando a Cifra de César com o deslocamento especificado
    private static byte[] caesarCod(byte[] dado, int shift) {
        for (int i = 0; i < dado.length; i++) {
            dado[i] = (byte) ((dado[i] + shift) % 256);
        }
        return dado;
    }

    // Decodifica os dados usando a Cifra de César com o deslocamento especificado
    private static byte[] caesarDecod(byte[] dado, int shift) {
        for (int i = 0; i < dado.length; i++) {
            dado[i] = (byte) ((dado[i] - shift + 256) % 256);
        }
        return dado;
    }

    // TRANSPOSIÇÃO - CIFRA RAIL FENCE

    // Codifica os dados usando a Cifra Rail Fence com o número de trilhos
    // especificado
    private static byte[] railFenceCod(byte[] dado, int numRails) {
        if (numRails == 1)
            return dado;

        byte[][] rail = new byte[numRails][dado.length];
        int[] railIndex = new int[numRails];

        boolean down = true;
        int row = 0;

        // Distribui os caracteres nos trilhos
        for (int i = 0; i < dado.length; i++) {
            rail[row][railIndex[row]++] = dado[i];

            if (row == 0) {
                down = true;
            } else if (row == numRails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        // Lê os caracteres dos trilhos em ordem
        byte[] result = new byte[dado.length];
        int index = 0;

        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < railIndex[i]; j++) {
                result[index++] = rail[i][j];
            }
        }

        return result;
    }

    // Decodifica os dados usando a Cifra Rail Fence com o número de trilhos
    // especificado
    private static byte[] railFenceDecod(byte[] dado, int numRails) {
        if (numRails == 1)
            return dado;

        byte[][] rail = new byte[numRails][dado.length];
        int[] railIndex = new int[numRails];
        int[] railFillIndex = new int[numRails];

        boolean down = true;
        int row = 0;

        // Calcula quantos caracteres vão para cada trilho
        for (int i = 0; i < dado.length; i++) {
            railIndex[row]++;
            if (row == 0) {
                down = true;
            } else if (row == numRails - 1) {
                down = false;
            }
            row += down ? 1 : -1;
        }

        // Preenche os trilhos com os caracteres
        int index = 0;
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < railIndex[i]; j++) {
                rail[i][j] = dado[index++];
            }
        }

        // Lê os caracteres dos trilhos em ordem para decodificação
        byte[] result = new byte[dado.length];
        index = 0;
        row = 0;
        down = true;

        for (int i = 0; i < dado.length; i++) {
            result[i] = rail[row][railFillIndex[row]++];

            if (row == 0) {
                down = true;
            } else if (row == numRails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        return result;
    }

    // Imprime os dados na forma de bytes
    private static void printB(byte[] dado) {
        System.out.print("[ ");
        for (byte b : dado) {
            System.out.print(b + " ");
        }
        System.out.println("]\n");
    }

    // Codifica os dados usando uma combinação das cifras de César e Rail Fence com
    // base na chave fornecida
    public static byte[] cod(byte[] dado, String chave) {
        int shift = extractShift(chave);
        int numRails = extractNumRails(chave);
        System.out.println("\nCodificando os dados\n");
        printB(dado);
        System.out.println(" -> \n");
        dado = caesarCod(dado, shift);
        dado = railFenceCod(dado, numRails);
        printB(dado);
        return dado;
    }

    // Decodifica os dados usando uma combinação das cifras de César e Rail Fence
    // com base na chave fornecida
    public static byte[] decod(byte[] dado, String chave) {
        int shift = extractShift(chave);
        int numRails = extractNumRails(chave);
        System.out.println("\n Decodificando os dados\n");
        printB(dado);
        System.out.println(" -> \n");
        dado = railFenceDecod(dado, numRails);
        dado = caesarDecod(dado, shift);
        printB(dado);
        return dado;
    }

}