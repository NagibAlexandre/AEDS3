package aed3;

public class Cripto {

    private static int extractShift(String chave) {
        return chave.chars().sum() % 256;
    }

    private static int extractNumRails(String chave) {
        return (chave.chars().sum() % 9) + 2; // To ensure numRails is between 2 and 10
    }

    // SUBSTITUICAO CAESAR CIPHER

    private static byte[] caesarCod(byte[] dado, int shift) {
        for (int i = 0; i < dado.length; i++) {
            dado[i] = (byte) ((dado[i] + shift) % 256);
        }
        return dado;
    }

    private static byte[] caesarDecod(byte[] dado, int shift) {
        for (int i = 0; i < dado.length; i++) {
            dado[i] = (byte) ((dado[i] - shift + 256) % 256);
        }
        return dado;
    }

    // TRANSPOSITION RAIL FENCE CIPHER

    private static byte[] railFenceCod(byte[] dado, int numRails) {
        if (numRails == 1)
            return dado;

        byte[][] rail = new byte[numRails][dado.length];
        int[] railIndex = new int[numRails];

        boolean down = true;
        int row = 0;

        for (int i = 0; i < dado.length; i++) {
            rail[row][railIndex[row]++] = dado[i];

            if (row == 0) {
                down = true;
            } else if (row == numRails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        byte[] result = new byte[dado.length];
        int index = 0;

        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < railIndex[i]; j++) {
                result[index++] = rail[i][j];
            }
        }

        return result;
    }

    private static byte[] railFenceDecod(byte[] dado, int numRails) {
        if (numRails == 1)
            return dado;

        byte[][] rail = new byte[numRails][dado.length];
        int[] railIndex = new int[numRails];
        int[] railFillIndex = new int[numRails];

        boolean down = true;
        int row = 0;

        for (int i = 0; i < dado.length; i++) {
            railIndex[row]++;
            if (row == 0) {
                down = true;
            } else if (row == numRails - 1) {
                down = false;
            }
            row += down ? 1 : -1;
        }

        int index = 0;
        for (int i = 0; i < numRails; i++) {
            for (int j = 0; j < railIndex[i]; j++) {
                rail[i][j] = dado[index++];
            }
        }

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

    private static void printB(byte[] dado) {
        System.out.print("[ ");
        for (byte b : dado) {
            System.out.print(b + " ");
        }
        System.out.println("]\n");
    }

    public static byte[] cod(byte[] dado, String chave) {
        int shift = extractShift(chave);
        int numRails = extractNumRails(chave);
        System.out.println("Codificando dados\n");
        printB(dado);
        System.out.println(" -> \n");
        dado = caesarCod(dado, shift);
        dado = railFenceCod(dado, numRails);
        printB(dado);
        return dado;
    }

    public static byte[] decod(byte[] dado, String chave) {
        int shift = extractShift(chave);
        int numRails = extractNumRails(chave);
        System.out.println("Decodificando dados\n");
        printB(dado);
        System.out.println(" -> \n");
        dado = railFenceDecod(dado, numRails);
        dado = caesarDecod(dado, shift);
        printB(dado);
        return dado;
    }

}