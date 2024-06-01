/*
 * O código possui rotinas de backup diferentes dependendo da escolha do usuário
 *  - É possível codificar todos os arquivos na pasta "dados" ou somente um deles.
 *  - É possível descompactar todas as versões de um momento de um arquivo, (Ex:. decompactar todos os backups do dia 01/06/2024 do arquivo "Arquivo.db" ).
 *  - É possível descompactar também somente uma versão de um backup. 
 * 
 * Código realizado na disciplina de Aeds III 
 * Pelos integrantes João Vítor, Luisa Silva, Nagib Alexandre, Yasmin Cassemiro
 *
 * BitSequence.java & LZW.java são de autoria do professor titular 2024/1 Prof. Marcos Kutova
 * Lzw.java teve poucas modificações feitas por parte do grupo
 *
 * 2024
 */

import java.nio.file.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class BackupCompactado {

    public static void main(String[] args) throws Exception {
        try {
            Scanner sc = new Scanner(System.in);

            int opc = 0;

            String directoryPath = "dados";
            while (opc != 1 && opc != 2 && opc != 3) {

                // Exibe o menu principal para o usuário
                System.out.println("+-----------------------------------+");
                System.out.println("| Escolha dentre as opções a seguir |");
                System.out.println("+-----------------------------------+");
                System.out.println("| [ 1 ] Fazer backup e codificar.   |");
                System.out.println("| [ 2 ] Decodificar.                |");
                System.out.println("| [ 3 ] Sair.                       |");
                System.out.println("+-----------------------------------+");

                // [ 1 ] Backup
                // [ 2 ] Decod
                // [ 3 ] Sair

                System.out.println("\nDigite sua opcao: ");

                opc = sc.nextInt();
            }
            if (opc == 3) {
                sc.close();
                return;
            }

            // Exibe o menu secundário para o usuário
            System.out.println(opc == 1 ? "+------------------------------+":"+---------------------------------------+");
            System.out.println(opc == 1 ? "|       Você gostaria de       |":"|            Você gostaria de           |");
            System.out.println(opc == 1 ? "|   Fazer backup e Codificar   |":"|               Decodificar             |");
            System.out.println(opc == 1 ? "+------------------------------+":"+---------------------------------------+");
            System.out.println(opc == 1 ? "| [ 1 ] Todos os arquivos.     |":"| [ 1 ] Todos os arquivos.              |");
            System.out.println(opc == 1 ? "| [ 2 ] Um arquivo específico. |":"| [ 2 ] Todas as versões de um arquivo. |");
            System.out.println(opc == 1 ? "+------------------------------+":"+---------------------------------------+");
            int opc2 = sc.nextInt();

            // [ 1 ] Todos os arq.
            // [ 2 ] { Um especifico } || { Todas as versões de um arquivo }

            if (opc == 1) { // Fazer backup e codificar
                if (opc2 == 1) {
                    // Backup de todos os arquivos
                    String backupFolder = criarBackup(directoryPath);
                    System.out.println("Backup criado no diretório -> " + backupFolder);
                } else if (opc2 == 2) {
                    // Backup de um arquivo específico
                    String[] filesArray = listarArquivos(directoryPath);
                    System.out.println("\nArquivos disponíveis para compactar: ");
                    for (int i = 0; i < filesArray.length; i++) {
                        System.out.println(" [" + i + "] " + filesArray[i]);
                    }
                    System.out.print("\nDigite o número do arquivo que deseja compactar: ");
                    int fileChoice = sc.nextInt();
                    if (fileChoice >= 0 && fileChoice < filesArray.length) {
                        String fileName = filesArray[fileChoice];
                        String backupFolder = criarBackupEspecifico(directoryPath, fileName);
                        System.out.println("Backup do arquivo " + fileName + " criado no diretório -> " + backupFolder);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }
            } else if (opc == 2) { // Decodificar
                System.out.println("\nBackups disponíveis: ");
                String[] backupDates = listarPastasDeBackup();
                System.out.print("\nDigite o número da data de backup que deseja recuperar: ");
                int escolhaData = sc.nextInt();

                String chosenDate = backupDates[escolhaData];
                String[] backupFolders = listarPastasDeArquivos(chosenDate);
                System.out.print("\nDigite o número da pasta de backup que deseja recuperar: ");
                int escolhaPasta = sc.nextInt();

                String chosenFolder = backupFolders[escolhaPasta];
                if (opc2 == 1) { // Todos os arquivos
                    recuperarBackups(chosenDate, chosenFolder, null);
                } else if (opc2 == 2) { // Um arquivo específico
                    String[] backupsArray = listarArquivosBackup(chosenDate, chosenFolder);
                    System.out.print("\nDigite o número do backup que deseja recuperar: ");
                    int escolhaBackup = sc.nextInt();

                    String chosenBackup = backupsArray[escolhaBackup];
                    recuperarBackupEspecifico(chosenDate, chosenFolder, chosenBackup);
                }
            }

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String criarBackup(String directoryPath) throws Exception { // Backup de todos os arquivos
        // Formatação das pastas para versionizar os backups
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH'h'mm'm'");
        Date now = new Date();

        String dateStr = dateFormat.format(now);
        String timeStr = timeFormat.format(now);

        // Cria o diretório de backup com base na data atual
        Path backupRootPath = Paths.get("backup", dateStr);
        Files.createDirectories(backupRootPath);

        List<String> filePaths = listFilesInDirectory(directoryPath);

        // Processa cada arquivo para criar um backup compactado
        for (String filePath : filePaths) {
            Path path = Paths.get(filePath);
            byte[] fileBytes = Files.readAllBytes(path);

            // Compacta os dados do arquivo
            byte[] compressedData = LZW.codifica(fileBytes);

            // Define o caminho do arquivo de backup e grava os dados compactados
            Path fileTypePath = backupRootPath.resolve(path.getFileName().toString());
            Files.createDirectories(fileTypePath);

            Path backupFilePath = fileTypePath.resolve(timeStr + "-" + path.getFileName() + ".lzw");
            Files.write(backupFilePath, compressedData);
        }

        return backupRootPath.toString();
    }

    public static String criarBackupEspecifico(String directoryPath, String fileName) throws Exception { // Backup de um
                                                                                                         // arquivo em
                                                                                                         // específico
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH'h'mm'm'");
        Date now = new Date();

        String dateStr = dateFormat.format(now);
        String timeStr = timeFormat.format(now);

        // Cria o diretório de backup
        Path backupRootPath = Paths.get("backup", dateStr);
        Files.createDirectories(backupRootPath);

        Path path = Paths.get(directoryPath, fileName);
        if (!Files.exists(path)) {
            System.out.println("O arquivo especificado não existe.");
            return null;
        }

        byte[] fileBytes = Files.readAllBytes(path);
        byte[] compressedData = LZW.codifica(fileBytes);

        // Define o caminho do arquivo de backup e grava os dados compactados
        Path fileTypePath = backupRootPath.resolve(path.getFileName().toString());
        Files.createDirectories(fileTypePath);

        Path backupFilePath = fileTypePath.resolve(timeStr + "-" + path.getFileName() + ".lzw");
        Files.write(backupFilePath, compressedData);

        return backupRootPath.toString();
    }

    public static List<String> listFilesInDirectory(String directoryPath) throws IOException {
        List<String> filePaths = new ArrayList<>();

        // Lista todos os arquivos do diretório
        Files.list(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .forEach(path -> filePaths.add(path.toString()));

        return filePaths;
    }

    public static String[] listarPastasDeBackup() throws IOException {
        List<String> backupList = new ArrayList<>();

        // Lista todas as pastas de backup
        Files.list(Paths.get("backup"))
                .filter(Files::isDirectory)
                .forEach(backupPath -> {
                    backupList.add(backupPath.getFileName().toString());
                });

        for (int i = 0; i < backupList.size(); i++) {
            System.out.println(" [" + i + "] " + backupList.get(i));
        }

        return backupList.toArray(new String[0]);
    }

    public static String[] listarArquivos(String directoryPath) throws IOException {
        List<String> fileList = new ArrayList<>();

        // Lista todos os arquivos no diretório especificado
        Files.list(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .forEach(filePath -> fileList.add(filePath.getFileName().toString()));

        return fileList.toArray(new String[0]);
    }

    public static String[] listarPastasDeArquivos(String chosenDate) throws IOException {
        List<String> folderList = new ArrayList<>();

        // Lista todas as pastas de arquivos dentro da pasta de backup
        Files.list(Paths.get("backup", chosenDate))
                .filter(Files::isDirectory)
                .forEach(folderPath -> {
                    folderList.add(folderPath.getFileName().toString());
                });

        for (int i = 0; i < folderList.size(); i++) {
            System.out.println(" [" + i + "] " + folderList.get(i));
        }

        return folderList.toArray(new String[0]);
    }

    public static String[] listarArquivosBackup(String chosenDate, String chosenFolder) throws IOException {
        List<String> backupList = new ArrayList<>();

        Files.list(Paths.get("backup", chosenDate, chosenFolder))
                .filter(Files::isRegularFile)
                .forEach(backupPath -> {
                    backupList.add(backupPath.getFileName().toString());
                });

        for (int i = 0; i < backupList.size(); i++) {
            System.out.println(" [" + i + "] " + backupList.get(i));
        }

        return backupList.toArray(new String[0]);
    }

    public static void recuperarBackups(String chosenDate, String chosenFolder, String fileName) throws Exception {
        Path backupRootPath = Paths.get("backup", chosenDate, chosenFolder);
        if (!Files.exists(backupRootPath)) {
            System.out.println("O backup escolhido não existe.");
            return;
        }

        List<Path> filesToRecover = new ArrayList<>();
        if (fileName == null) { // Todos os arq
            try (Stream<Path> paths = Files.walk(backupRootPath)) {
                paths.filter(Files::isRegularFile).forEach(filesToRecover::add);
            }
        } else { // Arq especifico
            try (Stream<Path> paths = Files.walk(backupRootPath)) {
                paths.filter(Files::isRegularFile)
                        .filter(path -> path.getFileName().toString().contains(fileName))
                        .forEach(filesToRecover::add);
            }
        }

        // Cria o diretório para salvar os arquivos que foram recuperados
        Path recoveredFolder = Paths.get("recuperado", chosenDate, chosenFolder);
        Files.createDirectories(recoveredFolder);

        // Processa cada arquivo de backup para recuperar os dados originais
        for (Path backupFilePath : filesToRecover) {
            byte[] compressedData = Files.readAllBytes(backupFilePath);
            byte[] originalData = LZW.decodifica(compressedData);

            String originalFileName = backupFilePath.getFileName().toString().replace(".lzw", "");
            Path originalFilePath = recoveredFolder.resolve(originalFileName);
            Files.write(originalFilePath, originalData);
        }

        System.out.println("Arquivos recuperados salvos em: " + recoveredFolder);
    }

    public static void recuperarBackupEspecifico(String chosenDate, String chosenFolder, String backupFileName)
            throws Exception {
        Path backupFilePath = Paths.get("backup", chosenDate, chosenFolder, backupFileName);
        if (!Files.exists(backupFilePath)) {
            System.out.println("O backup escolhido não existe.");
            return;
        }
        byte[] compressedData = Files.readAllBytes(backupFilePath);
        byte[] originalData = LZW.decodifica(compressedData);

        String originalFileName = backupFileName.replace(".lzw", "");
        Path recoveredFolder = Paths.get("recuperado", chosenDate, chosenFolder);
        Files.createDirectories(recoveredFolder);

        Path originalFilePath = recoveredFolder.resolve(originalFileName);
        Files.write(originalFilePath, originalData);

        System.out.println("Arquivo recuperado '" + originalFileName + "' salvo em: " + recoveredFolder);
    }
}
